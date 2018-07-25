package ddob.game.unit;

import java.util.List;

public class Unit {
	public static final String LANDING_BOX_1st_DIVISION = "1st";
	public static final String LANDING_BOX_29th_DIVISION = "29th";

	private Allegiance _allegiance;
	private String _designation;
	private UnitType _type;
	private List<UnitState> _states;
	private int _unitStateIndex;

	private boolean _disrupted;

	public Unit( Allegiance allegiance, UnitType type, String designation ) {
		_allegiance = allegiance;
		_type = type;
		_designation = designation;
		_unitStateIndex = 0;
		_disrupted = false;
	}
	
	public void addState( UnitState state ) {
		_states.add( state );
	}
	
	public Allegiance getAllegiance(){ return _allegiance; }
	public UnitType getType(){ return _type; }

    public String getDesignation(){ return _designation; }
	public List<UnitState> getStates(){ return _states; }
	public UnitState getState(){ return _states.get( _unitStateIndex ); }
	public boolean isDisrupted(){ return _disrupted; }

	public void setDisrupted( boolean v ){ _disrupted = v; }

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