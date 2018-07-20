package ddob.game;

import java.util.List;
import java.util.ArrayList;

public class Turn {
	private int _number;
	private List<Unit> _arrivingUnits;
	private Tide _tide;
	private boolean _shuffleCards;

	private List<Phase> _phases;
	private int _phaseIndex;
	
	public Turn( int number, Tide tide, boolean shuffle ) {
		_number = number;
		_tide = tide;
		_shuffleCards = shuffle;
		_arrivingUnits = new ArrayList<>();
		_phases = new ArrayList<>();
		_phaseIndex = 0;
	}
	
	public int getNumber(){ return _number; }
	public List<Unit> getArrivingUnits(){ return _arrivingUnits; }
	public Tide getTide(){ return _tide; }
	public boolean shouldShuffleCards(){ return _shuffleCards; }

	public List<Phase> getPhases(){ return _phases; }
	public Phase getCurrentPhase(){ return _phases.get( _phaseIndex ); }

	public void nextPhase() {
	    _phaseIndex = (_phaseIndex + 1) % _phases.size();
    }
}