package ddob.game.board;

public class Cell {
	private int _x;
	private int _y;
	
	private CellType _type;
	
	public Cell( int x, int y, CellType type ) {
		_x = x;
		_y = y;
		_type = type;
	}
	
	public int getX(){ return _x; }
	public int getY(){ return _y; }
	public CellType getType(){ return _type; }
}