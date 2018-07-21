package ddob.game.phase;

import ddob.game.Phase;
import ddob.game.Turn;

public class EndTurnPhase extends Phase {
    public static final int DISCARD_CARDS = 0;
    public static final int SHUFFLE_CHECK = 1;
    public static final int END_PHASE = 2;
    public static final int MAX_PROGRESS = 3;

    public EndTurnPhase() {
        super( Phase.END_TURN_PHASE );
    }

    public void incProgress() {
        _progress = (_progress + 1) % MAX_PROGRESS;
    }
}
