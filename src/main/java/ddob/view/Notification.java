package ddob.view;

import java.util.Date;

public class Notification {
	// Amount of time to display a notification (milliseconds)
	public static final long LONG    = 8000;
	public static final long DEFAULT = 5000;
	public static final long SHORT   = 3000;
	
	private NotificationLevel _level;
	private String _content;
	private long _timeToHide;
	
	public Notification( NotificationLevel level, String content, long timeToHide ) {
		_level = level;
		_content = content;
		_timeToHide = timeToHide;
	}
	
	public Notification( NotificationLevel level, String content ) {
		this( level, content, new Date().getTime() + DEFAULT );
	}
	
	public NotificationLevel getLevel(){ return _level; }
	public String getContent(){ return _content; }
	public boolean shouldHide() {
		return new Date().getTime() >= _timeToHide;
	}
}