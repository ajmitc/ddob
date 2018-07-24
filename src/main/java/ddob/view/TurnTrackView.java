package ddob.view;

import ddob.Model;
import ddob.util.ImageFactory;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

public class TurnTrackView extends GameView {
    public static final String TRACK_IMAGE_FILENAME = "Turn_Track_1-16";
    private static final int DEFAULT_TURN_SPACE_OFFSET = 50;
    private static final int DEFAULT_TURN_SPACE_WIDTH = 100;
    private static final int TURN_MARKER_OFFSET_Y = 20;
    private static final int TURN_MARKER_WIDTH = 60;
    private static final int TURN_MARKER_HEIGHT = 60;

    private Logger _logger = Logger.getLogger( TurnTrackView.class.getName() );
    private BufferedImage _trackImage;
    private Image _scaledTrackImage;
    private int _turnSpaceWidth;

    public TurnTrackView( Model model, View view ) {
        super( model, view );
        _turnSpaceWidth = DEFAULT_TURN_SPACE_WIDTH;

        _trackImage = ImageFactory.get( TRACK_IMAGE_FILENAME );
        _scaledTrackImage = _trackImage;
    }

    public void redraw( Graphics2D g, Dimension panelSize ) {
        if( !_view.getGamePanel().shouldShowPeripheralViews() )
            return;
        if( panelSize.width != _scaledTrackImage.getWidth( null ) ) {
            _scaledTrackImage = _trackImage.getScaledInstance( panelSize.width, -1, 0 );
        }
        g.drawImage( _scaledTrackImage, 0, 0, null );

        // Turn marker
        int markerX = DEFAULT_TURN_SPACE_OFFSET + ((_model.getGame().getCurrentTurn().getNumber() - 1) * _turnSpaceWidth);
        int markerY = TURN_MARKER_OFFSET_Y;
        g.setColor( Color.BLACK );
        g.fillRect( markerX, markerY, TURN_MARKER_WIDTH, TURN_MARKER_HEIGHT );
        g.setColor( Color.WHITE );
        g.drawString( "Turn", markerX + 5, markerY + (TURN_MARKER_HEIGHT / 2) );
    }

    public Image getTurnTrackImage() { return _scaledTrackImage; }
}
