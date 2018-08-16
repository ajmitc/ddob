package ddob.game.table;

import ddob.game.card.Card;
import ddob.game.unit.GermanUnit;
import ddob.game.unit.USUnit;
import ddob.game.unit.Unit;
import ddob.game.board.Color;

public class BarrageTable {

    public static BarrageResult get( USUnit unit, GermanUnit target, Color targetColor, Card card ) {
        boolean colorMatch =
                targetColor == card.getGermanAttackColor1() ||
                targetColor == card.getGermanAttackColor2() ||
                targetColor == card.getGermanAttackColor3();
        boolean symbolMatch = target.getState().getSymbol() == card.getUnitSymbol();

        // If nothing matches, miss
        if( !colorMatch && !symbolMatch )
            return BarrageResult.NO_EFFECT;

        if( unit.getState().getStrength() <= 2 ) {
            if( !colorMatch && symbolMatch ) {
                return BarrageResult.NO_EFFECT;
            }
            else if( colorMatch && !symbolMatch ) {
                if( target.getDepthMarker() == null ) {
                    return BarrageResult.DISRUPTED;
                }
                return BarrageResult.NO_EFFECT;
            }
            return BarrageResult.DISRUPTED;
        }
        else if( unit.getState().getStrength() <= 5 ) {
            if( !colorMatch && symbolMatch ) {
                if( target.getDepthMarker() == null ) {
                    return BarrageResult.DISRUPTED;
                }
                return BarrageResult.NO_EFFECT;
            }
            return BarrageResult.DISRUPTED;
        }
        else {
            if( colorMatch && symbolMatch ) {
                return BarrageResult.DISRUPTED_AND_DEPTH_REMOVED;
            }
            return BarrageResult.DISRUPTED;
        }
    }

    private BarrageTable(){}
}

