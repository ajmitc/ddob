package ddob.game.unit;

public class USUnit extends Unit {
    public static final Weapon[] INFANTRY_WEAPONS = {
            Weapon.DE,
            Weapon.BG,
            Weapon.BR,
            Weapon.BZ,
            Weapon.MO,
            Weapon.RD
            // TODO Complete this
    };

    public static final Weapon[] HEAVY_INFANTRY_WEAPONS = {
            Weapon.DE,
            Weapon.BG,
            Weapon.BR,
            Weapon.BZ,
            Weapon.MO,
            Weapon.RD
            // TODO Complete this
    };

    public static final Weapon[] ARTILLERY_WEAPONS = {
            Weapon.RD
            // TODO Complete this
    };

    public static final Weapon[] ANTI_TANK_WEAPONS = {
            Weapon.RD
            // TODO Complete this
    };

    private Division _division;
    private int _arrivalTurn;
    private String _landingBox;

    public USUnit( Division division, UnitType type, String designation, int arrivalTurn, String landingBox ) {
        super( Allegiance.US, type, designation );
        _division = division;
        _arrivalTurn = arrivalTurn;
        _landingBox = landingBox;
    }

    public Division getDivision() {
        return _division;
    }

    public int getArrivalTurn(){ return _arrivalTurn; }

    public String getLandingBox(){ return _landingBox; }
}
