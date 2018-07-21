package ddob.game.phase;

import ddob.game.Phase;

public abstract class EventPhase extends Phase {
    public static final int DRAW_CARD = 0;
    public static final int END_PHASE = 1;
    public static final int MAX_PROGRESS = 2;

    public EventPhase(String name ) {
        super( name );
    }

    public void incProgress() {
        _progress = (_progress + 1) % MAX_PROGRESS;
    }
}
