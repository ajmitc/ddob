package ddob.view;

import ddob.Model;
import ddob.util.ImageFactory;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

public class BoardGameView extends GameView {
    public static final String BOARD_IMAGE_FILENAME = "Board_Main_Cropped"; // "Board_MainAlternate"
    private static final int EDGE_SCROLLING_THRESHOLD = 40;
    private static final int[] SCROLLING_SPEED = { 10, 8, 6, 4 };

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
        if( _mpy >= 0 && _mpy < EDGE_SCROLLING_THRESHOLD ) {
            _vpy -= SCROLLING_SPEED[ (_mpy / 10) % SCROLLING_SPEED.length ];
        }
        // Scroll down
        if( _mpy > g.getClipBounds().height - EDGE_SCROLLING_THRESHOLD ) {
            _vpy += SCROLLING_SPEED[ ((g.getClipBounds().height - EDGE_SCROLLING_THRESHOLD) / 10) % SCROLLING_SPEED.length ];
        }
        _vpx = Math.max( Math.min( _vpx, _boardImage.getWidth()  - g.getClipBounds().width  ), 0 );
        _vpy = Math.max( Math.min( _vpy, _boardImage.getHeight() - drawHeight ), 0 );

        BufferedImage clip = _boardImage.getSubimage( _vpx, _vpy, g.getClipBounds().width, drawHeight );
        g.drawImage( clip, null, 0, turnTrackHeight );
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
