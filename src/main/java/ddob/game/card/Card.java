package ddob.game.card;

import ddob.game.unit.ArtilleryType;
import ddob.game.GermanActionSymbol;
import ddob.game.unit.UnitSymbol;
import ddob.game.board.Color;
import ddob.game.event.Event;
import ddob.util.ImageFactory;

import java.awt.image.BufferedImage;

public class Card {
	private BufferedImage _image;

    private String _filename;
    private LandingCheckColumn _diamond;
    private LandingCheckColumn _triangle;
    private LandingCheckColumn _circle;
    private boolean _mineExplosion;
    private Event _event2_10;
    private Event _event11_20;
    private Event _event21_31;
    private Color _germanAttackColor1;
    private int _germanAttackDepth1;
    private GermanActionSymbol _germanActionSymbol1;
    private Color _germanAttackColor2;
    private int _germanAttackDepth2;
    private GermanActionSymbol _germanActionSymbol2;
    private Color _germanAttackColor3;
    private int _germanAttackDepth3;
    private GermanActionSymbol _germanActionSymbol3;
    private UnitSymbol _unitSymbol;
    private boolean _artillery;
    private int _minArtillery;
    private ArtilleryType[] _artilleryTypes;

    public Card( String filename,
                 LandingCheckColumn diamond, LandingCheckColumn triangle, LandingCheckColumn circle, boolean mine,
                 Event event2_10, Event event11_20, Event event21_31,
                 Color germanAttackColor1, int germanAttackDepth1, GermanActionSymbol germanActionSymbol1,
                 Color germanAttackColor2, int germanAttackDepth2, GermanActionSymbol germanActionSymbol2,
                 Color germanAttackColor3, int germanAttackDepth3, GermanActionSymbol germanActionSymbol3,
                 UnitSymbol symbol
            ) {
        this( filename,
                diamond, triangle, circle, mine,
                event2_10, event11_20, event21_31,
                germanAttackColor1, germanAttackDepth1, germanActionSymbol1,
                germanAttackColor2, germanAttackDepth2, germanActionSymbol2,
                germanAttackColor3, germanAttackDepth3, germanActionSymbol3,
                symbol,
                -1, new ArtilleryType[]{} );
    }

	public Card( String filename,
                 LandingCheckColumn diamond, LandingCheckColumn triangle, LandingCheckColumn circle, boolean mine,
                 Event event2_10, Event event11_20, Event event21_31,
                 Color germanAttackColor1, int germanAttackDepth1, GermanActionSymbol germanActionSymbol1,
                 Color germanAttackColor2, int germanAttackDepth2, GermanActionSymbol germanActionSymbol2,
                 Color germanAttackColor3, int germanAttackDepth3, GermanActionSymbol germanActionSymbol3,
                 UnitSymbol unitSymbol,
                 int minArtillery, ArtilleryType[] artilleryTypes
                 ) {
		_filename = filename;
		_image = ImageFactory.get( _filename );
		_diamond = diamond;
		_triangle = triangle;
		_circle = circle;
		_mineExplosion = mine;
		_event2_10 = event2_10;
		_event11_20 = event11_20;
		_event21_31 = event21_31;
		_germanAttackColor1 = germanAttackColor1;
		_germanAttackDepth1 = germanAttackDepth1;
		_germanActionSymbol1 = germanActionSymbol1;
        _germanAttackColor2 = germanAttackColor2;
        _germanAttackDepth2 = germanAttackDepth2;
        _germanActionSymbol2 = germanActionSymbol2;
        _germanAttackColor3 = germanAttackColor3;
        _germanAttackDepth3 = germanAttackDepth3;
        _germanActionSymbol3 = germanActionSymbol3;
        _unitSymbol = unitSymbol;
        _artillery = minArtillery > 0;
        _minArtillery = minArtillery;
        _artilleryTypes = artilleryTypes;
	}

    public BufferedImage getImage() {
        return _image;
    }

    public String getFilename() {
        return _filename;
    }

    public LandingCheckColumn getDiamondLandingCheckColumn() {
        return _diamond;
    }

    public LandingCheckColumn getTriangleLandingCheckColumn() {
        return _triangle;
    }

    public LandingCheckColumn getCircleLandingCheckColumn() {
        return _circle;
    }

    public boolean hasMineExplosion() {
        return _mineExplosion;
    }

    public Event getEvent2_10() {
        return _event2_10;
    }

    public Event getEvent11_20() {
        return _event11_20;
    }

    public Event getEvent21_31() {
        return _event21_31;
    }

    public Color getGermanAttackColor1() {
        return _germanAttackColor1;
    }

    public int getGermanAttackDepth1() {
        return _germanAttackDepth1;
    }

    public GermanActionSymbol getGermanActionSymbol1() {
        return _germanActionSymbol1;
    }

    public Color getGermanAttackColor2() {
        return _germanAttackColor2;
    }

    public int getGermanAttackDepth2() {
        return _germanAttackDepth2;
    }

    public GermanActionSymbol getGermanActionSymbol2() {
        return _germanActionSymbol2;
    }

    public Color getGermanAttackColor3() {
        return _germanAttackColor3;
    }

    public int getGermanAttackDepth3() {
        return _germanAttackDepth3;
    }

    public GermanActionSymbol getGermanActionSymbol3() {
        return _germanActionSymbol3;
    }

    public UnitSymbol getUnitSymbol() {
        return _unitSymbol;
    }

    public boolean hasArtillery() {
        return _artillery;
    }

    public int getMinArtilleryCount() {
        return _minArtillery;
    }

    public ArtilleryType[] getArtilleryTypes() {
        return _artilleryTypes;
    }
}