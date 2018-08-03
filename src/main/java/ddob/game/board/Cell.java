package ddob.game.board;

import ddob.game.unit.Unit;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cell {
	private String _code; // Landing Box name, Zone ID, etc.
	private int _x;
	private int _y;
	
	private List<CellType> _types;

	private List<Unit> _units;

	// Applies to mid-tide beach cells only
	private boolean _cleared;

	private AttackPositionType _attackPositionType; // null if no attack position
	private Color _attackPositionColor;
	private int _attackPositionVP;
	private List<Point> _attackPositionAdvanceCells;
	private Map<Intensity, List<Point>> _attackPositionFieldOfFire;

	private boolean _selectable;

    public Cell( int x, int y, List<CellType> types, String code ) {
        this( x, y, types, code, null, null, 0, new Point[]{}, new Point[]{}, new Point[]{}, new Point[]{} );
    }


	public Cell( int x, int y, List<CellType> types, String code, AttackPositionType attackPositionType, Color attackPositionColor, int attackPositionVP, Point[] attackPositionAdvanceCells, Point[] intense, Point[] steady, Point[] sporadic  ) {
		_x = x;
		_y = y;
		_types = new ArrayList<>();
		_types.addAll( types );
		_code = code;
		_units = new ArrayList<>();
		_cleared = false;

		_attackPositionType = attackPositionType;
		_attackPositionColor = attackPositionColor;
        _attackPositionVP = attackPositionVP;
        _attackPositionAdvanceCells = new ArrayList<>();
        _attackPositionFieldOfFire = new HashMap<>();
        _attackPositionFieldOfFire.put( Intensity.INTENSE, new ArrayList<>() );
        _attackPositionFieldOfFire.put( Intensity.STEADY, new ArrayList<>() );
        _attackPositionFieldOfFire.put( Intensity.SPORADIC, new ArrayList<>() );

        for( Point p: attackPositionAdvanceCells )
            _attackPositionAdvanceCells.add( p );
        for( Point p: intense )
            _attackPositionFieldOfFire.get( Intensity.INTENSE ).add( p );
        for( Point p: steady )
            _attackPositionFieldOfFire.get( Intensity.STEADY ).add( p );
        for( Point p: sporadic )
            _attackPositionFieldOfFire.get( Intensity.SPORADIC ).add( p );

        _selectable = true;
	}

	public String getCode(){ return _code; }
	public int getX(){ return _x; }
	public int getY(){ return _y; }
	public List<CellType> getTypes(){ return _types; }

	public boolean hasType( CellType type ) {
        return _types.contains( type );
    }

	public List<Unit> getUnits(){ return _units; }
	public boolean isCleared(){ return _cleared; }

    public boolean hasAttackPosition() {
        return _attackPositionType != null;
    }

    public AttackPositionType getAttackPositionType() {
        return _attackPositionType;
    }

    public Color getAttackPositionColor() {
        return _attackPositionColor;
    }

    public int getAttackPositionVP() {
        return _attackPositionVP;
    }

    public List<Point> getAttackPositionAdvanceCells() {
        return _attackPositionAdvanceCells;
    }

    public Map<Intensity, List<Point>> getAttackPositionFieldOfFire() {
        return _attackPositionFieldOfFire;
    }

    public boolean isSelectable(){ return _selectable; }
    public void setSelectable( boolean s ){ _selectable = s; }
}
