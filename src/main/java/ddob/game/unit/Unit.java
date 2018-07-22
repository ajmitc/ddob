package ddob.game.unit;

import java.util.List;

public class Unit {
	public static final String LANDING_BOX_1st_DIVISION = "1st";
	public static final String LANDING_BOX_29th_DIVISION = "29th";

	private Allegiance _allegiance;
	private String _designation;
	private UnitType _type;
	private UnitSymbol _symbol;
	private List<UnitState> _states;
	private int _unitStateIndex;
	private int _landingTurn;
	private String _landingBox;

	private boolean _disrupted;

	private DepthMarker _depthMarker;
	
	public Unit( Allegiance allegiance, UnitType type, String designation, UnitSymbol symbol ) {
		_allegiance = allegiance;
		_type = type;
		_symbol = symbol;
		_designation = designation;
		_unitStateIndex = 0;
		_landingTurn = 0;
		_landingBox = null;
		_disrupted = false;
		_depthMarker = null;
	}
	
	public void addState( UnitState state ) {
		_states.add( state );
	}
	
	public Allegiance getAllegiance(){ return _allegiance; }
	public UnitType getType(){ return _type; }

    public UnitSymbol getSymbol() {
        return _symbol;
    }

    public String getDesignation(){ return _designation; }
	public List<UnitState> getStates(){ return _states; }
	public UnitState getState(){ return _states.get( _unitStateIndex ); }
	public int getLandingTurn(){ return _landingTurn; }
	public String getLandingBox(){ return _landingBox; }
	public boolean isDisrupted(){ return _disrupted; }

	public void setDisrupted( boolean v ){ _disrupted = v; }

    public DepthMarker getDepthMarker() {
        return _depthMarker;
    }

    public void setDepthMarker( DepthMarker depthMarker ) {
        _depthMarker = depthMarker;
    }

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