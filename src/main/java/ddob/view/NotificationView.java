package ddob.view;

import ddob.Model;
import ddob.util.Util;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Each GameView represents a layer of the GamePanel.  For example, one GameView may be the map, another may be the
 * mini-map, another may be the Card/Phase, etc.
 */
public class NotificationView extends GameView {
	public static final Color NOTIFICATION_BG_COLOR = Color.BLACK;
	public static final Color NOTIFICATION_FG_COLOR = Color.WHITE;
	public static final int MAX_TEXT_LENGTH = 30;
	public static final Font FONT = new Font( "Serif", Font.PLAIN, 16 );
	
	public List<NotificationPanel> _panels;
	
    public NotificationView( Model model, View view ) {
        super( model, view );
		_panels = new ArrayList<>();
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
				NotificationPanel panelToRemove = null;
				for( NotificationPanel panel: _panels ) {
					if( panel.getNotification() == notification ) {
						panelToRemove = panel;
						break;
					}
				}
				if( panelToRemove != null ) {
					_panels.remove( panelToRemove );
				}
			}
			else {
				// Check if panel exists
				boolean found = false;
				for( NotificationPanel panel: _panels ) {
					if( panel.getNotification() == notification ) {
						found = true;
						break;
					}
				}
				if( !found ) {
					// Add panel
					NotificationPanel panel = new NotificationPanel( notification );
					_panels.add( panel );
				}
			}
		}
	}

	@Override
    public void redraw( Graphics2D g, Dimension panelSize ) {
		// Draw notifications on screen
		int turnTrackHeight = _view.getGamePanel().getTurnTrackView().getTurnTrackImage().getHeight( null );
		int x = (int) panelSize.getWidth() - 100; // centered-ish
		int y = turnTrackHeight + 3;
		for( NotificationPanel panel: _panels ) {
			panel.draw( g, x, y );
			y += panel.getHeight();
		}
	}
	
	class NotificationPanel {
		private Notification _notification;
		private List<String> _content;
		private int _width, _height, _lineHeight;
		
		public NotificationPanel( Notification notification ) {
			_notification = notification;
			_content = Util.breakString( _notification.getContent(), MAX_TEXT_LENGTH );
			_width = -1;
			_height = -1;
		}
		
		public void draw( Graphics2D g, int x, int y ) {
			if( _width < 0 || _height < 0 ) {
				Dimension textSize = Util.calculateStringDimension( g, FONT, _content, 2 );
				_width = textSize.width;
				_height = textSize.height;
				_lineHeight = Util.getFontHeight( g, FONT ) + 2;
			}
			
			g.setColor( NOTIFICATION_BG_COLOR );
			g.fillRect( x, y, _width, _height );
			g.setColor( NOTIFICATION_FG_COLOR );
			for( String c: _content ) {
				g.drawString( c, x, y );
				y += _lineHeight;
			}
		}
		
		public int getHeight(){ return _height; }
		public Notification getNotification(){ return _notification; }
	}
}
