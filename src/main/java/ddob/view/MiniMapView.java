package ddob.view;

import ddob.Model;
import ddob.util.ImageFactory;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

public class MiniMapView extends GameView {
    private static final int MINI_MAP_WIDTH = 400;
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
}

