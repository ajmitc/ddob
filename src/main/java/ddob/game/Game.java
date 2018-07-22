package ddob.game;

import ddob.game.board.Board;
import ddob.game.card.Deck;

import java.util.List;
import java.util.ArrayList;

public class Game {
    public static final int MID_TIDE_TURN = 7;   // TODO Check this
	public static final int HIGH_TIDE_TURN = 10; // TODO Check this
    public static final int BEYOND_THE_BEACH_START_TURN = 17;

	// First Waves, Beyond the Beach, or both
	private GameType _type;
	
	// Reference to current Turn
	private int _currentTurn;
	
	// List of all turns (1-32)
	private List<Turn> _turns;
	
	private Deck _deck;

	private Board _board;
	
	public Game( GameType type ) {
		_type = type;
		_turns = new ArrayList<>();
		_currentTurn = 0;
		_deck = new Deck();
		_deck.shuffle();
		_board = new Board();

		if( _type == GameType.FIRST_WAVES || _type == GameType.EXTENDED ) {
			initTurn0( _type == GameType.EXTENDED );
		}
		else {
			initTurn16();
		}
	}

	private void initTurn0( boolean extended ) {
		_currentTurn = 0;
		for( int i = 0; i < (extended? 32: 16); ++i ) {
			Tide tide = Tide.LOW_TIDE;
			Turn turn = new Turn( i + 1, tide, false );
			_turns.add( turn );
		}
	}

	private void initTurn16() {
		_currentTurn = 16;
		for( int i = 16; i < 32; ++i ) {
			Turn turn = new Turn( i + 1, Tide.HIGH_TIDE, false );
			_turns.add( turn );
		}
	}

	public GameType getType(){ return _type; }

	public Turn getCurrentTurn(){ 
		if( _currentTurn < _turns.size() ) {
			return _turns.get( _currentTurn );
		}
		return null;
	}

	public Turn getFutureTurn( int delta ) {
		if( _currentTurn + delta < _turns.size() ) {
			return _turns.get( _currentTurn + delta );
		}
		return null;
	}

	public List<Turn> getTurns(){ return _turns; }
	
	public Turn nextTurn() {
		_currentTurn += 1;
		return getCurrentTurn();
	}
	
	public Deck getDeck(){ return _deck; }

	public Board getBoard() {
		return _board;
	}
}