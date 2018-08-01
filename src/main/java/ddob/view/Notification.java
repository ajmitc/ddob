package ddob.view;

import java.util.Date;

public class Notification {
	public static final long DEFAULT_SHOW_DURATION_MILLI = 5000;
	
	private NotificationLevel _level;
	private String _content;
	private long _timeToHide;
	
	public Notification( NotificationLevel level, String content, long timeToHide ) {
		_level = level;
		_content = content;
		_timeToHide = timeToHide;
	}
	
	public Notification( NotificationLevel level, String content ) {
		this( level, content, new Date().getTime() + DEFAULT_SHOW_DURATION_MILLI );
	}
	
	public NotificationLevel getLevel(){ return _level; }
	public String getContent(){ return _content; }
	public boolean shouldHide() {
		return new Date().getTime() >= _timeToHide;
	}
}