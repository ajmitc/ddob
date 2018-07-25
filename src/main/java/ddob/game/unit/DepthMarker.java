package ddob.game.unit;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class DepthMarker {
    public static final int STRENGTH = 0;
    public static final int TACTICAL_REINFORCEMENT = 1;

    private BufferedImage _image;
    private DepthMarkerType _type;

    private int _strength;
    private List<Weapon> _weapons;

    public DepthMarker( BufferedImage image, DepthMarkerType type, int effect, int strength, Weapon[] weapons ) {
        _image = image;
        _type = type;
        _strength = 0;
        _weapons = new ArrayList<>();
        for( Weapon weapon: weapons ) {
            _weapons.add( weapon );
        }
    }
}
