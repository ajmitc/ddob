package ddob.view;

import ddob.Model;
import ddob.game.board.*;
import ddob.game.unit.Allegiance;
import ddob.game.unit.GermanUnit;
import ddob.game.unit.Unit;
import ddob.game.unit.UnitType;
import ddob.util.ImageFactory;

import java.awt.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class BoardGameView extends GameView {
    public static final String BOARD_IMAGE_FILENAME = "Board_Main_Cropped"; // "Board_MainAlternate"
    private static final int EDGE_SCROLLING_THRESHOLD = 40;
    private static final int[] SCROLLING_SPEED = { 15, 10, 8, 6 };
    private static final int CELL_OFFSET_Y = 20;
    private static final int CELL_OFFSET_X_ODD = 38;
    private static final int CELL_OFFSET_X_EVEN = 93;
    private static final int CELL_WIDTH = 112;
    private static final int CELL_HEIGHT = 100;
    private static final int UNIT_STACK_OFFSET = 5;
    private static final long MOUSE_HOVER_SHOW_UNITS_DELAY = 500;

    private Logger _logger = Logger.getLogger( BoardGameView.class.getName() );
    private GamePanel _gamePanel;
    private BufferedImage _boardImage;
    private int _vpx, _vpy; // Viewport location
    private int _mpx, _mpy; // Mouse Pointer location
    private Set<Integer> _keysPressed;

    private boolean _allowEdgeScroll;

    private Map<BoardListenerType, List<BoardListener>> _listeners;

    private Dimension _viewportSize;

    private boolean _debug;

    private Cell _mouseHoverCell;
    private Timer _mouseHoverTimer;
    private boolean _cellUnitsDisplayed;

    private Lock _cellUnitsDisplayLock;

    public BoardGameView( Model model, View view, GamePanel gamePanel ) {
        super( model, view );
        _gamePanel = gamePanel;

        _boardImage = ImageFactory.get( BOARD_IMAGE_FILENAME );
        _vpx = 0;
        _vpy = 0;
        _allowEdgeScroll = false;

        _listeners = new HashMap<>();

        _viewportSize = null;

        _keysPressed = new HashSet<>();

        _debug = true;

        _mouseHoverCell = null;
        _mouseHoverTimer = null;
        _cellUnitsDisplayed = false;
        _cellUnitsDisplayLock = new ReentrantLock();
    }

    @Override
    public boolean mouseMoved( MouseEvent e ) {
        if( e.getY() < CELL_OFFSET_Y  || e.getX() < CELL_OFFSET_X_ODD || e.getY() > CELL_OFFSET_Y + (Board.HEIGHT * CELL_HEIGHT) ) {
            return true;
        }

        _mpx = e.getX();
        _mpy = e.getY();

        Cell cell = getCellAtPixel( e.getX(), e.getY() );
        if( cell != null )
            _logger.info( cell.getCoordString() );
        if( cell != null && cell.getCoordString().equals( "0723" ) ) {
            _logger.info( "0723: Units: " + cell.getUnits().size() );
            for( Unit unit: cell.getUnits() ) {
                _logger.info( "0723 Unit: " + unit );
            }
        }
        // If we're pointing at a cell and we're not displaying units already...
        if( cell != null && !_cellUnitsDisplayed ) {
            // if there are two or more units
            // OR if there is one German Unit and it has depth
            // then display the units
            if( cell.getUnits().size() > 1 || (cell.getUnits().size() == 1 && cell.getUnits().get( 0 ) instanceof GermanUnit && ((GermanUnit) cell.getUnits().get( 0 )).getDepthMarker() != null) ) {
                _cellUnitsDisplayLock.lock();
                try {
                    _logger.info( "Hovering over cell " + cell + " (mouseHoverCell: " + _mouseHoverCell + ")" );
                    if( _mouseHoverCell == null || _mouseHoverCell != cell ) {
                        if( _mouseHoverTimer != null ) {
                            try {
                                _mouseHoverTimer.cancel();
                                _mouseHoverTimer = null;
                                _cellUnitsDisplayed = false;
                            } catch( Exception ex ) {

                            }
                        }
                        //_logger.info( "Starting hover timer at cell " + cell );
                        _mouseHoverCell = cell;
                        _mouseHoverTimer = new Timer();
                        _mouseHoverTimer.schedule( new CellUnitsTimerTask( cell ), MOUSE_HOVER_SHOW_UNITS_DELAY );
                    }
                }
                finally {
                    _cellUnitsDisplayLock.unlock();
                }
            }
        }

        return false;
    }

    @Override
    public boolean mouseExited( MouseEvent e ) {
        _mpx = -1;
        _mpy = -1;
        return false;
    }

    @Override
    public boolean mouseClicked( MouseEvent e ){
        // Check if within bounds of Cells
        if( e.getY() < CELL_OFFSET_Y  || e.getX() < CELL_OFFSET_X_ODD || e.getY() > CELL_OFFSET_Y + (Board.HEIGHT * CELL_HEIGHT) ) {
            return true;
        }

        // Determine which Cell the user clicked on
        Cell cell = getCellAtPixel( e.getX(), e.getY() );
        if( cell != null ) {
            if( _listeners.containsKey( BoardListenerType.CELL_SELECTED ) ) {
                for( BoardListener notifiable : _listeners.get( BoardListenerType.CELL_SELECTED ) ) {
                    notifiable.cellSelected( cell, e.getX(), e.getY() );
                }
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean keyPressed( KeyEvent e ) {
        _keysPressed.add( e.getKeyCode() );
        for( Integer keyCode: _keysPressed ) {
            if( keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A ) {
                _vpx -= SCROLLING_SPEED[ 0 ];
            }
            if( keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D ) {
                _vpx += SCROLLING_SPEED[ 0 ];
            }
            if( keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W ) {
                _vpy -= SCROLLING_SPEED[ 0 ];
            }
            if( keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S ) {
                _vpy += SCROLLING_SPEED[ 0 ];
            }
        }
        return false;
    }

    @Override
    public boolean keyReleased( KeyEvent e ) {
        _keysPressed.remove( e.getKeyCode() );
        return false;
    }

    @Override
    public void redraw( Graphics2D g, Dimension panelSize ) {
        int turnTrackHeight = _gamePanel.getTurnTrackView().getTurnTrackImage().getHeight( null );
        int drawHeight = panelSize.height - turnTrackHeight;

        if( _viewportSize == null )
            _viewportSize = new Dimension( panelSize.width, drawHeight );

        if( _allowEdgeScroll ) {
            // Scroll left
            if( _mpx >= 0 && _mpx < EDGE_SCROLLING_THRESHOLD ) {
                _vpx -= SCROLLING_SPEED[ (_mpx / 10) % SCROLLING_SPEED.length ];
            }
            // Scroll right
            if( _mpx > g.getClipBounds().width - EDGE_SCROLLING_THRESHOLD ) {
                _vpx += SCROLLING_SPEED[ ((g.getClipBounds().width - EDGE_SCROLLING_THRESHOLD) / 10) % SCROLLING_SPEED.length ];
            }
            // Scroll up
            if( _mpy >= turnTrackHeight && _mpy < turnTrackHeight + EDGE_SCROLLING_THRESHOLD ) {
                _vpy -= SCROLLING_SPEED[ ((_mpy - turnTrackHeight) / 10) % SCROLLING_SPEED.length ];
            }
            // Scroll down
            if( _mpy > g.getClipBounds().height - EDGE_SCROLLING_THRESHOLD ) {
                _vpy += SCROLLING_SPEED[ ((g.getClipBounds().height - EDGE_SCROLLING_THRESHOLD) / 10) % SCROLLING_SPEED.length ];
            }
        }

        _vpx = Math.max( Math.min( _vpx, _boardImage.getWidth()  - g.getClipBounds().width  ), 0 );
        _vpy = Math.max( Math.min( _vpy, _boardImage.getHeight() - drawHeight ), 0 );

        // Draw portion of map visible
        BufferedImage clip = _boardImage.getSubimage( _vpx, _vpy, g.getClipBounds().width, drawHeight );
        g.drawImage( clip, null, 0, turnTrackHeight );

        // Debug info
        if( _debug ) {
            g.setColor( Color.RED );
            g.drawLine( 0, turnTrackHeight + CELL_OFFSET_Y, 1000, turnTrackHeight + CELL_OFFSET_Y );
            g.drawString( "CELL_OFFSET_Y", 1000, turnTrackHeight + CELL_OFFSET_Y );

            g.drawLine( CELL_OFFSET_X_EVEN, turnTrackHeight, CELL_OFFSET_X_EVEN, turnTrackHeight + 500 );
            g.drawString( "CELL_OFFSET_X_EVEN", CELL_OFFSET_X_EVEN, turnTrackHeight + 500 );

            g.drawLine( CELL_OFFSET_X_ODD, turnTrackHeight, CELL_OFFSET_X_ODD, turnTrackHeight + 450 );
            g.drawString( "CELL_OFFSET_X_ODD", CELL_OFFSET_X_ODD, turnTrackHeight + 450 );

            g.drawRect( 38, 402, CELL_WIDTH, CELL_HEIGHT );

            g.drawString( _mpx + ", " + _mpy, panelSize.width - 100, turnTrackHeight + 30 );
        }

        drawCells( g );
    }

    private void drawCells( Graphics2D g ) {
        CellVisitor visitor = new CellVisitor() {
            @Override
            public boolean visit( Cell cell ) {
                drawCell( g, cell );
                return true;
            }
        };

        _model.getGame().getBoard().visitCells( visitor );
    }


    private void drawCell( Graphics2D g, Cell cell ) {
        // Flip cell coordinates to make the math work out
        int x = cell.getX() - 1;
        int y = cell.getY() - 1;
        y = -y + Board.HEIGHT;

        // Calculate the pixel location on map
        int cellY = CELL_OFFSET_Y + (y * CELL_HEIGHT);
        int cellX = cell.getY() % 2 == 0? CELL_OFFSET_X_EVEN: CELL_OFFSET_X_ODD;
        cellX += (x * CELL_WIDTH);

        // Cell spacing increases slightly the farther from 1,1 you get.  Add a small bias.
        //cellX += cell.getX();

        // If these pixels are outside the viewport, don't draw it
        if( cellX < _vpx - 100 || cellX > _vpx + _viewportSize.width + 100 || cellY < _vpy - 100 || cellY > _vpy + _viewportSize.height + 100 ) {
            // Cell is outside viewable area so don't draw it.
            return;
        }

        // move to proper place on screen
        cellX -= _vpx;
        cellY -= _vpy;

        // Draw units
        for( int i = 0; i < cell.getUnits().size(); ++i ) {
            Unit unit = cell.getUnits().get( i );
            BufferedImage image = null;
            if( unit.getAllegiance() == Allegiance.GERMAN && !((GermanUnit) unit).isRevealed() ) {
                image = _model.getGame().getGermanUnitBack( unit.getType() );
            }
            else {
                image = unit.getState().getImage();
            }

            // Center in cell
            int ux = cellX + ((CELL_WIDTH  - image.getWidth())  / 2);
            int uy = cellY; // + ((CELL_HEIGHT - image.getHeight()) / 2);

            // Offset subsequent units
            ux -= (i * UNIT_STACK_OFFSET);
            uy -= (i * UNIT_STACK_OFFSET);

            if( unit.getAllegiance() == Allegiance.GERMAN && ((GermanUnit) unit).getDepthMarker() != null ) {
                BufferedImage depthImage = ((GermanUnit) unit).getDepthMarker().getImage();
                if( ((GermanUnit) unit).getDepthMarker().isRevealed() ) {
                    depthImage = _model.getGame().getDepthMarkerBack( ((GermanUnit) unit).getDepthMarker().getType() );
                }
                g.drawImage( depthImage, null, ux, uy );
                ux -= UNIT_STACK_OFFSET;
                uy -= UNIT_STACK_OFFSET;
                g.drawImage( image, null, ux, uy );
            }
            else {
                g.drawImage( image, null, ux, uy );
            }

            /*
            if( _debug && unit.getDesignation().equals( "1/B/741" ) ) {
                _logger.info( "Drawing " + unit + " at [" + cellX + ", " + cellY + "]" );
                _logger.info( "Cell: " + cell.getX() + ", " + cell.getY() + " [" + cell.getCode() + "]" );
            }
            */
        }

        // Draw Cell Info
        if( _debug ) {
            g.setColor( Color.RED );
            g.drawString( cell.getY() + "," + cell.getX() + ": " + cell.getTypes().stream().map( f -> f.toString() ).collect( Collectors.joining( ", ") ), cellX, cellY );
        }

    }

    /**
     * Attempt to center the viewport on the given coordinate
     * @param x
     * @param y
     */
    public void center( int x, int y ) {
        _vpx = x - (_viewportSize.width / 2);
        _vpy = y - (_viewportSize.height / 2);
    }


    public int getVpx(){ return _vpx; }
    public int getVpy(){ return _vpy; }

    public void addBoardListener( BoardListenerType type, BoardListener notifiable ) {
        if( !_listeners.containsKey( type ) ) {
            _listeners.put( type, new ArrayList<>() );
        }
        _listeners.get( type ).add( notifiable );
    }

    public void removeBoardListener( BoardListenerType type, BoardListener notifiable ) {
        if( _listeners.containsKey( type ) ) {
            _listeners.get( type ).remove( notifiable );
        }
    }

    public Cell getCellAtPixel( int px, int py ) {
        int y = (py + _vpy - CELL_OFFSET_Y) / CELL_HEIGHT;
        y = -y + Board.HEIGHT + 1;
        int x = px + _vpx;
        if( y % 2 == 0 ) {
            x -= CELL_OFFSET_X_EVEN;
        }
        else {
            x -= CELL_OFFSET_X_ODD;
        }
        x /= CELL_WIDTH;
        x += 1;
        //_logger.info( "Getting cell " + x + ", " + y );
        return _model.getGame().getBoard().get( x, y );
    }


    class CellUnitsTimerTask extends TimerTask {
        private Cell _cell;

        public CellUnitsTimerTask( Cell cell ) {
            super();
            _cell = cell;
        }

        @Override
        public void run() {
            Cell cell = getCellAtPixel( _mpx, _mpy );
            if( cell == _cell ) {
                _cellUnitsDisplayLock.lock();
                try {
                    _logger.info( "Displaying Unit Game View" );
                    CellUnitsGameView cellUnitsGameView = new CellUnitsGameView( _model, _view, _cell.getUnits(), _mpx, _mpy );
                    _view.getGamePanel().pushGameView( cellUnitsGameView );
                    _mouseHoverCell = null;
                    _mouseHoverTimer = null;
                    _cellUnitsDisplayed = true;
                }
                finally {
                    _cellUnitsDisplayLock.unlock();
                }
            }
        }
    }

    public void setCellUnitsDisplayed( boolean v ) { _cellUnitsDisplayed = v; }
}

