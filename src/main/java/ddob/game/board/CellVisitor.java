package ddob.game.board;

public abstract class CellVisitor {

    public CellVisitor() {

    }

    /**
     * Visit the given Cell.
     * @param cell
     * @return false if no more cells should be visited, true otherwise
     */
    public abstract boolean visit( Cell cell );
}
