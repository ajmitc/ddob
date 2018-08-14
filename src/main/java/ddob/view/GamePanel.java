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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

    private Lock _gameviewListLock;

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
        //_gameViews.add( _phaseView );
        _gameViews.add( _turnView  );
        _gameViews.add( _minimapView );
		_gameViews.add( _notificationView );
        // Add additional views here
        _gameViews.add( _topGlassView );

        _gameviewListLock = new ReentrantLock();
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
        _gameviewListLock.lock();
        try {
            for( int i = _gameViews.size() - 1; i >= 0; --i ) {
                GameView view = _gameViews.get( i );
                if( !view.mouseClicked( e ) )
                    break;
            }
        }
        finally {
            _gameviewListLock.unlock();
        }
    }

    @Override
    public void mousePressed( MouseEvent e ) {
        if( !_allowEvents ) return;
        _gameviewListLock.lock();
        try {
            for( int i = _gameViews.size() - 1; i >= 0; --i ) {
                GameView view = _gameViews.get( i );
                if( !view.mousePressed( e ) )
                    break;
            }
        }
        finally {
            _gameviewListLock.unlock();
        }
    }

    @Override
    public void mouseReleased( MouseEvent e ) {
        if( !_allowEvents ) return;
        _gameviewListLock.lock();
        try {
            for( int i = _gameViews.size() - 1; i >= 0; --i ) {
                GameView view = _gameViews.get( i );
                if( !view.mouseReleased( e ) )
                    break;
            }
        }
        finally {
            _gameviewListLock.unlock();
        }
    }

    @Override
    public void mouseMoved( MouseEvent e ) {
        if( !_allowEvents ) return;
        _gameviewListLock.lock();
        try {
            for( int i = _gameViews.size() - 1; i >= 0; --i ) {
                GameView view = _gameViews.get( i );
                if( !view.mouseMoved( e ) )
                    break;
            }
        }
        finally {
            _gameviewListLock.unlock();
        }
    }

    @Override
    public void mouseDragged( MouseEvent e ) {
        if( !_allowEvents ) return;
        _gameviewListLock.lock();
        try {
            for( int i = _gameViews.size() - 1; i >= 0; --i ) {
                GameView view = _gameViews.get( i );
                if( !view.mouseDragged( e ) )
                    break;
            }
        }
        finally {
            _gameviewListLock.unlock();
        }
    }

    @Override
    public void mouseEntered( MouseEvent e ) {
        if( !_allowEvents ) return;
        _gameviewListLock.lock();
        try {
            for( int i = _gameViews.size() - 1; i >= 0; --i ) {
                GameView view = _gameViews.get( i );
                if( !view.mouseEntered( e ) )
                    break;
            }
        }
        finally {
            _gameviewListLock.unlock();
        }
    }

    @Override
    public void mouseExited( MouseEvent e ) {
        if( !_allowEvents ) return;
        _gameviewListLock.lock();
        try {
            for( int i = _gameViews.size() - 1; i >= 0; --i ) {
                GameView view = _gameViews.get( i );
                if( !view.mouseExited( e ) )
                    break;
            }
        }
        finally {
            _gameviewListLock.unlock();
        }
    }

    @Override
    public void keyPressed( KeyEvent e ) {
        if( !_allowEvents ) return;
        _gameviewListLock.lock();
        try {
            for( int i = _gameViews.size() - 1; i >= 0; --i ) {
                GameView view = _gameViews.get( i );
                if( !view.keyPressed( e ) )
                    break;
            }
        }
        finally {
            _gameviewListLock.unlock();
        }
    }

    @Override
    public void keyReleased( KeyEvent e ) {
        if( !_allowEvents ) return;
        _gameviewListLock.lock();
        try {
            for( int i = _gameViews.size() - 1; i >= 0; --i ) {
                GameView view = _gameViews.get( i );
                if( !view.keyReleased( e ) )
                    break;
            }
        }
        finally {
            _gameviewListLock.unlock();
        }
    }

    @Override
    public void keyTyped( KeyEvent e ) {
        if( !_allowEvents ) return;
        _gameviewListLock.lock();
        try {
            for( int i = _gameViews.size() - 1; i >= 0; --i ) {
                GameView view = _gameViews.get( i );
                if( !view.keyTyped( e ) )
                    break;
            }
        }
        finally {
            _gameviewListLock.unlock();
        }
    }

    @Override
    public void componentResized( ComponentEvent e ) {
        _gameviewListLock.lock();
        try {
            for( GameView view : _gameViews ) {
                view.componentResized( e );
            }
        }
        finally {
            _gameviewListLock.unlock();
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
            _gameviewListLock.lock();
            try {
                for( int i = _gameViews.size() - 1; i >= 0; --i ) {
                    GameView view = _gameViews.get( i );
                    view.update( UPDATE_INTERVAL_MILLIS );
                    if( view.shouldHide() )
                        remove.add( view );
                }
                for( GameView view : remove ) {
                    _gameViews.remove( view );
                }
            }
            finally {
                _gameviewListLock.unlock();
            }
            repaint();
        }
    }

    public void paintComponent( Graphics gfx ) {
        Graphics2D g = (Graphics2D) gfx;

        // Update the views bottom-up (first on list is lowest layer)
        _gameviewListLock.lock();
        try {
            for( GameView view : _gameViews ) {
                view.redraw( g, this.getSize() );
            }
        }
        finally {
            _gameviewListLock.unlock();
        }
    }

    public void togglePeripheralViews() {
        _showPeripheralViews = !_showPeripheralViews;
    }

    public boolean shouldShowPeripheralViews() {
        return _showPeripheralViews;
    }


    public void pushGameView( GameView gameView ) {
        _gameviewListLock.lock();
        _gameViews.add( gameView );
        _gameviewListLock.unlock();
    }

    public void setAllowEvents( boolean v ){ _allowEvents = v; }

    public void setGameManager( GameManager manager ){ _manager = manager; }

    public TurnTrackView getTurnTrackView() {
        return _turnView;
    }

    public BoardGameView getBoardView() {
        return _boardView;
    }
}
