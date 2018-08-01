package ddob.game.unit;

import java.util.List;
import java.util.ArrayList;

import java.awt.image.BufferedImage;

/**
 * Unit State represents a single side of a Unit token (minus the allegiance and designation).
 * This includes strength, weapons, etc.
 */
public class UnitState {
	private UnitSymbol _symbol;
	private int _strength;
	private int _range;
	private List<Weapon> _weapons;
	private BufferedImage _image;
	
	public UnitState( BufferedImage image, int strength, int range, UnitSymbol symbol, Weapon[] weapons ) {
		_image = image;
		_symbol = symbol;
		_strength = strength;
		_range = range;
		_weapons = new ArrayList<>();
		if( weapons != null ) {
			for( Weapon w : weapons ) {
				_weapons.add( w );
			}
		}
	}
	
	public BufferedImage getImage(){ return _image; }
	public int getStrength(){ return _strength; }
	public int getRange(){ return _range; }
	public List<Weapon> getWeapons(){ return _weapons; }

    public UnitSymbol getSymbol() {
        return _symbol;
    }
}