package ddob.game.phase;

import ddob.game.Phase;

public abstract class USActionPhase extends Phase {
    public static final int PLAYER_ACTIONS = 0;
    public static final int END_PHASE = 1;
    public static final int MAX_PROGRESS = 2;

    public USActionPhase( String name ) {
        super( name );
    }

    public void incProgress() {
        _progress = (_progress + 1) % MAX_PROGRESS;
    }
}
