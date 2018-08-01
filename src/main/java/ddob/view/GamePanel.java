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

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener, ComponentListener{
    public static final long UPDATE_INTERVAL_MILLIS = 60;

    public static final Color GAME_VIEW_BACKGROUND = Color.GRAY;
    public static final Color GAME_VIEW_FONT_COLOR = Color.BLACK;
    public static final Font GAME_VIEW_FONT = new Font( "Serif", Font.BOLD, 16 );

    private Model _model;
    private View _view;
    private GameManager _manager;

    private boolean _allowEvents;

    private boolean _updateThreadRunning;
    private ScheduledThreadPoolExecutor _threadPool;

    private List<GameView> _gameViews;
    private BoardGameView _boardView;
    private PhaseGameView _phaseView;
    private TurnTrackView _turnView;
    private MiniMapView _minimapView;
	private NotificationView _notificationView;
    private TopGlassView _topGlassView;

    private boolean _showPeripheralViews;

    public GamePanel( Model model, View view ) {
        _model = model;
        _view = view;
        _manager = null;

        _allowEvents = true;

        _updateThreadRunning = false;
        _threadPool = new ScheduledThreadPoolExecutor( 1 );

        addMouseListener( this );
        addMouseMotionListener( this );
        addKeyListener( this );

        _showPeripheralViews = true;

        _phaseView = new PhaseGameView( _model, _view );
        _turnView  = new TurnTrackView( _model, _view );
        _boardView = new BoardGameView( _model, _view, this );
        _minimapView = new MiniMapView( _model, _view, _boardView );
		_notificationView = new NotificationView( _model, _view );
        _topGlassView = new TopGlassView( _model, _view );

        _gameViews = new ArrayList<>();
        _gameViews.add( _boardView );
        _gameViews.add( _phaseView );
        _gameViews.add( _turnView  );
        _gameViews.add( _minimapView );
		_gameViews.add( _notificationView );
        // Add additional views here
        _gameViews.add( _topGlassView );
    }

    public void start() {
        _updateThreadRunning = true;
        _threadPool.scheduleAtFixedRate( new GameUpdater(), 0, UPDATE_INTERVAL_MILLIS, TimeUnit.MILLISECONDS );
		requestFocus();
    }

    public void stop() {
        if( _updateThreadRunning ) {
            _threadPool.shutdownNow();
            _updateThreadRunning = false;
        }
    }

    @Override
    public void mouseClicked( MouseEvent e ) {
        if( !_allowEvents ) return;
        for( int i = _gameViews.size() - 1; i >= 0; --i ) {
            GameView view = _gameViews.get( i );
            if( !view.mouseClicked( e ))
                break;
        }
    }

    @Override
    public void mousePressed( MouseEvent e ) {
        if( !_allowEvents ) return;
        for( int i = _gameViews.size() - 1; i >= 0; --i ) {
            GameView view = _gameViews.get( i );
            if( !view.mousePressed( e ))
                break;
        }
    }

    @Override
    public void mouseReleased( MouseEvent e ) {
        if( !_allowEvents ) return;
        for( int i = _gameViews.size() - 1; i >= 0; --i ) {
            GameView view = _gameViews.get( i );
            if( !view.mouseReleased( e ))
                break;
        }
    }

    @Override
    public void mouseMoved( MouseEvent e ) {
        if( !_allowEvents ) return;
        for( int i = _gameViews.size() - 1; i >= 0; --i ) {
            GameView view = _gameViews.get( i );
            if( !view.mouseMoved( e ))
                break;
        }
    }

    @Override
    public void mouseDragged( MouseEvent e ) {
        if( !_allowEvents ) return;
        for( int i = _gameViews.size() - 1; i >= 0; --i ) {
            GameView view = _gameViews.get( i );
            if( !view.mouseDragged( e ))
                break;
        }
    }

    @Override
    public void mouseEntered( MouseEvent e ) {
        if( !_allowEvents ) return;
        for( int i = _gameViews.size() - 1; i >= 0; --i ) {
            GameView view = _gameViews.get( i );
            if( !view.mouseEntered( e ))
                break;
        }
    }

    @Override
    public void mouseExited( MouseEvent e ) {
        if( !_allowEvents ) return;
        for( int i = _gameViews.size() - 1; i >= 0; --i ) {
            GameView view = _gameViews.get( i );
            if( !view.mouseExited( e ))
                break;
        }
    }

    @Override
    public void keyPressed( KeyEvent e ) {
        if( !_allowEvents ) return;
        for( int i = _gameViews.size() - 1; i >= 0; --i ) {
            GameView view = _gameViews.get( i );
            if( !view.keyPressed( e ))
                break;
        }
    }

    @Override
    public void keyReleased( KeyEvent e ) {
        if( !_allowEvents ) return;
        for( int i = _gameViews.size() - 1; i >= 0; --i ) {
            GameView view = _gameViews.get( i );
            if( !view.keyReleased( e ))
                break;
        }
    }

    @Override
    public void keyTyped( KeyEvent e ) {
        if( !_allowEvents ) return;
        for( int i = _gameViews.size() - 1; i >= 0; --i ) {
            GameView view = _gameViews.get( i );
            if( !view.keyTyped( e ))
                break;
        }
    }

    @Override
    public void componentResized( ComponentEvent e ) {
        for( GameView view: _gameViews ) {
            view.componentResized( e );
        }
    }

    @Override
    public void componentShown( ComponentEvent e ) {

    }

    @Override
    public void componentMoved( ComponentEvent e ) {

    }

    @Override
    public void componentHidden( ComponentEvent e ) {

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

    public void togglePeripheralViews() {
        _showPeripheralViews = !_showPeripheralViews;
    }

    public boolean shouldShowPeripheralViews() {
        return _showPeripheralViews;
    }


    public void pushGameView( GameView gameView ) {
        _gameViews.add( gameView );
    }

    public void setAllowEvents( boolean v ){ _allowEvents = v; }

    public void setGameManager( GameManager manager ){ _manager = manager; }

    public TurnTrackView getTurnTrackView() {
        return _turnView;
    }
}
