package ddob.game.phase;

import ddob.game.Phase;
import ddob.game.Unit;

import java.util.ArrayList;
import java.util.List;

public abstract class LandingCheckPhase extends Phase {
    public static final int PLACE_UNITS_IN_LANDING_BOXES = 0;
    public static final int PLAYER_PLACE_UNITS_IN_LANDING_BOXES = 1;
    public static final int DRAW_CARD = 2;
    public static final int APPLY_LANDING_CHECKS = 3;
    public static final int CHECK_MINE_EXPLOSION = 4;
    public static final int LAND_UNITS = 5;
    public static final int END_PHASE = 6;
    public static final int MAX_PROGRESS = 7;

    private List<Unit> _playerPlacement;

    public LandingCheckPhase( String name ) {
        super( name );
        _playerPlacement = new ArrayList<>();
    }

    public void incProgress() {
        _progress = (_progress + 1) % MAX_PROGRESS;
    }

    public List<Unit> getPlayerPlacement(){ return _playerPlacement; }
}
