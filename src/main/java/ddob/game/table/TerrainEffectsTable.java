package ddob.game.table;

import ddob.game.board.*;
import ddob.game.unit.GermanDefenseModifier;
import ddob.game.unit.USUnit;
import ddob.game.unit.UnitType;

public class TerrainEffectsTable {

    public static boolean allowUSMovement( USUnit unit, Cell fromCell, Cell toCell, Direction direction ) {
        if( unit.getType() == UnitType.INFANTRY ) {
            return allowUSInfantryMovement( toCell );
        }
        else if( unit.getType() == UnitType.HQ || unit.getType() == UnitType.GENERAL ) {
            return allowUSLeaderMovement( toCell );
        }
        return allowUSOtherMovement( fromCell, toCell, direction );
    }

    public static boolean allowUSMovement( USUnit unit, HexSideType type ) {
        if( unit.getType() == UnitType.INFANTRY ) {
            return allowUSInfantryMovement( type );
        }
        else if( unit.getType() == UnitType.HQ || unit.getType() == UnitType.GENERAL ) {
            return allowUSLeaderMovement( type );
        }
        return allowUSOtherMovement( type );
    }

    public static boolean allowUSInfantryMovement( Cell cell ) {
    	boolean allow = true;
        for( CellType type: cell.getTypes() ) {
            if( type == CellType.WATER || type == CellType.OFF_LIMITS || type == CellType.OFF_LIMITS || type == CellType.LANDING_BOX || type == CellType.ROUGH ) {
					allow = false;
            }
        }
        return allow;
    }

    public static boolean allowUSInfantryMovement( HexSideType type ) {
        boolean allow = true;
        if( type == HexSideType.SHEER_CLIFF ) {
            allow = false;
        }
        return allow;
    }

    public static boolean allowUSLeaderMovement( Cell cell ) {
        return allowUSInfantryMovement( cell );
    }

    public static boolean allowUSLeaderMovement( HexSideType type ) {
        boolean allow = true;
        if( type == HexSideType.SHEER_CLIFF || type == HexSideType.SCALEABLE_CLIFF ) {
            allow = false;
        }
        return allow;
    }

    public static boolean allowUSOtherMovement( Cell fromCell, Cell toCell, Direction direction ) {
        boolean allow = true;
        for( CellType type: toCell.getTypes() ) {
            if( type == CellType.WATER || type == CellType.OFF_LIMITS || type == CellType.OFF_LIMITS || type == CellType.LANDING_BOX || type == CellType.ROUGH ) {
                allow = false;
            }
        }

        if( toCell.hasType( CellType.WOODS ) || toCell.hasType( CellType.BOCAGE ) ) {
            allow = fromCell.getRoad() != null && toCell.getRoad() != null && fromCell.getRoad() == toCell.getRoad() && fromCell.getRoad() != RoadType.MINED_ROAD && toCell.getRoad() != RoadType.MINED_ROAD;
        }

        if( allow && (fromCell.getHexSide( direction ) == HexSideType.HEDGE ||
                      fromCell.getHexSide( direction ) == HexSideType.SEAWALL ||
                      fromCell.getHexSide( direction ) == HexSideType.SLOPE ||
                      fromCell.getHexSide( direction ) == HexSideType.BLUFF) ) {
            allow = fromCell.getRoad() != null && toCell.getRoad() != null && fromCell.getRoad() == toCell.getRoad() && fromCell.getRoad() != RoadType.MINED_ROAD && toCell.getRoad() != RoadType.MINED_ROAD;
        }
        return allow;
    }

    public static boolean allowUSOtherMovement( HexSideType type ) {
        boolean allow = true;
        if( type == HexSideType.SHEER_CLIFF || type == HexSideType.SCALEABLE_CLIFF ) {
            allow = false;
        }
        return allow;
    }



    public static GermanDefenseModifier getGermanDefenseModifier( Cell attackerCell, Cell defenderCell, Direction direction ) {
        if( attackerCell.getHexSide( direction ) == HexSideType.BLUFF || attackerCell.getHexSide( direction ) == HexSideType.SHEER_CLIFF || attackerCell.getHexSide( direction ) == HexSideType.SCALEABLE_CLIFF ) {
            return GermanDefenseModifier.ATTACK_PROHIBITED;
        }

        if( defenderCell.hasType( CellType.BOCAGE ) || defenderCell.hasType( CellType.BUILDINGS ) ) {
            return GermanDefenseModifier.UNIT_AND_DEPTH_STRENGTH_DOUBLED;
        }

        if( attackerCell.getHexSide( direction ) == HexSideType.SLOPE ) {
            return GermanDefenseModifier.UNIT_AND_DEPTH_STRENGTH_DOUBLED;
        }

        if( defenderCell.hasType( CellType.WOODS ) ) {
            return GermanDefenseModifier.UNIT_STRENGTH_DOUBLED;
        }

        if( attackerCell.getHexSide( direction ) == HexSideType.SHINGLE ||
                attackerCell.getHexSide( direction ) == HexSideType.ANTI_TANK_WALL ||
                attackerCell.getHexSide( direction ) == HexSideType.ANTI_TANK_DITCH ) {
            return GermanDefenseModifier.UNIT_STRENGTH_DOUBLED;
        }

        return GermanDefenseModifier.NO_MODIFIER;
    }

    private TerrainEffectsTable(){}
}
