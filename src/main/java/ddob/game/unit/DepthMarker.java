package ddob.game.unit;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DepthMarker {
    private BufferedImage _image;
    private DepthMarkerType _type;
    private DepthMarkerEffect _effect;

    private int _strength;
    private List<Weapon> _weapons;

    private boolean _revealed;

    public DepthMarker( BufferedImage image, DepthMarkerType type, DepthMarkerEffect effect, int strength, Weapon[] weapons ) {
        _image = image;
        _type = type;
        _effect = effect;
        _strength = strength;
        _weapons = new ArrayList<>();
        if( weapons != null ) {
            for( Weapon weapon : weapons ) {
                _weapons.add( weapon );
            }
        }
        _revealed = false;
    }

    public BufferedImage getImage() {
        return _image;
    }

    public void setImage( BufferedImage image ) {
        this._image = image;
    }

    public DepthMarkerType getType() {
        return _type;
    }

    public void setType( DepthMarkerType type ) {
        this._type = type;
    }

    public DepthMarkerEffect getEffect() {
        return _effect;
    }

    public void setEffect( DepthMarkerEffect effect ) {
        _effect = effect;
    }

    public int getStrength() {
        return _strength;
    }

    public void setStrength( int strength ) {
        this._strength = strength;
    }

    public List<Weapon> getWeapons() {
        return _weapons;
    }

    public void setWeapons( List<Weapon> weapons ) {
        this._weapons = weapons;
    }

    public String toString() {
        if( _effect == DepthMarkerEffect.STRENGTH )
            return _type + " DM[Str " + _strength + "; " + _weapons.stream().map( w -> w.toString() ).collect( Collectors.joining(", " ) ) + "]";
        return _type + " DM[" + _effect + "; " + _weapons.stream().map( w -> w.toString() ).collect( Collectors.joining(", " ) ) + "]";
    }

    public boolean isRevealed() {
        return _revealed;
    }

    public void setRevealed( boolean v ) {
        _revealed = v;
    }
}

