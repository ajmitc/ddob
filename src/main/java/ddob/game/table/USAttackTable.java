package ddob.game.table;

import ddob.game.Unit;
import ddob.game.card.Card;

public class USAttackTable {

    public static AttackResult get(int turn, Unit unit, Unit target) {
        return AttackResult.NO_EFFECT;
    }

    private USAttackTable(){}
}