package ddob.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.*;

public class ImageFactory {
    public static final String IMAGE_DIRECTORY = "image";
    public static final String[] EXTENSIONS = { ".png", "jpg" };

    private static Logger logger = Logger.getLogger( ImageFactory.class.getName() );

    private static Map<File, BufferedImage> _images = new HashMap<>();

    public static BufferedImage get( String filename ) {
        File f = new File( IMAGE_DIRECTORY, filename );
        if( f.exists() ) {
            return get( f );
        }
        for( String ext: EXTENSIONS ) {
            f = new File( IMAGE_DIRECTORY, filename + ext );
            if( f.exists() ) {
                return get( f );
            }
        }
        logger.severe( "Unable to find image '" + filename + "'" );
        return null;
    }

    public static BufferedImage get( File file ) {
        if( _images.containsKey( file ) ) {
            return _images.get( file );
        }

        try {
            BufferedImage image = ImageIO.read( file );
            _images.put( file, image );
            return image;
        }
        catch( IOException ioe ) {
            logger.severe( "" + ioe );
        }

        return null;
    }

    public static ImageIcon getIcon( String filename ) {
        BufferedImage image = get( filename );
        if( image != null )
            return new ImageIcon( image );
        return null;
    }

    private ImageFactory(){}
}
