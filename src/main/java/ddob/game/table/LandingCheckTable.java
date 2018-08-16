package ddob.game.table;

import ddob.game.card.LandingCheckColumn;
import ddob.game.unit.Unit;
import ddob.game.card.Card;
import ddob.game.unit.UnitSymbol;
import ddob.game.unit.UnitType;

import java.util.HashMap;
import java.util.Map;

public class LandingCheckTable {

    private static final Map<UnitType, Map<LandingCheckColumn, LandingCheckResult>> _turn1 = new HashMap<>();
    private static final Map<UnitType, Map<LandingCheckColumn, LandingCheckResult>> _turn2_3 = new HashMap<>();
    private static final Map<UnitType, Map<LandingCheckColumn, LandingCheckResult>> _turn4_14 = new HashMap<>();
    private static final Map<UnitType, Map<LandingCheckColumn, LandingCheckResult>> _turn15_32 = new HashMap<>();

    static {
        // Turn 1
        _turn1.put( UnitType.TANK, new HashMap<>() );
        _turn1.get( UnitType.TANK ).put( LandingCheckColumn.A, LandingCheckResult.DELAYED_ONE_TURN );
        _turn1.get( UnitType.TANK ).put( LandingCheckColumn.B, LandingCheckResult.ELIMINATED );
        _turn1.get( UnitType.TANK ).put( LandingCheckColumn.C, LandingCheckResult.LOSE_ONE_STEP );
        _turn1.get( UnitType.TANK ).put( LandingCheckColumn.D, LandingCheckResult.LOSE_ONE_STEP_DRIFT_TWO_EAST );

        // Turn 2-3
        _turn2_3.put( UnitType.TANK, new HashMap<>() );
        _turn2_3.get( UnitType.TANK ).put( LandingCheckColumn.A, LandingCheckResult.NO_EFFECT );
        _turn2_3.get( UnitType.TANK ).put( LandingCheckColumn.B, LandingCheckResult.DRIFT_ONE_BOX_EAST );
        _turn2_3.get( UnitType.TANK ).put( LandingCheckColumn.C, LandingCheckResult.LOSE_ONE_STEP );
        _turn2_3.get( UnitType.TANK ).put( LandingCheckColumn.D, LandingCheckResult.DRIFT_THREE_BOXES_EAST );

        // Except DG and CH beaches
        _turn2_3.put( UnitType.INFANTRY, new HashMap<>() );
        _turn2_3.get( UnitType.INFANTRY ).put( LandingCheckColumn.A, LandingCheckResult.DRIFT_FOUR_BOXES_EAST );
        _turn2_3.get( UnitType.INFANTRY ).put( LandingCheckColumn.B, LandingCheckResult.DRIFT_TWO_BOXES_EAST );
        _turn2_3.get( UnitType.INFANTRY ).put( LandingCheckColumn.C, LandingCheckResult.DRIFT_NINE_BOXES_EAST );
        _turn2_3.get( UnitType.INFANTRY ).put( LandingCheckColumn.D, LandingCheckResult.NO_EFFECT );

        // DG and CH beaches
        _turn2_3.put( UnitType.RANGER_INFANTRY, new HashMap<>() );
        _turn2_3.get( UnitType.RANGER_INFANTRY ).put( LandingCheckColumn.A, LandingCheckResult.DRIFT_FOUR_BOXES_EAST );
        _turn2_3.get( UnitType.RANGER_INFANTRY ).put( LandingCheckColumn.B, LandingCheckResult.DRIFT_TWO_BOXES_EAST );
        _turn2_3.get( UnitType.RANGER_INFANTRY ).put( LandingCheckColumn.C, LandingCheckResult.DRIFT_NINE_BOXES_EAST );
        _turn2_3.get( UnitType.RANGER_INFANTRY ).put( LandingCheckColumn.D, LandingCheckResult.NO_EFFECT );

        // Turn 4-14
        _turn4_14.put( UnitType.INFANTRY, new HashMap<>() );
        _turn4_14.get( UnitType.INFANTRY ).put( LandingCheckColumn.A, LandingCheckResult.NO_EFFECT );
        _turn4_14.get( UnitType.INFANTRY ).put( LandingCheckColumn.B, LandingCheckResult.DRIFT_ONE_BOX_EAST );
        _turn4_14.get( UnitType.INFANTRY ).put( LandingCheckColumn.C, LandingCheckResult.DRIFT_FOUR_BOXES_EAST );
        _turn4_14.get( UnitType.INFANTRY ).put( LandingCheckColumn.D, LandingCheckResult.DRIFT_ONE_BOX_WEST );

        _turn4_14.put( UnitType.RANGER_INFANTRY, new HashMap<>( _turn4_14.get( UnitType.INFANTRY ) ) );

        _turn4_14.put( UnitType.ARTILLERY_DUKW, new HashMap<>() );
        _turn4_14.get( UnitType.ARTILLERY_DUKW ).put( LandingCheckColumn.A, LandingCheckResult.ELIMINATED );
        _turn4_14.get( UnitType.ARTILLERY_DUKW ).put( LandingCheckColumn.B, LandingCheckResult.LOSE_ONE_STEP );
        _turn4_14.get( UnitType.ARTILLERY_DUKW ).put( LandingCheckColumn.C, LandingCheckResult.LOSE_TWO_STEPS );
        _turn4_14.get( UnitType.ARTILLERY_DUKW ).put( LandingCheckColumn.D, LandingCheckResult.NO_EFFECT );

        _turn4_14.put( UnitType.ARTILLERY , new HashMap<>() );
        _turn4_14.get( UnitType.ARTILLERY ).put( LandingCheckColumn.A, LandingCheckResult.DELAYED_THREE_TURNS );
        _turn4_14.get( UnitType.ARTILLERY ).put( LandingCheckColumn.B, LandingCheckResult.DRIFT_TWO_BOXES_EAST );
        _turn4_14.get( UnitType.ARTILLERY ).put( LandingCheckColumn.C, LandingCheckResult.LOSE_ONE_STEP );
        _turn4_14.get( UnitType.ARTILLERY ).put( LandingCheckColumn.D, LandingCheckResult.NO_EFFECT );

        _turn4_14.put( UnitType.ANTI_AIRCRAFT,                new HashMap<>( _turn4_14.get( UnitType.ARTILLERY ) ) );
        _turn4_14.put( UnitType.SELF_PROPELLED_ANTI_AIRCRAFT, new HashMap<>( _turn4_14.get( UnitType.ARTILLERY ) ) );
        _turn4_14.put( UnitType.ANTI_TANK,                    new HashMap<>( _turn4_14.get( UnitType.ARTILLERY ) ) );
        _turn4_14.put( UnitType.SELF_PROPELLED_ARTILLERY,     new HashMap<>( _turn4_14.get( UnitType.ARTILLERY ) ) );

        // Turn 15+
        _turn15_32.put( UnitType.ARTILLERY_DUKW, new HashMap<>() );
        _turn15_32.get( UnitType.ARTILLERY_DUKW ).put( LandingCheckColumn.A, LandingCheckResult.ELIMINATED );
        _turn15_32.get( UnitType.ARTILLERY_DUKW ).put( LandingCheckColumn.B, LandingCheckResult.LOSE_ONE_STEP );
        _turn15_32.get( UnitType.ARTILLERY_DUKW ).put( LandingCheckColumn.C, LandingCheckResult.LOSE_TWO_STEPS );
        _turn15_32.get( UnitType.ARTILLERY_DUKW ).put( LandingCheckColumn.D, LandingCheckResult.NO_EFFECT );

        _turn15_32.put( UnitType.ARTILLERY, new HashMap<>() );
        _turn15_32.get( UnitType.ARTILLERY ).put( LandingCheckColumn.A, LandingCheckResult.DELAYED_THREE_TURNS );
        _turn15_32.get( UnitType.ARTILLERY ).put( LandingCheckColumn.B, LandingCheckResult.ELIMINATED );
        _turn15_32.get( UnitType.ARTILLERY ).put( LandingCheckColumn.C, LandingCheckResult.NO_EFFECT );
        _turn15_32.get( UnitType.ARTILLERY ).put( LandingCheckColumn.D, LandingCheckResult.LOSE_ONE_STEP );

        _turn15_32.put( UnitType.ANTI_AIRCRAFT,                new HashMap<>( _turn15_32.get( UnitType.ARTILLERY ) ) );
        _turn15_32.put( UnitType.SELF_PROPELLED_ANTI_AIRCRAFT, new HashMap<>( _turn15_32.get( UnitType.ARTILLERY ) ) );
        _turn15_32.put( UnitType.ANTI_TANK,                    new HashMap<>( _turn15_32.get( UnitType.ARTILLERY ) ) );
        _turn15_32.put( UnitType.SELF_PROPELLED_ARTILLERY,     new HashMap<>( _turn15_32.get( UnitType.ARTILLERY ) ) );
    }

    public static LandingCheckResult get(int turn, Unit unit, Card card, String landingBeachCode ) {
        if( turn == 1 ) {
            if( _turn1.containsKey( unit.getType() ) ) {
                LandingCheckColumn column = getColumn( unit.getState().getSymbol(), card );
                return _turn1.get( unit.getType() ).get( column );
            }
        }
        else if( turn == 2 || turn == 3 ) {
            UnitType unitType = unit.getType();
            if( unitType == UnitType.INFANTRY && (landingBeachCode.startsWith( "DG" ) || landingBeachCode.startsWith( "CH" )) )
                unitType = UnitType.RANGER_INFANTRY;
            if( _turn2_3.containsKey( unitType ) ) {
                LandingCheckColumn column = getColumn( unit.getState().getSymbol(), card );
                return _turn2_3.get( unitType ).get( column );
            }
        }
        else if( turn >= 4 && turn <= 14 ) {
            UnitType unitType = unit.getType();
            if( _turn4_14.containsKey( unitType ) ) {
                LandingCheckColumn column = getColumn( unit.getState().getSymbol(), card );
                if( unitType == UnitType.RANGER_INFANTRY && column == LandingCheckColumn.A ) {
                    return LandingCheckResult.PLAYER_DRIFT_1_4_BOXES_EAST;
                }
                return _turn4_14.get( unitType ).get( column );
            }
        }
        else {
            UnitType unitType = unit.getType();
            if( _turn15_32.containsKey( unitType ) ) {
                LandingCheckColumn column = getColumn( unit.getState().getSymbol(), card );
                return _turn15_32.get( unitType ).get( column );
            }
        }
        return LandingCheckResult.NO_EFFECT;
    }


    private static LandingCheckColumn getColumn( UnitSymbol unitSymbol, Card card ) {
        LandingCheckColumn circle = card.getCircleLandingCheckColumn();
        LandingCheckColumn diamond = card.getDiamondLandingCheckColumn();
        LandingCheckColumn triangle = card.getTriangleLandingCheckColumn();
        return unitSymbol == UnitSymbol.DIAMOND? diamond: (unitSymbol == UnitSymbol.CIRCLE? circle: triangle);
    }

    private LandingCheckTable(){}
}