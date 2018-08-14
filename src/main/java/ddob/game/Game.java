package ddob.game;

import ddob.game.board.Board;
import ddob.game.board.Cell;
import ddob.game.card.Deck;
import ddob.game.unit.*;
import ddob.util.ImageFactory;

import java.awt.image.BufferedImage;
import java.util.*;
import java.util.logging.Logger;

public class Game {
    public static final int MID_TIDE_TURN = 7;   // TODO Check this
	public static final int HIGH_TIDE_TURN = 16; // TODO Check this
    public static final int BEYOND_THE_BEACH_START_TURN = 17;

    public Logger _logger = Logger.getLogger( Game.class.getName() );

	// First Waves, Beyond the Beach, or both
	private GameType _type;
	
	// Reference to current Turn
	private int _currentTurnIndex;
	
	// List of all turns (1-32)
	private List<Turn> _turns;
	
	private Deck _deck;

	private Board _board;

	private Map<Division, List<USUnit>> _usUnits;
	private Map<Division, List<String>> _heroFilenames;
	private int _1stHeroIndex;
    private int _29thHeroIndex;
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

        _heroFilenames = new HashMap<>();
        _heroFilenames.put( Division.US_1, new ArrayList<>() );
        _heroFilenames.put( Division.US_29, new ArrayList<>() );

        _heroFilenames.get( Division.US_1 ).add( "1st_Hero_Barret" );
        _heroFilenames.get( Division.US_1 ).add( "1st_Hero_Monteith" );
        _heroFilenames.get( Division.US_1 ).add( "1st_Hero_Pinder" );
        _heroFilenames.get( Division.US_1 ).add( "1st_Hero_Spalding" );
        _heroFilenames.get( Division.US_1 ).add( "1st_Hero_Strojny" );

        _heroFilenames.get( Division.US_29 ).add( "29th_Hero_Bingham" );
        _heroFilenames.get( Division.US_29 ).add( "29th_Hero_Morse" );
        _heroFilenames.get( Division.US_29 ).add( "29th_Hero_Schwartz" );
        _heroFilenames.get( Division.US_29 ).add( "29th_Hero_Smith" );
        _heroFilenames.get( Division.US_29 ).add( "29th_Hero_Thompson" );

        _1stHeroIndex = 0;
        _29thHeroIndex = 0;

        _logger.info( "Loading US Units" );
        _usUnits = new HashMap<>();
        _usUnits.put( Division.US_1,  UnitFactory.loadUS1stDivisionUnits() );
        _usUnits.put( Division.US_29, UnitFactory.loadUS29thDivisionUnits() );

        _logger.info( "Loading German Units" );
        _germanWNs = UnitFactory.loadGermanWNs();
        _germanReinforcements = new HashMap<>();
        _germanReinforcements.put( GermanReinforcementType.TACTICAL,         UnitFactory.getGermanTacticalReinforcements() );
        _germanReinforcements.put( GermanReinforcementType.DIVISIONAL,       UnitFactory.getGermanDivisionReinforcements() );
        _germanReinforcements.put( GermanReinforcementType.KAMPGRUPPE_MEYER, UnitFactory.getGermanKMReinforcements() );
        _germanDepthMarkers = UnitFactory.getDepthMarkers();


        // Setup turns
        _logger.info( "Setting up Turns" );
        for( int i = 0; i < 32; ++i ) {
            Tide tide = Tide.LOW_TIDE;
            if( i >= MID_TIDE_TURN - 1 && i < HIGH_TIDE_TURN - 1 ) { // Turn 7-15
                tide = Tide.MID_TIDE;
            }
            else if( i >= HIGH_TIDE_TURN - 1 ) {  // Turn 16-32
                tide = Tide.HIGH_TIDE;
            }

            boolean shuffle = (i == 5 || i == 12 || i == 19 || i == 26); // Turns 6, 13, 20, 27
            Turn turn = new Turn( i + 1, tide, shuffle );
            _turns.add( turn );
        }

        // Extended and Beyond the Beach start at Turn 17
        _currentTurnIndex = (_type == GameType.FIRST_WAVES? 0: 16);

        // Place US Units in turn spaces
        _logger.info( "Placing US Units on Turn Track" );
        for( Division division: _usUnits.keySet() ) {
            for( USUnit unit : _usUnits.get( division ) ) {
                int arrivalTurn = unit.getArrivalTurn();
                if( arrivalTurn > 0 ) {
                    _turns.get( arrivalTurn - 1 ).getArrivingUnits().add( unit );
                }
            }
        }

        // Place starting units in landing boxes
        _logger.info( "Placing US Units in Landing Boxes" );
        if( _type == GameType.FIRST_WAVES || _type == GameType.EXTENDED ) {
            for( Division division: _usUnits.keySet() ) {
                for( USUnit unit : _usUnits.get( division ) ) {
                    int arrivalTurn = unit.getArrivalTurn();
                    if( arrivalTurn == 0 ) {
                        List<Cell> landingBoxes = _board.getLandingBox( unit.getLandingBox() );
                        if( landingBoxes.size() == 1 ) {
                            landingBoxes.get( 0 ).getUnits().add( unit );
                            _logger.info( "   Placed " + unit + " in " + landingBoxes.get( 0 ).getCode() );
                        }
                        else {
                            _logger.warning( "   Landing Box '" + unit.getLandingBox() + "' returned " + landingBoxes.size() + " results, not placing..." );
                        }
                    }
                }
            }
        }
        // TODO Implement Beyond the Beach
        else {

        }

        // Place German starting units
        Collections.shuffle( _germanWNs );
        if( _type == GameType.FIRST_WAVES || _type == GameType.EXTENDED ) {
            // Shuffle the 18 WN units and place
            // - rocket on N 69
            addWN( UnitType.WN_ROCKET, "WN 69" );
            // - two 88 on WN 61 and WN 72S
            addWN( UnitType.WN_ARTILLERY_88, "WN 61" );
            addWN( UnitType.WN_ARTILLERY_88, "WN 72S" );
            // - six units with artillery on WN 60, 62S, 65S, 68S, 70, 73
            addArtilleryWN( "WN 60" );
            addArtilleryWN( "WN 62S" );
            addArtilleryWN( "WN 65S" );
            addArtilleryWN( "WN 68S" );
            addArtilleryWN( "WN 70" );
            addArtilleryWN( "WN 73" );
            // - remaining nine units on other WN positions
            String[] wnPositions = { "WN 62N", "WN 64", "WN 65N", "WN 66S", "WN 66N", "WN 67", "WN 68N", "WN 71", "WN 72N" };
            for( String wnPosition: wnPositions ) {
                GermanUnit wn = _germanWNs.remove( 0 );
                Cell cell = _board.getByCode( wnPosition );
                if( cell == null ) {
                    _logger.severe( "Failed to find cell with code '" + wnPosition + "'" );
                    return;
                }
                cell.getUnits().add( wn );
            }

            // Add WN depth
            List<DepthMarker> wnDepthMarkers = _germanDepthMarkers.get( DepthMarkerType.WN );
            Collections.shuffle( wnDepthMarkers );
            String[] wnDepthPositions = { "WN 60", "WN 61", "WN 62N", "WN 65N", "WN 66N", "WN 68N", "WN 70", "WN 72N", "WN 73" };
            for( String wnDepthPosition: wnDepthPositions ) {
                Cell cell = _board.getByCode( wnDepthPosition );
                if( cell == null ) {
                    _logger.severe( "Failed to find cell with code '" + wnDepthPosition + "'" );
                    return;
                }
                if( cell.getUnits().size() == 0 ) {
                    _logger.severe( "Attempting to add depth marker to cell " + cell + " without a unit!" );
                    return;
                }
                DepthMarker marker = wnDepthMarkers.remove( 0 );
                ((GermanUnit) cell.getUnits().get( 0 )).setDepthMarker( marker );
                _logger.info( "Added Depth Marker " + marker + " to " + cell.getUnits().get( 0 ) );
            }
        }
        // TODO Implement Beyond the Beach
        else {

        }

        // Shuffle remaining markers
        Collections.shuffle( _germanReinforcements.get( GermanReinforcementType.TACTICAL ) );
        Collections.shuffle( _germanReinforcements.get( GermanReinforcementType.DIVISIONAL ) );
        Collections.shuffle( _germanReinforcements.get( GermanReinforcementType.KAMPGRUPPE_MEYER ) );
        Collections.shuffle( _germanDepthMarkers.get( DepthMarkerType.MOBILE ) );
        Collections.shuffle( _germanDepthMarkers.get( DepthMarkerType.BUILDING ) );
	}


	private void addWN( UnitType type, String cellCode ) {
        GermanUnit wn = popGermanWN( type );
        if( wn == null ) {
            _logger.severe( "Failed to get WN of type " + type + " from supply" );
            return;
        }
        Cell cell = _board.getByCode( cellCode );
        if( cell == null ) {
            _logger.severe( "Failed to find cell with code '" + cellCode + "'" );
            return;
        }
        cell.getUnits().add( wn );
    }

    private void addArtilleryWN( String cellCode ) {
	    GermanUnit wn = null;
	    for( GermanUnit unit: _germanWNs ) {
	        if( unit.getType().isArtillery() ) {
	            wn = unit;
	            break;
            }
        }
        if( wn == null ) {
            _logger.severe( "Failed to get artillery WN from supply" );
            return;
        }
        _germanWNs.remove( wn );
        Cell cell = _board.getByCode( cellCode );
        if( cell == null ) {
            _logger.severe( "Failed to find cell with code '" + cellCode + "'" );
            return;
        }
        cell.getUnits().add( wn );
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

	public BufferedImage getHero( Division div ) {
	    int i = div == Division.US_1? _1stHeroIndex: _29thHeroIndex;
	    BufferedImage ret = ImageFactory.get( _heroFilenames.get( div ).get( i ) );
        if( div == Division.US_1 ) {
            _1stHeroIndex = (_1stHeroIndex + 1) % _heroFilenames.get( div ).size();
        }
        else {
            _29thHeroIndex = (_29thHeroIndex + 1) % _heroFilenames.get( div ).size();
        }
        return ret;
    }

    public BufferedImage getBuildingDepthMarkerBack() {
	    return ImageFactory.get( "Ger_Depth_Bui_Back" );
    }

    public BufferedImage getMobileDepthMarkerBack() {
        return ImageFactory.get( "Ger_Depth_Mob_Back" );
    }

    public BufferedImage getWNDepthMarkerBack() {
        return ImageFactory.get( "Ger_Depth_WN_Back" );
    }

    public BufferedImage getGermanReinforcementBack() {
        return ImageFactory.get( "Ger_Reinf_Back" );
    }

    public BufferedImage getGermanWNRocketBack() {
        return ImageFactory.get( "Ger_WN_1Ro_Back" );
    }

    public BufferedImage getGermanWNArtillery75Back() {
        return ImageFactory.get( "Ger_WN_75_Back" );
    }

    public BufferedImage getGermanWNArtillery88Back() {
        return ImageFactory.get( "Ger_WN_88_Back" );
    }

    public BufferedImage getGermanWNBack() {
        return ImageFactory.get( "Ger_WN_Back" );
    }

    public BufferedImage getGermanUnitBack( UnitType type ) {
        switch( type ) {
            case WN:
                return getGermanWNBack();
            case WN_ARTILLERY_75:
                return getGermanWNArtillery75Back();
            case WN_ARTILLERY_88:
                return getGermanWNArtillery88Back();
            case WN_ROCKET:
                return getGermanWNRocketBack();
            case INFANTRY:
                return getGermanReinforcementBack();
        }
        return null;
    }

    public BufferedImage getDepthMarkerBack( DepthMarkerType type ) {
        switch( type ) {
            case WN:
                return getWNDepthMarkerBack();
            case MOBILE:
                return getMobileDepthMarkerBack();
            case BUILDING:
                return getBuildingDepthMarkerBack();
        }
        return null;
    }

    public GermanUnit popGermanWN( UnitType type ) {
	    GermanUnit u = null;
	    for( GermanUnit unit: _germanWNs ) {
	        if( unit.getType() == type ) {
                u = unit;
                break;
            }
        }
        if( u != null )
            _germanWNs.remove( u );
        return u;
    }
}

