package ddob.view;

import ddob.Model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Each GameView represents a layer of the GamePanel.  For example, one GameView may be the map, another may be the
 * mini-map, another may be the Card/Phase, etc.
 */
public abstract class GameView {
    protected Model _model;
    protected View _view;

    public GameView( Model model, View view ) {
        super();
        _model = model;
        _view = view;
    }

    /**
     * This method is used to progress an animation, if any, within the GameView or perform some other computation.
     * @param timeDiffMillis Amount of time since the last time update has been called
     */
    public void update( long timeDiffMillis ) {}

    public void redraw( Graphics2D g, Dimension panelSize ) {}

    /**
     * Mouse/Key Listeners
     * @param e Event
     * @return true if the Event should be propagated down to the next GameView layer, false if the event should be captured.
     */
    public boolean mouseClicked( MouseEvent e ) { return true; }

    public boolean mousePressed( MouseEvent e ) { return true; }

    public boolean mouseReleased( MouseEvent e ) { return true; }

    public boolean mouseMoved( MouseEvent e ) { return true; }

    public boolean mouseDragged( MouseEvent e ) { return true; }

    public boolean mouseEntered( MouseEvent e ) { return true; }

    public boolean mouseExited( MouseEvent e ) { return true; }

    public boolean keyPressed( KeyEvent e ) { return true; }

    public boolean keyReleased( KeyEvent e ) { return true; }

    public boolean keyTyped( KeyEvent e ) { return true; }

    /**
     * This is called after every update to determine if this Game View should be hidden
     * @return
     */
    public boolean shouldHide(){ return false; }
}
