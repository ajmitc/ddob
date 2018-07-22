package ddob.game.unit;

import java.util.ArrayList;
import java.util.List;

public class DepthMarker {
    public static final int STRENGTH = 0;

    private DepthMarkerType _type;

    private int _strength;
    private List<Weapon> _weapons;

    public DepthMarker( DepthMarkerType type, int effect, int strength, Weapon[] weapons ) {
        _type = type;
        _strength = 0;
        _weapons = new ArrayList<>();
        for( Weapon weapon: weapons ) {
            _weapons.add( weapon );
        }
    }
}
