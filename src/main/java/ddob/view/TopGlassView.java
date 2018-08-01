package ddob.view;

import ddob.Model;
import ddob.game.card.Card;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

/**
 * This view captures the TAB key pressed that will hide other views.
 */
public class TopGlassView extends GameView {
    private Logger _logger = Logger.getLogger( TopGlassView.class.getName() );

    public TopGlassView( Model model, View view ) {
        super( model, view );
    }

    @Override
    public boolean keyTyped( KeyEvent e ) {
        if( e.getKeyCode() == KeyEvent.VK_TAB ) {
            // Hide Game Views on top of board
            _view.getGamePanel().togglePeripheralViews();
            return false;
        }
        return true;
    }

    public void redraw( Graphics2D g, Dimension panelSize ) {
    }
}
