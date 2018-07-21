package ddob.game.board;

import ddob.game.Unit;

import java.util.ArrayList;
import java.util.List;

public class Cell {
	private String _code; // Landing Box name, Zone ID, etc.
	private int _x;
	private int _y;
	
	private CellType _type;

	private List<Unit> _units;

	private boolean _cleared;
	
	public Cell( int x, int y, CellType type, String code ) {
		_x = x;
		_y = y;
		_type = type;
		_code = code;
		_units = new ArrayList<>();
		_cleared = false;
	}

	public String getCode(){ return _code; }
	public int getX(){ return _x; }
	public int getY(){ return _y; }
	public CellType getType(){ return _type; }
	public List<Unit> getUnits(){ return _units; }
	public boolean isCleared(){ return _cleared; }
}