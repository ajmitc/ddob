package ddob.game.phase;

import ddob.game.Phase;
import ddob.game.Unit;

import java.util.ArrayList;
import java.util.List;

public abstract class GermanAttackPhase extends Phase {
    public static final int DRAW_CARD = 0;
    public static final int ATTACK = 1;
    public static final int REMOVE_DISRUPTION = 2;
    public static final int END_PHASE = 3;
    public static final int MAX_PROGRESS = 4;

    public GermanAttackPhase(String name ) {
        super( name );
    }

    public void incProgress() {
        _progress = (_progress + 1) % MAX_PROGRESS;
    }
}
