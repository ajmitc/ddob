package ddob.view;

import ddob.Model;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Each GameView represents a layer of the GamePanel.  For example, one GameView may be the map, another may be the
 * mini-map, another may be the Card/Phase, etc.
 */
public abstract class NotificationView {
	public static final Color NOTIFICATION_BG_COLOR = Color.BLACK;
	public static final Color NOTIFICATION_FG_COLOR = Color.WHITE;
	
    public NotificationView( Model model, View view ) {
        super( model, view );
    }

    /**
     * This method is used to progress an animation, if any, within the GameView or perform some other computation.
     * @param timeDiffMillis Amount of time since the last time update has been called
     */
	@Override
    public void update( long timeDiffMillis ) {
		// Remove expired notifications
		Iterator<Notification> iter = _view.getNotifications().iterator();
		while( iter.hasNext() ) {
			Notification notification = iter.next();
			if( notification.shouldHide() ) {
				iter.remove();
			}
		}
	}

	@Override
    public void redraw( Graphics2D g, Dimension panelSize ) {
		// Draw notifications on screen
		int turnTrackHeight = _gamePanel.getTurnTrackView().getTurnTrackImage().getHeight( null );
		int x = panelSize.getWidth() - 100; // centered-ish
		int y = turnTrackHeight + 3;
		for( Notification notification: _view.getNotifications() ) {
			NotificationPanel panel = new NotificationPanel( notification );
			panel.draw( g, x, y );
			y += panel.getHeight();
		}
	}
	
	class NotificationPanel {
		private Notification _notification;
		public NotificationPanel( Notification notification ) {
			_notification = notification;
		}
		
		public void draw( Graphics2D g, int x, int y ) {
			String content = _notification.getContent();
			int w = 0;  // Measure width
			int h = 0;  // Measure height
			g.setColor( NOTIFICATION_BG_COLOR );
			g.fillRect( x, y, w, h );
			g.setColor( NOTIFICATION_FG_COLOR );
			g.drawString( content, x, y ) ;
		}
	}
}
