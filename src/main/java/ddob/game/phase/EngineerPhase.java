package ddob.game.phase;

import ddob.game.Phase;

public class EngineerPhase extends Phase {
    public static final int PLAYER_ACTIONS = 0;
    public static final int END_PHASE = 1;
    public static final int MAX_PROGRESS = 2;

    public EngineerPhase() {
        super( Phase.ENGINEER_PHASE );
    }

    public void incProgress() {
        _progress = (_progress + 1) % MAX_PROGRESS;
    }
}
