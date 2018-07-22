package ddob.game.table;

import ddob.game.unit.Unit;

public class USAttackTable {

    public static AttackResult get(int turn, Unit unit, Unit target) {
        return AttackResult.NO_EFFECT;
    }

    private USAttackTable(){}
}