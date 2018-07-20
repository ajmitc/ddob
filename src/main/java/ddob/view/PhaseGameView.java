package ddob.view;

import ddob.Model;
import ddob.game.card.Card;
import ddob.util.ImageFactory;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * This view will display the current phase and the drawn card.  Clicking on the Card will display it in a CardGameView.
 */
public class PhaseGameView extends GameView {
    private Model _model;

    public PhaseGameView( Model model, View view ) {
        super( model, view );
    }

    public boolean mouseClicked( MouseEvent e ) {
        // Show card in CardGameView
        Card card = _model.getGame().getCurrentTurn().getCurrentPhase().getCard();
        if( card != null ) {
            CardGameView newView = new CardGameView( _model, _view, card );
            _view.getGamePanel().pushGameView( newView );
        }

        return false;
    }

    public void redraw( Graphics2D g, Dimension panelSize ) {
        Card card = _model.getGame().getCurrentTurn().getCurrentPhase().getCard();
        if( card != null )
            g.drawImage( card.getImage(), null, 0, 0 );
    }
}
