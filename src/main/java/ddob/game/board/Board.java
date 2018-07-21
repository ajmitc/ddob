package ddob.game.board;

import ddob.game.Tide;

public class Board {
	public static final int WIDTH = 50;   // max X
	public static final int HEIGHT = 50;  // max Y
	
	private Cell[][] _cells;
	private Cell[] _landingBoxes;
	
	public Board() {
		_cells = new Cell[ HEIGHT ][ WIDTH ];
		_landingBoxes = new Cell[ WIDTH ];
	}
	
	public Cell get( int x, int y ) {
		if( x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT ) {
			return null;
		}
		return _cells[ y ][ x ];
	}

	public Cell getLandingBox( String name ) {
		for( Cell cell: _landingBoxes ) {
			if( cell.getCode().equals( name ) ) {
				return cell;
			}
		}
		return null;
	}

	public Cell[] getLandingBoxes(){ return _landingBoxes; }

	/**
	 * Get the Beach Hex that unit in the given landing box will land on
	 * @param landingBox
	 * @return
	 */
	public Cell getLandingBoxBeachHex( Cell landingBox, Tide tide ) {
		// TODO implement me
		return null;
	}
}