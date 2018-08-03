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

    // w: water
    // b: beach
    // l: landing box
    // p: pavilion
    // d: draw
    // h: high ground
    // r: rough
    // c: bocage
    // o: building
    // m: draw + woods
    // n: draw + buildings
    // #: off-map
    public static final String[] CELLTYPES = {
            "##hhccchocchcccccchhhhhchhcchchhh##",   // 20
            "hhhcochohhcchcccchhhwcchhhcchhhchhhh",  // 19
            "hhhohhwohhcchcccccchhchchhcwhchchhh",   // 18
            "hcchchhhhhchhhhwcchhhhhcchhchhhccchh",  // 17
            "hhchhhoohhoohhcccchchhhcchcocohhccc",   // 16
            "hhhhchhhhhohhchhhhwcwhhccccwhhhhohcc",  // 15
            "chcchcccoomchwcchhohoccoocchcchcoho",   // 14
            "hcchhhccoomdchccchcooochhhhchchhcchc",  // 13
            "chcchhohchdhcccmccooocchhhhhcchhhhh",   // 12
            "choohhcchchmocchmhoonhdccchhhwcchcho",  // 11
            "oocchhhchcdhcccmhhhhnnhccchcoochccw",   // 10
            "cchhrhcccddhhcchdhhhhhdhhchhoowhhhcw",  // 9
            "hrhhrhwppppppppppppppphhhhhcoooochw",   // 8
            "hrhrrrppbbbbbbbbbbbbpppppppchhdhchhh",  // 7
            "wwrbbbbbbbbbbbbbbbbbbbbbbbpphdhhhhh",   // 6
            "wwwbbbbbbbbbbbbbbbbbbbbbbbbbppphhhch",  // 5
            "wwwllllllllllllllbbbbbbbbbbbbbbbhhh",   // 4
            "wwwwwwwwwwwwwwwwwllllllllbbbbbbbbbrr",  // 3
            "wwwwwwwwwwwwwwwwwwwwwwwwllbbbbbbbbb",   // 2
            "wwwwwwwwwwwwwwwwwwwwwwwwwwlllllllbbb"   // 1
	};


    // s: shingle
    // c: cliff
    // p: slope
    // b: bluff
    // f: sheer cliff
    // i: scalable cliff
    // h: hedge
    // a: seawall
    // w: wall
    // t: anti-tank ditch
    public static final String CELLEDGES = "" +
            "hrhhrhwppppppppppppppphhhhhcoooochw\n" +   // 8
            "hrhrrrpp. . . . . . . . . . . . pppppppchhdhchhh\n" +  // 7
            ". . r. . . . . . . . . . . . . . . . . . . . . . . pphdhhhhh\n" +   // 6
            ". . . . . . . . . . . . . . . . . . . . . . . . . . . . ppphhhch\n" +  // 5
            ". . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . hhh\n" +   // 4
            ". . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . rr\n" +  // 3
            ". . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . f..... f....f \n" +   // 2
            ". . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . ";     // 1


    private static final Map<String, String> CELLCODES = new HashMap<>();

    static {
        CELLCODES.put( "0128", "DG1" );
        CELLCODES.put( "0129", "DG2" );
        CELLCODES.put( "0130", "DG3" );
        CELLCODES.put( "0131", "DG4" );
        CELLCODES.put( "0132", "CH1" );
        CELLCODES.put( "0133", "CH2" );
        CELLCODES.put( "0134", "CH3" );
        CELLCODES.put( "0225", "DW2" );
        CELLCODES.put( "0226", "DW3" );
        CELLCODES.put( "0319", "EG1" );
        CELLCODES.put( "0320", "EG2" );
        CELLCODES.put( "0321", "EG3" );
        CELLCODES.put( "0322", "DR1" );
        CELLCODES.put( "0323", "DR2" );
        CELLCODES.put( "0324", "DR3" );
        CELLCODES.put( "0325", "DW1" );
        CELLCODES.put( "0407", "FG1" );
        CELLCODES.put( "0408", "FG2" );
        CELLCODES.put( "0409", "FG3" );
        CELLCODES.put( "0410", "FG4" );
        CELLCODES.put( "0411", "FG5" );
        CELLCODES.put( "0412", "ER1" );
        CELLCODES.put( "0413", "ER2" );
        CELLCODES.put( "0414", "ER3" );
        CELLCODES.put( "0415", "ER4" );
        CELLCODES.put( "0416", "ER5" );
        CELLCODES.put( "0417", "ER6" );
        CELLCODES.put( "0531", "WN 72N" );
        CELLCODES.put( "0533", "WN 73" );
        CELLCODES.put( "0629", "WN 71" );
        CELLCODES.put( "0631", "WN 72S" );
        CELLCODES.put( "0635", "G1" );
        // TODO Finish this

        // FG
        addLandingBoxDest( "0407", "0508", "0608", "0709" );
        addLandingBoxDest( "0408", "0509", "0609", "0710" );
        addLandingBoxDest( "0409", "0510", "0610", "0711" );
        addLandingBoxDest( "0410", "0511", "0611", "0712" );
        addLandingBoxDest( "0411", "0512", "0612", "0713" );
        // ER
        addLandingBoxDest( "0412", "0513", "0613", "0714" );
        addLandingBoxDest( "0413", "0514", "0614", "0715" );
        addLandingBoxDest( "0414", "0515", "0615", "0716" );
        addLandingBoxDest( "0415", "0516", "0616", "0717" );
        addLandingBoxDest( "0416", "0517", "0617", "0718" );
        addLandingBoxDest( "0417", "0518", "0618", "0719" );
        // EG
        addLandingBoxDest( "0319", "0419", "0520", "0620" );
        addLandingBoxDest( "0320", "0420", "0521", "0621" );
        addLandingBoxDest( "0321", "0421", "0522", "0622" );
        //DR
        addLandingBoxDest( "0322", "0422", "0523", "0623" );
        addLandingBoxDest( "0323", "0423", "0524", "0624" );
        addLandingBoxDest( "0324", "0424", "0525", "0625" );
        // DW
        addLandingBoxDest( "0325", "0425", "0526", "0626" );
        addLandingBoxDest( "0225", "0326", "0426", "0527" );
        addLandingBoxDest( "0226", "0327", "0427", "0528" );
        // DG
        addLandingBoxDest( "0128", "0227", "0328", "0428" );
        addLandingBoxDest( "0129", "0228", "0329", "0429" );
        addLandingBoxDest( "0130", "0229", "0330", "0430" );
        addLandingBoxDest( "0131", "0230", "0331", "0431" );
        // CH
        addLandingBoxDest( "0132", "0231", "0332", "0432" );
        addLandingBoxDest( "0133", "0232", "0333", "0433" );
        addLandingBoxDest( "0134", "0233", "0334", "0434" );
    }

    private static void addLandingBoxDest( String lb, String lt, String mt, String ht ) {
        int lby = Integer.parseInt( lb.substring( 0, 2 ) ) - 1;
        int lbx = Integer.parseInt( lb.substring( 2, 4 ) ) - 1;
        int lty = Integer.parseInt( lt.substring( 0, 2 ) ) - 1;
        int ltx = Integer.parseInt( lt.substring( 2, 4 ) ) - 1;
        int mty = Integer.parseInt( mt.substring( 0, 2 ) ) - 1;
        int mtx = Integer.parseInt( mt.substring( 2, 4 ) ) - 1;
        int hty = Integer.parseInt( ht.substring( 0, 2 ) ) - 1;
        int htx = Integer.parseInt( ht.substring( 2, 4 ) ) - 1;
        Point p = new Point( lbx, lby );
        LANDING_BOX_DESTINATIONS.put( p, new HashMap<>() );
        LANDING_BOX_DESTINATIONS.get( p ).put( Tide.LOW_TIDE,  new Point( ltx, lty ) );
        LANDING_BOX_DESTINATIONS.get( p ).put( Tide.MID_TIDE,  new Point( mtx, mty ) );
        LANDING_BOX_DESTINATIONS.get( p ).put( Tide.HIGH_TIDE, new Point( htx, hty ) );
    }




	private List<Cell> _cells;

	public Board() {
		_cells = new ArrayList<>();
		buildBoard();
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

    public String toMapCoord( int xIndex, int yIndex ) {
        StringBuilder sb = new StringBuilder();
        if( yIndex < 9 )
            sb.append( "0" );
        sb.append( yIndex + 1 );
        if( xIndex < 9 )
            sb.append( "0" );
        sb.append( xIndex + 1 );
        return sb.toString();
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

	public void disableCells() {
	    CellVisitor visitor = new CellVisitor() {
            @Override
            public boolean visit( Cell cell ) {
                cell.setSelectable( false );
                return true;
            }
        };
	    visitCells( visitor );
    }

    public void enableCells() {
        CellVisitor visitor = new CellVisitor() {
            @Override
            public boolean visit( Cell cell ) {
                cell.setSelectable( true );
                return true;
            }
        };
        visitCells( visitor );
    }


    /**
     * Get the landing box with the given code/name
     * @param name
     * @return
     */
	public List<Cell> getLandingBox( String name ) {
	    List<Cell> ret = new ArrayList<>();
	    for( Cell cell: _cells ) {
            if( cell.getY() >= 4 )
                break;
	        if( cell.hasType( CellType.LANDING_BOX ) ) {
	            if( cell.getCode().equals( name ) || cell.getCode().startsWith( name ) ) {
                    ret.add( cell );
                }
                else if( name.equals( "1st" ) &&
                        ((cell.getY() % 2 == 0 && cell.getX() <= SECTOR_BOUNDARY_EVEN_ROWS) || (cell.getY() % 2 == 1 && cell.getX() <= SECTOR_BOUNDARY_ODD_ROWS) ) ) {
                    ret.add( cell );
                }
                else if( name.equals( "29th" ) &&
                        ((cell.getY() % 2 == 0 && cell.getX() > SECTOR_BOUNDARY_EVEN_ROWS) || (cell.getY() % 2 == 1 && cell.getX() > SECTOR_BOUNDARY_ODD_ROWS) ) ) {
                    ret.add( cell );
                }
            }
        }
        return ret;
	}

    /**
     * Get all landing boxes
     * @return
     */
	public List<Cell> getLandingBoxes(){
	    List<Cell> landingBoxes = new ArrayList<>();
        for( Cell cell: _cells ) {
            if( cell.hasType( CellType.LANDING_BOX ) ) {
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
            if( cell.hasType( CellType.LANDING_BOX ) ) {
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
        if( target.hasType( CellType.LANDING_BOX ) )
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
                if( target.hasType( CellType.LANDING_BOX ) )
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
                if( target.hasType( CellType.LANDING_BOX ) )
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


    private void buildBoard() {
	    String[] celltypes = new String[ CELLTYPES.length ];
	    int j = 0;
	    for( int i = CELLTYPES.length - 1; i >= 0; --i ) {
	        celltypes[ j++ ] = CELLTYPES[ i ];
        }

	    for( int y = 0; y < HEIGHT; ++y ) {
	        for( int x = 0; x < WIDTH; ++x ) {
	            if( y % 2 == 1 && x == WIDTH - 2 )
	                break;
	            List<CellType> types = getCellType( x, y, celltypes );
                String code = getCellCode( x, y );
	            Cell cell = new Cell( x, y, types, code );
	            _cells.add( cell );
            }
        }
    }

    private List<CellType> getCellType( int x, int y, String[] celltypes ) {
        List<CellType> types = new ArrayList<>();
        char c = celltypes[ y ].charAt( x );
        switch( c ) {
            case 'w':
                types.add( CellType.WATER );
                break;
            case 'b':
                types.add( CellType.BEACH );
                break;
            case 'l':
                types.add( CellType.LANDING_BOX );
                break;
            case 'p':
                types.add( CellType.PAVILION );
                break;
            case 'd':
                types.add( CellType.DRAW );
                break;
            case 'h':
                types.add( CellType.HIGH_GROUND );
                break;
            case 'r':
                types.add( CellType.ROUGH );
                break;
            case 'c':
                types.add( CellType.BOCAGE );
                break;
            case 'o':
                types.add( CellType.BUILDINGS );
                break;
            case 'm':
                types.add( CellType.DRAW );
                types.add( CellType.WOODS );
                break;
            case 'n':
                types.add( CellType.DRAW );
                types.add( CellType.BUILDINGS );
                break;
            case '#':
                types.add( CellType.OFF_LIMITS );
                break;
        }
        return types;
    }

    private String getCellCode( int x, int y ){
	    String mapCoord = toMapCoord( x, y );
	    if( CELLCODES.containsKey( mapCoord ) )
	        return CELLCODES.get( mapCoord );
        return "";
    }
}

