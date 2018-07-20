package ddob.view;

import ddob.Model;
import ddob.game.card.Card;
import ddob.util.ImageFactory;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class CardGameView extends GameView {
    private Card _card;
    private boolean _hide;

    public CardGameView( Model model, View view, Card card ) {
        super( model, view );
        _card = card;
        _hide = false;
    }

    public boolean mouseClicked( MouseEvent e ) {
        _hide = true;
        return false;
    }

    public void redraw( Graphics2D g, Dimension panelSize ) {
        g.drawImage( _card.getImage(), null, (int) (panelSize.getWidth() / 2) - (_card.getImage().getWidth() / 2), (int) (panelSize.getHeight() / 2) - (_card.getImage().getHeight() / 2) );
    }

    public boolean shouldHide() {
        return _hide;
    }
}
