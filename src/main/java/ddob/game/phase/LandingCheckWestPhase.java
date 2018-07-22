package ddob.game.phase;

import ddob.game.Phase;
import ddob.game.board.Sector;

public class LandingCheckWestPhase extends LandingCheckPhase {
    public LandingCheckWestPhase() {
        super( Phase.LANDING_CHECK_WEST_PHASE, Sector.WEST );
    }
}
