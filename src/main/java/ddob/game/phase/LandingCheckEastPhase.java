package ddob.game.phase;

import ddob.game.Phase;
import ddob.game.board.Sector;

public class LandingCheckEastPhase extends LandingCheckPhase {
    public LandingCheckEastPhase() {
        super( Phase.LANDING_CHECK_EAST_PHASE, Sector.EAST );
    }
}
