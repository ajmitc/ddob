package ddob.game.phase;

import ddob.game.Phase;

public class EngineerPhase extends Phase {
    public static final int RESET_PHASE = 0;
    public static final int PLAYER_ACTIONS = 1;
    public static final int END_PHASE = 2;
    public static final int MAX_PROGRESS = 3;

    public EngineerPhase() {
        super( Phase.ENGINEER_PHASE );
    }

    public void incProgress() {
        _progress = (_progress + 1) % MAX_PROGRESS;
    }
}
