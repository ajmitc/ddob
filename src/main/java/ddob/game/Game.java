package ddob.game;

import ddob.game.card.Deck;

import java.util.List;
import java.util.ArrayList;

public class Game {
	// First Waves, Beyond the Beach, or both
	private GameType _type;
	
	// Reference to current Turn
	private int _currentTurn;
	
	// List of all turns (1-32)
	private List<Turn> _turns;
	
	private Deck _deck;
	
	public Game( GameType type ) {
		_type = type;
		_turns = new ArrayList<>();
		_currentTurn = (_type == GameType.FIRST_WAVES || _type == GameType.EXTENDED)? 0: 16;
		_deck = new Deck();
		_deck.shuffle();
	}
	
	public GameType getType(){ return _type; }

	public Turn getCurrentTurn(){ 
		if( _currentTurn < _turns.size() ) {
			return _turns.get( _currentTurn );
		}
		return null;
	}
	public List<Turn> getTurns(){ return _turns; }
	
	public Turn nextTurn() {
		_currentTurn += 1;
		return getCurrentTurn();
	}
	
	public Deck getDeck(){ return _deck; }
}