package ddob.view;

import ddob.Model;
import ddob.game.board.Board;
import ddob.game.board.Cell;
import ddob.game.board.CellVisitor;
import ddob.game.unit.Allegiance;
import ddob.game.unit.GermanUnit;
import ddob.game.unit.Unit;
import ddob.util.ImageFactory;
import ddob.util.Util;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MiniMapView extends GameView {
    private static final int MINI_MAP_WIDTH = 400;
    private static final int UNIT_SIZE = 5;
    private static final Color US_COLOR = Color.GREEN;
    private static final Color GERMAN_COLOR = Color.DARK_GRAY;

    private Logger _logger = Logger.getLogger( MiniMapView.class.getName() );
    private BufferedImage _minimapImage;
    private Image _scaledMinimapImage;
    private BoardGameView _boardGameView;
    private double _scaleX, _scaleY;
    private int _minimapX, _minimapY;

    public MiniMapView( Model model, View view, BoardGameView boardGameView ) {
        super( model, view );
        _boardGameView = boardGameView;

        _minimapImage = ImageFactory.get( BoardGameView.BOARD_IMAGE_FILENAME );
        componentResized( null );
    }

    @Override
    public boolean mouseClicked( MouseEvent e ) {
        if( e.getX() < _minimapX + _scaledMinimapImage.getWidth(  null ) && e.getY() > _minimapY ) {
            // Center board on mouse click
            int vpx = (int) (((double) (e.getX() - _minimapX)) / _scaleX);
            int vpy = (int) (((double) (e.getY() - _minimapY)) / _scaleY);
            _boardGameView.center( vpx, vpy );
            return false;
        }
        return true;
    }

    @Override
    public boolean mouseMoved( MouseEvent e ) {
        if( e.getX() < _minimapX + _scaledMinimapImage.getWidth( null ) && e.getY() > _minimapY ) {
            return false;
        }
        return true;
    }

    @Override
    public void componentResized( ComponentEvent e ) {
        _scaledMinimapImage = _minimapImage.getScaledInstance( MINI_MAP_WIDTH, -1, 0 );
        _scaleX = ((double) _scaledMinimapImage.getWidth( null )) / ((double) _minimapImage.getWidth());
        _scaleY = ((double) _scaledMinimapImage.getHeight( null )) / ((double) _minimapImage.getHeight());
    }

    @Override
    public void redraw( Graphics2D g, Dimension panelSize ) {
        if( !_view.getGamePanel().shouldShowPeripheralViews() )
            return;
        int x = 0; //panelSize.width - MINI_MAP_WIDTH;
        int y = panelSize.height - _scaledMinimapImage.getHeight( null );
        g.drawImage( _scaledMinimapImage, x, y, null );

        // Draw units as small boxes (germans=gray, us=green)
        drawCells( g, x, y );

        // draw red rect
        g.setColor( Color.RED );
        int bx = (int) (((double) _boardGameView.getVpx()) * _scaleX);
        int by = (int) (((double) _boardGameView.getVpy()) * _scaleY);
        int bw = (int) (((double) panelSize.width) * _scaleX );
        int bh = (int) (((double) panelSize.height) * _scaleY );
        g.drawRect( x + bx, y + by, bw, bh );

        g.setColor( Color.BLACK );
        g.drawRect( x, y, _scaledMinimapImage.getWidth( null ), _scaledMinimapImage.getHeight( null ) );


        _minimapX = x;
        _minimapY = y;
    }

    private void drawCells( Graphics2D g, int mmx, int mmy ) {
        CellVisitor visitor = new CellVisitor() {
            @Override
            public boolean visit( Cell cell ) {
                drawCell( g, cell, mmx, mmy );
                return true;
            }
        };

        _model.getGame().getBoard().visitCells( visitor );
    }


    private void drawCell( Graphics2D g, Cell cell, int minimapX, int minimapY ) {
        // Flip cell coordinates to make the math work out
        int x = cell.getX() - 1;
        int y = cell.getY() - 1;
        y = -y + Board.HEIGHT;

        int cellY = BoardGameView.CELL_OFFSET_Y + (y * BoardGameView.CELL_HEIGHT);
        int cellX = cell.getY() % 2 == 0? BoardGameView.CELL_OFFSET_X_EVEN: BoardGameView.CELL_OFFSET_X_ODD;
        cellX += (x * BoardGameView.CELL_WIDTH);

        cellX *= _scaleX;
        cellY *= _scaleY;

        cellX += minimapX;
        cellY += minimapY;

        // Draw units
        if( cell.getUnits().size() > 0 ) {
            Unit unit = cell.getUnits().get( 0 );

            if( unit.getAllegiance() == Allegiance.GERMAN ) {
                g.setColor( GERMAN_COLOR );
            }
            else {
                g.setColor( US_COLOR );
            }
            g.fillRect( cellX, cellY, UNIT_SIZE, UNIT_SIZE );
            g.setColor( Color.BLACK );
            g.drawRect( cellX, cellY, UNIT_SIZE, UNIT_SIZE );
            _logger.info( "Drawing minimap unit at [" + cellX + ", " + cellY + "]" );
        }
    }
}

