package ddob.game.phase;

import ddob.game.Phase;

public abstract class GermanAttackPhase extends Phase {
    public static final int DRAW_CARD = 0;
    public static final int ATTACK1 = 1;
    public static final int ATTACK1_PLAYER_INPUT = 2;
    public static final int ATTACK2 = 3;
    public static final int ATTACK2_PLAYER_INPUT = 4;
    public static final int ATTACK3 = 5;
    public static final int ATTACK3_PLAYER_INPUT = 6;
    public static final int REMOVE_DISRUPTION = 7;
    public static final int END_PHASE = 8;
    public static final int MAX_PROGRESS = 9;

    public GermanAttackPhase(String name ) {
        super( name );
    }

    public void incProgress() {
        _progress = (_progress + 1) % MAX_PROGRESS;
    }
}
