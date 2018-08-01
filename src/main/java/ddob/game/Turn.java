package ddob.game;

import ddob.game.phase.*;
import ddob.game.unit.USUnit;
import ddob.game.unit.Unit;

import java.util.List;
import java.util.ArrayList;

public class Turn {
	private int _number;
	private List<USUnit> _arrivingUnits;
	private Tide _tide;
	private boolean _shuffleCards;

	private List<Phase> _phases;
	private int _phaseIndex;
	
	public Turn( int number, Tide tide, boolean shuffle ) {
		_number = number;
		_tide = tide;
		_shuffleCards = shuffle;
		_arrivingUnits = new ArrayList<>();
		_phases = new ArrayList<>( 10 );
		_phaseIndex = 0;

		_phases.add( new LandingCheckEastPhase() );
        _phases.add( new LandingCheckWestPhase() );
		_phases.add( new Event1Phase() );
		_phases.add( new GermanAttackEastPhase() );
		_phases.add( new GermanAttackWestPhase() );
		_phases.add( new Event2Phase() );
		_phases.add( new EngineerPhase() );
		_phases.add( new USActionEastPhase() );
		_phases.add( new USActionWestPhase() );
		_phases.add( new EndTurnPhase() );
	}
	
	public int getNumber(){ return _number; }
	public List<USUnit> getArrivingUnits(){ return _arrivingUnits; }
	public Tide getTide(){ return _tide; }
	public boolean shouldShuffleCards(){ return _shuffleCards; }

	public List<Phase> getPhases(){ return _phases; }
	public Phase getCurrentPhase(){ return _phases.get( _phaseIndex ); }

	public void nextPhase() {
	    _phaseIndex = (_phaseIndex + 1) % _phases.size();
    }
}