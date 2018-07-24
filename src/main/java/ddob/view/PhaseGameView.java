package ddob.view;

import ddob.Model;
import ddob.game.card.Card;
import ddob.util.ImageFactory;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

/**
 * This view will display the current phase and the drawn card.  Clicking on the Card will display it in a CardGameView.
 */
public class PhaseGameView extends GameView {
    public static final int WIDTH  = 250;
    public static final int HEIGHT = 300;

    public static final int PHASE_NAME_OFFSET_X = 10;
    public static final int PHASE_NAME_OFFSET_Y = 20;

    public static final int PHASE_CARD_OFFSET_X = PHASE_NAME_OFFSET_X;
    public static final int PHASE_CARD_OFFSET_Y = PHASE_NAME_OFFSET_Y + 10;

    private Logger _logger = Logger.getLogger( PhaseGameView.class.getName() );

    public int _viewY;

    public PhaseGameView( Model model, View view ) {
        super( model, view );
    }

    public boolean mouseClicked( MouseEvent e ) {
        if( e.getX() < WIDTH && e.getY() > _viewY + PHASE_CARD_OFFSET_Y ) {
            // Show card in CardGameView
            Card card = _model.getGame().getCurrentTurn().getCurrentPhase().getCard();
            if( card != null ) {
                CardGameView newView = new CardGameView( _model, _view, card );
                _view.getGamePanel().pushGameView( newView );
            }
            return false;
        }

        return true;
    }

    public void redraw( Graphics2D g, Dimension panelSize ) {
        if( !_view.getGamePanel().shouldShowPeripheralViews() )
            return;
        int viewX = 0;
        _viewY = g.getClipBounds().height - HEIGHT;

        // Background
        g.setColor( GamePanel.GAME_VIEW_BACKGROUND );
        g.fillRect( viewX, _viewY, WIDTH, HEIGHT );

        // Phase name
        g.setColor( GamePanel.GAME_VIEW_FONT_COLOR );
        g.setFont( GamePanel.GAME_VIEW_FONT );
        g.drawString( _model.getGame().getCurrentTurn().getCurrentPhase().getName(), viewX + PHASE_NAME_OFFSET_X, _viewY + PHASE_NAME_OFFSET_Y );

        // Phase card
        Card card = _model.getGame().getCurrentTurn().getCurrentPhase().getCard();
        if( card != null ) {
            g.drawImage( card.getImage(), null, viewX + PHASE_CARD_OFFSET_X, _viewY + PHASE_CARD_OFFSET_Y );
        }
    }
}
