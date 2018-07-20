package ddob.game;

import java.util.List;
import java.util.ArrayList;

public class Unit {
	private Allegiance _allegiance;
	private String _designation;
	private UnitType _type;
	private List<UnitState> _states;
	private int _unitStateIndex;
	
	public Unit( Allegiance allegiance, UnitType type, String designation ) {
		_allegiance = allegiance;
		_type = type;
		_designation = designation;
		_unitStateIndex = 0;
	}
	
	public void addState( UnitState state ) {
		_states.add( state );
	}
	
	public Allegiance getAllegiance(){ return _allegiance; }
	public UnitType getType(){ return _type; }
	public String getDesignation(){ return _designation; }
	public List<UnitState> getStates(){ return _states; }
	public UnitState getState(){ return _states.get( _unitStateIndex ); }
	
	public boolean setStateByIndex( int index ) {
		if( index >= 0 && index < _states.size() ) {
			_unitStateIndex = index;
			return true;
		}
		return false;
	}
	
	public boolean setStateByStrength( int strength ) {
		// TODO Implement me
		return false;
	}
	
	public boolean nextState() {
		return setStateByIndex( _unitStateIndex + 1 );
	}
}