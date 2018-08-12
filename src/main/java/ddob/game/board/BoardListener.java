package ddob.game.board;

public interface BoardListener {
    /**
     * Triggered on a CELL_SELECTED Notification Type
     * @param cell  Selected cell
     * @param x  Mouse X coordinate
     * @param y  Mouse Y coordinate
     */
    void cellSelected( Cell cell, int x, int y );

    /**
     * Should this BoardListener object be removed from the listeners after a method is called?
     * @return true if it should be removed, false if it should remain in the list of listeners
     */
    default boolean removeAfterTrigger(){ return false; }
}
