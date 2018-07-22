package ddob.game.unit;

import java.util.List;
import java.util.ArrayList;

import java.awt.image.BufferedImage;

/**
 * Unit State represents a single side of a Unit token (minus the allegiance and designation).
 * This includes strength, weapons, etc.
 */
public class UnitState {
	private int _strength;
	private List<Weapon> _weapons;
	private BufferedImage _image;
	
	public UnitState( BufferedImage image, int strength, Weapon[] weapons ) {
		_image = image;
		_strength = strength;
		_weapons = new ArrayList<>();
		for( Weapon w: weapons ) {
			_weapons.add( w );
		}
	}
	
	public BufferedImage getImage(){ return _image; }
	public int getStrength(){ return _strength; }
	public List<Weapon> getWeapons(){ return _weapons; }
}