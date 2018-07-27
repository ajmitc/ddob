package ddob.view;

import ddob.Model;
import ddob.game.board.Cell;
import ddob.game.board.CellVisitor;
import ddob.game.unit.Allegiance;
import ddob.game.unit.GermanUnit;
import ddob.game.unit.Unit;
import ddob.game.unit.UnitType;
import ddob.util.ImageFactory;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

public class BoardGameView extends GameView {
    public static final String BOARD_IMAGE_FILENAME = "Board_Main_Cropped"; // "Board_MainAlternate"
    private static final int EDGE_SCROLLING_THRESHOLD = 40;
    private static final int[] SCROLLING_SPEED = { 10, 8, 6, 4 };
    private static final int CELL_OFFSET_Y = 100;
    private static final int CELL_OFFSET_X_EVEN = 100;
    private static final int CELL_OFFSET_X_ODD = 100;
    private static final int CELL_WIDTH = 100;
    private static final int CELL_HEIGHT = 100;

    private Logger _logger = Logger.getLogger( BoardGameView.class.getName() );
    private GamePanel _gamePanel;
    private BufferedImage _boardImage;
    private int _vpx, _vpy; // Viewport location
    private int _mpx, _mpy; // Mouse Pointer location

    public BoardGameView( Model model, View view, GamePanel gamePanel ) {
        super( model, view );
        _gamePanel = gamePanel;

        _boardImage = ImageFactory.get( BOARD_IMAGE_FILENAME );
        _vpx = 0;
        _vpy = 0;
    }

    public boolean mouseMoved( MouseEvent e ) {
        _mpx = e.getX();
        _mpy = e.getY();
        return false;
    }

    public boolean mouseExited( MouseEvent e ) {
        _mpx = -1;
        _mpy = -1;
        return false;
    }

    public void redraw( Graphics2D g, Dimension panelSize ) {
        int turnTrackHeight = _gamePanel.getTurnTrackView().getTurnTrackImage().getHeight( null );
        int drawHeight = panelSize.height - turnTrackHeight;

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
        _vpx = Math.max( Math.min( _vpx, _boardImage.getWidth()  - g.getClipBounds().width  ), 0 );
        _vpy = Math.max( Math.min( _vpy, _boardImage.getHeight() - drawHeight ), 0 );

        BufferedImage clip = _boardImage.getSubimage( _vpx, _vpy, g.getClipBounds().width, drawHeight );
        g.drawImage( clip, null, 0, turnTrackHeight );

        drawCells( g, panelSize );
    }

    private void drawCells( Graphics2D g, Dimension panelSize ) {
        CellVisitor visitor = new CellVisitor() {
            @Override
            public boolean visit( Cell cell ) {
                drawCell( g, cell, panelSize );
                return true;
            }
        };

        _model.getGame().getBoard().visitCells( visitor );
    }


    private void drawCell( Graphics2D g, Cell cell, Dimension panelSize ) {
        int cellY = CELL_OFFSET_Y + (cell.getY() * CELL_HEIGHT);
        int cellX = cell.getY() % 2 == 0? CELL_OFFSET_X_EVEN: CELL_OFFSET_X_ODD;
        cellX += (cell.getX() * CELL_WIDTH);

        if( cellX < _vpx - 100 || cellX > _vpx + panelSize.width + 100 || cellY < _vpy - 100 || cellY > _vpy + panelSize.height + 100 ) {
            // Cell is outside viewable area so don't draw it.
            return;
        }

        // Draw units
        for( Unit unit: cell.getUnits() ) {
            BufferedImage image = null;
            if( unit.getAllegiance() == Allegiance.GERMAN && !((GermanUnit) unit).isRevealed() ) {
                if( unit.getType().isWN() ) {
                    if( unit.getType() == UnitType.WN_ARTILLERY_75 ) {
                        image = _model.getGame().getGermanWNArtillery75Back();
                    }
                    else if( unit.getType() == UnitType.WN_ARTILLERY_88 ) {
                        image = _model.getGame().getGermanWNArtillery88Back();
                    }
                    else if( unit.getType() == UnitType.WN_ROCKET ) {
                        image = _model.getGame().getGermanWNRocketBack();
                    }
                    else {
                        image = _model.getGame().getGermanWNBack();
                    }
                }
                else if( unit.getType() == UnitType.INFANTRY ) {
                    image = _model.getGame().getGermanReinforcementBack();
                }
            }
            else {
                image = unit.getState().getImage();
            }

            // move to proper place on screen
            cellX -= _vpx;
            cellY -= _vpy;
            g.drawImage( image, null, cellX, cellY );
        }
    }

    /**
     * Attempt to center the viewport on the given coordinate
     * @param x
     * @param y
     */
    public void center( int x, int y ) {
        _vpx = x - (_boardImage.getWidth() / 2);
        _vpy = y - (_boardImage.getHeight() / 2);
    }


    public int getVpx(){ return _vpx; }
    public int getVpy(){ return _vpy; }
}
