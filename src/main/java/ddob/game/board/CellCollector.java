package ddob.game.board;

import java.util.ArrayList;
import java.util.List;

public abstract class CellCollector extends CellVisitor {

    private List<Cell> _cells;

    public CellCollector() {
        _cells = new ArrayList<>();
    }

    /**
     * Visit the given Cell.
     * @param cell
     * @return false if no more cells should be visited, true otherwise
     */
    public boolean visit( Cell cell ) {
        if( shouldCollect( cell ) ) {
            _cells.add( cell );
        }
        return true;
    }

    public abstract boolean shouldCollect( Cell cell );

    public List<Cell> getCells(){ return _cells; }
}
