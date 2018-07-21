package ddob.view;

import ddob.GameManager;
import ddob.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener{
    public static final long UPDATE_INTERVAL_MILLIS = 60;

    private Model _model;
    private View _view;
    private GameManager _manager;

    private boolean _allowEvents;

    private boolean _updateThreadRunning;
    private ScheduledThreadPoolExecutor _threadPool;

    private List<GameView> _gameViews;
    private BoardGameView _boardView;
    private PhaseGameView _phaseView;

    public GamePanel( Model model, View view ) {
        _model = model;
        _view = view;
        _manager = null;

        _allowEvents = true;

        _updateThreadRunning = false;
        _threadPool = new ScheduledThreadPoolExecutor( 1 );

        _boardView = new BoardGameView( _model, _view );
        _phaseView = new PhaseGameView( _model, _view );

        _gameViews = new ArrayList<>();
        _gameViews.add( _boardView );
        _gameViews.add( _phaseView );
    }

    public void start() {
        _updateThreadRunning = true;
        _threadPool.scheduleAtFixedRate( new GameUpdater(), 0, UPDATE_INTERVAL_MILLIS, TimeUnit.MILLISECONDS );
    }

    public void stop() {
        if( _updateThreadRunning ) {
            _threadPool.shutdownNow();
            _updateThreadRunning = false;
        }
    }

    public void mouseClicked( MouseEvent e ) {
        if( !_allowEvents ) return;
        for( GameView view: _gameViews ) {
            if( !view.mouseClicked( e ))
                break;
        }
    }

    public void mousePressed( MouseEvent e ) {
        if( !_allowEvents ) return;
        for( GameView view: _gameViews ) {
            if( !view.mousePressed( e ))
                break;
        }
    }

    public void mouseReleased( MouseEvent e ) {
        if( !_allowEvents ) return;
        for( GameView view: _gameViews ) {
            if( !view.mouseReleased( e ))
                break;
        }
    }

    public void mouseMoved( MouseEvent e ) {
        if( !_allowEvents ) return;
        for( GameView view: _gameViews ) {
            if( !view.mouseMoved( e ))
                break;
        }
    }

    public void mouseDragged( MouseEvent e ) {
        if( !_allowEvents ) return;
        for( GameView view: _gameViews ) {
            if( !view.mouseDragged( e ))
                break;
        }
    }

    public void mouseEntered( MouseEvent e ) {
        if( !_allowEvents ) return;
        for( GameView view: _gameViews ) {
            if( !view.mouseEntered( e ))
                break;
        }
    }

    public void mouseExited( MouseEvent e ) {
        if( !_allowEvents ) return;
        for( GameView view: _gameViews ) {
            if( !view.mouseExited( e ))
                break;
        }
    }

    public void keyPressed( KeyEvent e ) {
        if( !_allowEvents ) return;
        for( GameView view: _gameViews ) {
            if( !view.keyPressed( e ))
                break;
        }
    }

    public void keyReleased( KeyEvent e ) {
        if( !_allowEvents ) return;
        for( GameView view: _gameViews ) {
            if( !view.keyReleased( e ))
                break;
        }
    }

    public void keyTyped( KeyEvent e ) {
        if( !_allowEvents ) return;
        for( GameView view: _gameViews ) {
            if( !view.keyTyped( e ))
                break;
        }
    }

    class GameUpdater implements Runnable {
        public void run() {
            // Work through the game views backward (top-down)
            List<GameView> remove = new ArrayList<>();
            for( int i = _gameViews.size() - 1; i >= 0; --i ) {
                GameView view = _gameViews.get( i );
                view.update( UPDATE_INTERVAL_MILLIS );
                if( view.shouldHide() )
                    remove.add( view );
            }
            for( GameView view: remove ) {
                _gameViews.remove( view );
            }
            repaint();
        }
    }

    public void paintComponent( Graphics gfx ) {
        Graphics2D g = (Graphics2D) gfx;

        // Update the views bottom-up (first on list is lowest layer)
        for( GameView view: _gameViews ) {
            view.redraw( g, this.getSize() );
        }
    }


    public void pushGameView( GameView gameView ) {
        _gameViews.add( gameView );
    }

    public void setAllowEvents( boolean v ){ _allowEvents = v; }

    public void setGameManager( GameManager manager ){ _manager = manager; }
}
