package ddob.game;

import ddob.game.board.Board;
import ddob.game.card.Deck;
import ddob.game.unit.*;
import ddob.util.ImageFactory;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Game {
    public static final int MID_TIDE_TURN = 7;   // TODO Check this
	public static final int HIGH_TIDE_TURN = 10; // TODO Check this
    public static final int BEYOND_THE_BEACH_START_TURN = 17;

	// First Waves, Beyond the Beach, or both
	private GameType _type;
	
	// Reference to current Turn
	private int _currentTurnIndex;
	
	// List of all turns (1-32)
	private List<Turn> _turns;
	
	private Deck _deck;

	private Board _board;

	private Map<Division, List<USUnit>> _usUnits;
    private List<GermanUnit> _germanWNs;
    private Map<GermanReinforcementType, List<GermanUnit>> _germanReinforcements;
    private Map<DepthMarkerType, List<DepthMarker>> _germanDepthMarkers;

	public Game( GameType type ) {
		_type = type;
		_turns = new ArrayList<>();
		_currentTurnIndex = 0;
		_deck = new Deck();
		_deck.shuffle();
		_board = new Board();

        _usUnits = new HashMap<>();
        _usUnits.put( Division.US_1,  new ArrayList<>() );
        _usUnits.put( Division.US_29, new ArrayList<>() );

        loadUSUnits();

        _germanWNs = new ArrayList<>();

        _germanReinforcements = new HashMap<>();
        _germanReinforcements.put( GermanReinforcementType.TACTICAL,         new ArrayList<>() );
        _germanReinforcements.put( GermanReinforcementType.DIVISIONAL,       new ArrayList<>() );
        _germanReinforcements.put( GermanReinforcementType.KAMPFGRUPE_MEYER, new ArrayList<>() );

        _germanDepthMarkers = new HashMap<>();
        _germanDepthMarkers.put( DepthMarkerType.WN,       new ArrayList<>() );
        _germanDepthMarkers.put( DepthMarkerType.MOBILE,   new ArrayList<>() );
        _germanDepthMarkers.put( DepthMarkerType.BUILDING, new ArrayList<>() );

        loadGermanUnits();

        // Setup turns
        for( int i = 0; i < 32; ++i ) {
            Tide tide = Tide.LOW_TIDE;
            if( i >= 6 && i < 16 ) { // Turn 7-15
                tide = Tide.MID_TIDE;
            }
            else if( i >= 15 ) {  // Turn 16-32
                tide = Tide.HIGH_TIDE;
            }

            boolean shuffle = (i == 5 || i == 12 || i == 19 || i == 26); // Turns 6, 13, 20, 27
            Turn turn = new Turn( i + 1, tide, shuffle );
            _turns.add( turn );
        }

        // Extended and Beyond the Beach start at Turn 17
        _currentTurnIndex = (_type == GameType.FIRST_WAVES? 0: 16);

        // Place US Units in turn spaces
        for( Division division: _usUnits.keySet() ) {
            for( USUnit unit : _usUnits.get( division ) ) {
                int arrivalTurn = unit.getArrivalTurn();
                _turns.get( arrivalTurn - 1 ).getArrivingUnits().add( unit );
            }
        }
	}

	private void loadUSUnits() {
	    // 1st Division
        USUnit unit = new USUnit( Division.US_1, UnitType.INFANTRY, "A/1/16", 6, "ER" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_A_3" ), 6, 1, UnitSymbol.DIAMOND, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_A_2" ), 4, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BZ, Weapon.BG, Weapon.RD } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_A_1" ), 2, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BZ } ) );
        _usUnits.get( Division.US_1 ).add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "B/1/16", 6, "ER" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_B_3" ), 6, 1, UnitSymbol.TRIANGLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_B_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BZ, Weapon.BR, Weapon.DE } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_B_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.DE } ) );
        _usUnits.get( Division.US_1 ).add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "C/1/16", 6, "ER" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_C_3" ), 6, 1, UnitSymbol.CIRCLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_C_2" ), 4, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BG, Weapon.MO, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_C_1" ), 2, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BG } ) );
        _usUnits.get( Division.US_1 ).add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "D/1/16", 7, "ER" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_D_3" ), 7, 2, UnitSymbol.TRIANGLE, USUnit.HEAVY_INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_D_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.DE, Weapon.MG, Weapon.MO } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_1_D_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.MG } ) );
        _usUnits.get( Division.US_1 ).add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "E/2/16", 1, "ER6" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_E_3" ), 6, 1, UnitSymbol.TRIANGLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_E_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BZ, Weapon.BG, Weapon.RD } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_E_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BG } ) );
        _usUnits.get( Division.US_1 ).add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "F/2/16", 1, "ER3" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_F_3" ), 6, 1, UnitSymbol.TRIANGLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_F_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BZ, Weapon.BR, Weapon.DE } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_F_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BR } ) );
        _usUnits.get( Division.US_1 ).add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "G/2/16", 3, "ER2" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_G_3" ), 6, 1, UnitSymbol.CIRCLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_G_2" ), 4, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BG, Weapon.MO, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_G_1" ), 2, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.MO } ) );
        _usUnits.get( Division.US_1 ).add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "H/2/16", 4, "ER4" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_H_3" ), 7, 2, UnitSymbol.DIAMOND, USUnit.HEAVY_INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_H_2" ), 4, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.DE, Weapon.MG, Weapon.MO } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_2_H_1" ), 2, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.DE } ) );
        _usUnits.get( Division.US_1 ).add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "I/3/16", 1, "FG2" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_I_3" ), 6, 1, UnitSymbol.DIAMOND, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_I_2" ), 4, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BZ, Weapon.BG, Weapon.RD } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_I_1" ), 2, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.RD } ) );
        _usUnits.get( Division.US_1 ).add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "K/3/16", 3, "FG4" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_K_3" ), 6, 1, UnitSymbol.TRIANGLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_K_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BZ, Weapon.BR, Weapon.DE } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_K_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BZ } ) );
        _usUnits.get( Division.US_1 ).add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "L/3/16", 1, "FG5" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_L_3" ), 6, 1, UnitSymbol.CIRCLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_L_2" ), 4, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BG, Weapon.MO, Weapon.BR } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_L_1" ), 2, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.BR } ) );
        _usUnits.get( Division.US_1 ).add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "M/3/16", 4, "FG4" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_M_3" ), 7, 2, UnitSymbol.CIRCLE, USUnit.HEAVY_INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_M_2" ), 4, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.DE, Weapon.MO, Weapon.MG } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_3_M_1" ), 2, 1, UnitSymbol.CIRCLE, new Weapon[]{ Weapon.MO } ) );
        _usUnits.get( Division.US_1 ).add( unit );

        // TODO Check Unit Type
        unit = new USUnit( Division.US_1, UnitType.ANTI_TANK, "AT/16", 9, "ER" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_AT_1" ), 2, 5, UnitSymbol.TRIANGLE, USUnit.ANTI_TANK_WEAPONS ) );
        _usUnits.get( Division.US_1 ).add( unit );

        unit = new USUnit( Division.US_1, UnitType.ARTILLERY_DUKW, "Can/16", 8, "ER" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_Can_2" ), 3, -1, UnitSymbol.CIRCLE, USUnit.ARTILLERY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_Can_1" ), 1, -1, UnitSymbol.TRIANGLE, USUnit.ARTILLERY_WEAPONS ) );
        _usUnits.get( Division.US_1 ).add( unit );

        unit = new USUnit( Division.US_1, UnitType.HQ, "16", 7, "FG" );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_HQ" ), 0, 1, null, new Weapon[]{ Weapon.RD } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_16_HQ_CP" ), 0, 1, null, new Weapon[]{ Weapon.RD } ) );
        _usUnits.get( Division.US_1 ).add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "A/1/18", , 20,  "1st" );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_1_A_3" ), 6, 1, UnitSymbol.DIAMOND, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_1_A_2" ), 4, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BZ, Weapon.BG, Weapon.RD } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_1_A_1" ), 2, 1, UnitSymbol.DIAMOND, new Weapon[]{ Weapon.BZ } ) );
        _usUnits.get( Division.US_1 ).add( unit );

        unit = new USUnit( Division.US_1, UnitType.INFANTRY, "B/1/18", 20,  "1st" );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_1_B_3" ), 6, 1, UnitSymbol.TRIANGLE, USUnit.INFANTRY_WEAPONS ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_1_B_2" ), 4, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.BZ, Weapon.BR, Weapon.DE } ) );
        unit.addState( new UnitState( ImageFactory.get( "1st_18_1_B_1" ), 2, 1, UnitSymbol.TRIANGLE, new Weapon[]{ Weapon.DE } ) );
        _usUnits.get( Division.US_1 ).add( unit );

        // TODO Complete this

        // 29th Division

        // TODO Complete this
    }

    private void loadGermanUnits() {
        GermanUnit unit = new GermanUnit( UnitType.INFANTRY, "1P/352" );
        unit.addState( new UnitState( ImageFactory.get( "Ger_Reinf_D_352_1P" ), 3, 1, new Weapon[]{ Weapon.FL, Weapon.BR } ) );
        _germanReinforcements.get( GermanReinforcementType.DIVISIONAL ).add( unit );

        // TODO Complete this
    }

	public GameType getType(){ return _type; }

	public Turn getCurrentTurn(){ 
		if( _currentTurnIndex < _turns.size() ) {
			return _turns.get( _currentTurnIndex );
		}
		return null;
	}

	public Turn getFutureTurn( int delta ) {
		if( _currentTurnIndex + delta < _turns.size() ) {
			return _turns.get( _currentTurnIndex + delta );
		}
		return null;
	}

	public List<Turn> getTurns(){ return _turns; }
	
	public Turn nextTurn() {
		_currentTurnIndex += 1;
		return getCurrentTurn();
	}
	
	public Deck getDeck(){ return _deck; }

	public Board getBoard() {
		return _board;
	}
}
