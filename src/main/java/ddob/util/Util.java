package ddob.util;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
	
	public static Dimension calculateStringDimension( Graphics graphics, Font font, String text ) {
		// get metrics from the graphics
		FontMetrics metrics = graphics.getFontMetrics( font );
		// get the height of a line of text in this
		// font and render context
		int hgt = metrics.getHeight();
		// get the advance of my text in this font
		// and render context
		int adv = metrics.stringWidth( text );
		// calculate the size of a box to hold the text
		return new Dimension( adv, hgt );
	}
	
	public static Dimension calculateStringDimension( Graphics graphics, Font font, List<String> text, int lineSpacing ) {
		// get metrics from the graphics
		FontMetrics metrics = graphics.getFontMetrics( font );
		// get the height of a line of text in this
		// font and render context
		int hgt = (metrics.getHeight() + lineSpacing) * text.size();
		// get the advance of my text in this font
		// and render context
		int maxAdv = 0;
		for( String s: text ) {
			int adv = metrics.stringWidth( s );
			if( adv > maxAdv ) {
				maxAdv = adv;
			}
		}
		// calculate the size of a box to hold the text
		return new Dimension( maxAdv, hgt );
	}
	
	public static int getFontHeight( Graphics g, Font f ) {
		FontMetrics metrics = g.getFontMetrics( f );
		return metrics.getHeight();
	}
	
	public static List<String> breakString( String s, int max_length ) {
		List<String> parts = new ArrayList<>();
		while( s.length() > max_length ) {
			String part = s.substring( 0, max_length );
			parts.add( part );
			s = s.substring( max_length );
		}
		if( s.length() > 0 ) {
			parts.add( s );	
		}
		return parts;
	}

    private Util() {

    }
}
