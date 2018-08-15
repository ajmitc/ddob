package ddob.game.phase;

import ddob.game.Phase;
import ddob.game.board.BoardListener;
import ddob.game.board.Cell;
import ddob.game.unit.USUnit;
import ddob.game.board.Sector;

import java.util.ArrayList;
import java.util.List;

public abstract class LandingCheckPhase extends Phase implements BoardListener {
    public static final int RESET = 0;
    public static final int DRAW_CARD = 1;
    public static final int APPLY_LANDING_CHECKS = 2;
    public static final int CHECK_MINE_EXPLOSION = 3;
    public static final int LAND_UNITS = 4;
    public static final int PLACE_UNITS_IN_LANDING_BOXES = 5;
    public static final int PLAYER_PLACE_UNITS_IN_LANDING_BOXES = 6;
    public static final int END_PHASE = 7;
    public static final int MAX_PROGRESS = 8;

    private Sector _sector;
    private List<USUnit> _playerPlacementEast;
    private List<USUnit> _playerPlacementWest;
    private boolean _waitForUserSelection;

    private Cell _selectedCell;

    public LandingCheckPhase( String name, Sector sector ) {
        super( name );
        _sector = sector;
        _playerPlacementEast = new ArrayList<>();
        _playerPlacementWest = new ArrayList<>();
        _waitForUserSelection = false;
    }

    public void cellSelected( Cell cell, int x, int y ) {
        if( cell != null && cell.isSelectable() )
            _selectedCell = cell;
    }

    public boolean removeAfterTrigger() { return true; }

    public void incProgress() {
        _progress = (_progress + 1) % MAX_PROGRESS;
    }

    public Sector getSector(){ return _sector; }

    public List<USUnit> getPlayerPlacementEast(){ return _playerPlacementEast; }
    public List<USUnit> getPlayerPlacementWest(){ return _playerPlacementWest; }

    public boolean shouldWaitForUserSelection(){ return _waitForUserSelection; }
    public void setWaitForUserSelection( boolean v ){ _waitForUserSelection = v; }

    public Cell getSelectedCell(){ return _selectedCell; }
    public void setSelectedCell( Cell cell ){ _selectedCell = cell; }
}
