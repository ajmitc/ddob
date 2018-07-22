package ddob.game.board;

import ddob.game.Tide;

import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
	public static final int WIDTH = 36;   // max X (odd rows; even rows = 35)
	public static final int HEIGHT = 20;  // max Y

    public static final int SECTOR_BOUNDARY_EVEN_ROWS = 17; // index of last hex in east sector (even rows)
	public static final int SECTOR_BOUNDARY_ODD_ROWS = 18;  // index of last hex in east sector (odd rows)

    public static final Map<Point, Map<Tide, Point>> LANDING_BOX_DESTINATIONS = new HashMap<>();
    static {
        // "FG1"
        addLandingBoxDest( "0407", "0508", "0608", "0709" );
        // TODO Finish me
    }

    private static void addLandingBoxDest( String lb, String lt, String mt, String ht ) {
        int lby = Integer.decode( lb.substring( 0, 2 ) ) - 1;
        int lbx = Integer.decode( lb.substring( 2, 4 ) ) - 1;
        int lty = Integer.decode( lt.substring( 0, 2 ) ) - 1;
        int ltx = Integer.decode( lt.substring( 2, 4 ) ) - 1;
        int mty = Integer.decode( mt.substring( 0, 2 ) ) - 1;
        int mtx = Integer.decode( mt.substring( 2, 4 ) ) - 1;
        int hty = Integer.decode( ht.substring( 0, 2 ) ) - 1;
        int htx = Integer.decode( ht.substring( 2, 4 ) ) - 1;
        Point p = new Point( lbx, lby );
        LANDING_BOX_DESTINATIONS.put( p, new HashMap<>() );
        LANDING_BOX_DESTINATIONS.get( p ).put( Tide.LOW_TIDE,  new Point( ltx, lty ) );
        LANDING_BOX_DESTINATIONS.get( p ).put( Tide.MID_TIDE,  new Point( mtx, mty ) );
        LANDING_BOX_DESTINATIONS.get( p ).put( Tide.HIGH_TIDE, new Point( htx, hty ) );
    }

	private List<Cell> _cells;

	public Board() {
		_cells = new ArrayList<>();
	}

    /**
     * Get cell by index
     * @param x
     * @param y
     * @return
     */
	public Cell get( int x, int y ) {
		if( x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT ) {
			return null;
		}
		// jump ahead to speed up search
		int index = Math.max( (y - 1) * 35, 0 ) + x;
		for( ; index < _cells.size(); ++index ) {
            Cell cell = _cells.get( index );
            if( cell.getX() == x && cell.getY() == y )
                return cell;
        }
		return null;
	}

    /**
     * Return all cells
     * @return
     */
	public List<Cell> getCells(){ return _cells; }

    /**
     * Get a Cell using the coordinate printed on the map.
     * For example, coord = "0709" will return Cell at index [8, 6]
     * @param coord
     * @return
     */
    public Cell getByMapCoord( String coord ) {
	    int y = Integer.decode( coord.substring( 0, 2 ) ) - 1;
	    int x = Integer.decode( coord.substring( 2, 4 ) ) - 1;
        return get( x, y );
    }

    /**
     * Visit all cells in the map
     * @param visitor
     */
	public void visitCells( CellVisitor visitor ) {
		for( Cell cell: _cells ) {
            if( !visitor.visit( cell ) )
                break;
		}
	}

    /**
     * Visit all cells in a sector
     * @param sector
     * @param visitor
     */
	public void visitCells( Sector sector, CellVisitor visitor ) {
		int x = sector == Sector.EAST? 0: SECTOR_BOUNDARY_ODD_ROWS + 1;
		int maxXEven = sector == Sector.EAST? SECTOR_BOUNDARY_EVEN_ROWS: WIDTH - 1;
		int maxXOdd = sector == Sector.EAST? SECTOR_BOUNDARY_ODD_ROWS: WIDTH;
		boolean cont = true;
		for( Cell cell: _cells ) {
		    boolean visit = false;
		    if( sector == Sector.EAST ) {
                if( cell.getY() % 2 == 0 && cell.getX() <= SECTOR_BOUNDARY_EVEN_ROWS ) {
                    visit = true;
                }
                else if( cell.getY() % 2 == 1 && cell.getX() <= SECTOR_BOUNDARY_ODD_ROWS ) {
                    visit = true;
                }
            }
            else if( sector == Sector.WEST ) {
                if( cell.getY() % 2 == 0 && cell.getX() > SECTOR_BOUNDARY_EVEN_ROWS ) {
                    visit = true;
                }
                else if( cell.getY() % 2 == 1 && cell.getX() > SECTOR_BOUNDARY_ODD_ROWS ) {
                    visit = true;
                }
            }
            if( visit ) {
                if( !visitor.visit( cell ) )
                    break;
            }
        }
	}

    /**
     * Get the landing box with the given code/name
     * @param name
     * @return
     */
	public Cell getLandingBox( String name ) {
	    for( Cell cell: _cells ) {
	        if( cell.getType() == CellType.LANDING_BOX && cell.getCode().equals( name ) ) {
	            return cell;
            }
            if( cell.getY() >= 4 )
                break;
        }
        return null;
	}

    /**
     * Get all landing boxes
     * @return
     */
	public List<Cell> getLandingBoxes(){
	    List<Cell> landingBoxes = new ArrayList<>();
        for( Cell cell: _cells ) {
            if( cell.getType() == CellType.LANDING_BOX ) {
                landingBoxes.add( cell );
            }
            if( cell.getY() >= 4 )
                break;
        }
	    return landingBoxes;
	}

    /**
     * Get the landing boxes in the given sector
     * @param sector
     * @return
     */
    public List<Cell> getLandingBoxes( Sector sector ){
        List<Cell> landingBoxes = new ArrayList<>();
        for( Cell cell: _cells ) {
            if( cell.getType() == CellType.LANDING_BOX ) {
                if( sector == Sector.EAST ) {
                    if( cell.getY() % 2 == 0 && cell.getX() <= SECTOR_BOUNDARY_EVEN_ROWS ) {
                        landingBoxes.add( cell );
                    }
                    else if( cell.getY() % 2 == 1 && cell.getX() <= SECTOR_BOUNDARY_ODD_ROWS ) {
                        landingBoxes.add( cell );
                    }
                }
                else {
                    if( cell.getY() % 2 == 0 && cell.getX() > SECTOR_BOUNDARY_EVEN_ROWS ) {
                        landingBoxes.add( cell );
                    }
                    else if( cell.getY() % 2 == 1 && cell.getX() > SECTOR_BOUNDARY_ODD_ROWS ) {
                        landingBoxes.add( cell );
                    }
                }
            }
            if( cell.getY() >= 4 )
                break;
        }
        return landingBoxes;
	}


    /**
     * Get the landing box that is vector hexes away from the origin cell.
     * @param origin Reference point cell
     * @param vector direction and distance from origin
     * @return Cell
     */
	public Cell getRelativeLandingBox( Cell origin, int vector ) {
        // In most cases, the landing box will be in the same row
        int x = origin.getX() + vector;
        Cell target = get( x, origin.getY() );
        if( target == null )
            return null; // off the map
        if( target.getType() == CellType.LANDING_BOX )
            return target;
        // OK, the landing box is not in the same row
        if( vector < 0 ) {
            int rowdelta = 1;
            // Moving left, check next row up.  If the next row up is odd, add 1 to X
            while( true ) {
                int y = origin.getY() + rowdelta;
                if( y >= 4 )
                    return null;  // Nope, didn't find it.
                x = origin.getX() + vector;
                if( y % 2 == 0 )
                    x += 1;
                target = get( x, y );
                if( target == null )
                    return null; // off the map
                if( target.getType() == CellType.LANDING_BOX )
                    return target;
                rowdelta += 1;
            }
        }
        else {
            // Moving right, check next row down
            int rowdelta = 1;
            // Moving left, check next row up.  If the next row up is odd, add 1 to X
            while( true ) {
                int y = origin.getY() - rowdelta;
                if( y < 0 )
                    return null;  // Nope, didn't find it.
                x = origin.getX() + vector;
                if( y % 2 == 1 )
                    x -= 1;
                target = get( x, y );
                if( target == null )
                    return null; // off the map
                if( target.getType() == CellType.LANDING_BOX )
                    return target;
                rowdelta += 1;
            }
        }
    }


	/**
	 * Get the Beach Hex that unit in the given landing box will land on
	 * @param landingBox
	 * @return
	 */
	public Cell getLandingBoxBeachHex( Cell landingBox, Tide tide ) {
		Point cellLocation = LANDING_BOX_DESTINATIONS.get( landingBox.getCode() ).get( tide );
		return get( cellLocation.x, cellLocation.y );
	}


	public Map<Intensity, List<Cell>> translateFieldOfFire( Map<Intensity, List<Point>> fof ) {
	    Map<Intensity, List<Cell>> fields = new HashMap<>();
	    for( Intensity intensity: fof.keySet() ) {
	        fields.put( intensity, new ArrayList<>() );
            for( Point p : fof.get( intensity ) ) {
                Cell cell = get( p.x, p.y );
                fields.get( intensity ).add( cell );
            }
        }
	    return fields;
    }
}
