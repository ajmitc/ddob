package ddob.game;

import java.util.List;
import java.util.ArrayList;

/**
 * Unit State represents a single side of a Unit token (minus the allegiance and designation).
 * This includes strength, weapons, etc.
 */
public class UnitState {
	private int _strength;
	private List<Weapon> _weapons;
	
	public UnitState( int strength, Weapon[] weapons ) {
		_strength = strength;
		_weapons = new ArrayList<>();
		for( Weapon w: weapons ) {
			_weapons.add( w );
		}
	}
	
	public int getStrength(){ return _strength; }
	public List<Weapon> getWeapons(){ return _weapons; }
}