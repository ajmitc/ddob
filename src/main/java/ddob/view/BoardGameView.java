package ddob.view;

import ddob.Model;
import ddob.util.ImageFactory;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class BoardGameView extends GameView {
    public static final String BOARD_IMAGE_FILENAME = "Board_Main"; // "Board_MainAlternate"

    private Model _model;
    private BufferedImage _boardImage;

    public BoardGameView( Model model, View view ) {
        super( model, view );

        _boardImage = ImageFactory.get( BOARD_IMAGE_FILENAME );
    }

    public boolean mouseMoved( MouseEvent e ) {
        // TODO implement scrolling board
        return false;
    }

    public void redraw( Graphics2D g, Dimension panelSize ) {
        g.drawImage( _boardImage, null, 0, 0 );
    }
}
