package ddob.game.board;

public class Board {
	public static final int WIDTH = 50;   // max X
	public static final int HEIGHT = 50;  // max Y
	
	private Cell[][] _cells;
	
	public Board() {
		_cells = new Cell[ HEIGHT ][ WIDTH ];
	}
	
	public Cell get( int x, int y ) {
		if( x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT ) {
			return null;
		}
		return _cells[ y ][ x ];
	}
}