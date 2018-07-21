package ddob.game.table;

import ddob.game.Unit;
import ddob.game.card.Card;

public class LandingCheckTable {

    public static LandingCheckResult get(int turn, Unit unit, Card card ) {
        return LandingCheckResult.NO_EFFECT;
    }

    private LandingCheckTable(){}
}