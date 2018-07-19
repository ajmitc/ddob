package ddob.game;

import java.util.List;
import java.util.ArrayList;

public class Turn {
	private int _number;
	private List<Unit> _arrivingUnits;
	private Tide _tide;
	private boolean _shuffleCards;
	
	public Turn( int number, Tide tide, boolean shuffle ) {
		_number = number;
		_tide = tide;
		_shuffleCards = shuffle;
		_arrivingUnits = new ArrayList<>();
	}
	
	public int getNumber(){ return _number; }
	public List<Unit> getArrivingUnits(){ return _arrivingUnits; }
	public Tide getTide(){ return _tide; }
	public boolean shouldShuffleCards(){ return _shuffleCards; }
}