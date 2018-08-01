package ddob.game.unit;

import java.awt.image.BufferedImage;

public class USUnit extends Unit {
    public static final Weapon[] INFANTRY_WEAPONS = {
            Weapon.BZ,
            Weapon.BG,
            Weapon.BR,
            Weapon.DE,
            Weapon.MO,
            Weapon.RD
    };

    public static final Weapon[] HEAVY_INFANTRY_WEAPONS = {
            Weapon.BZ,
            Weapon.BG,
            Weapon.BR,
            Weapon.DE,
            Weapon.MO,
            Weapon.RD,
            Weapon.MG
    };

    public static final Weapon[] HEAVY_INFANTRY_WEAPONS_NON_ADJACENT = {
            Weapon.BZ,
            Weapon.BR,
            Weapon.MO,
            Weapon.RD,
            Weapon.MG
    };

    public static final Weapon[] TANK_WEAPONS = {
            Weapon.AR,
            Weapon.BZ
    };

    public static final Weapon[] TANK_WEAPONS_WITHIN_3_HEXES = {
            Weapon.AR,
            Weapon.BZ,
            Weapon.MG,
            Weapon.BR
    };

    // Includes self-propelled Anti-Aircraft
    public static final Weapon[] ANTI_AIRCRAFT_WEAPONS = {
            Weapon.MG,
            Weapon.BR
    };

    // Includes self-propelled artillery and DUKW
    public static final Weapon[] ARTILLERY_WEAPONS = {
            Weapon.AR,
            Weapon.MO,
            Weapon.DE
    };

    public static final Weapon[] ANTI_TANK_WEAPONS = {
            Weapon.AR,
            Weapon.BZ
    };

    public static final Weapon[] HQ_WEAPONS = {
            Weapon.RD
    };

    public static final Weapon[] NAVAL_FIRE_WEAPONS = {
            Weapon.NA,
            Weapon.AR,
            Weapon.DE
    };

    private Division _division;
    private int _arrivalTurn;
    private String _landingBox;
    private BufferedImage _heroImage; // if no hero, this should be null.

    public USUnit( Division division, UnitType type, String designation, int arrivalTurn, String landingBox ) {
        super( Allegiance.US, type, designation );
        _division = division;
        _arrivalTurn = arrivalTurn;
        _landingBox = landingBox;
        _heroImage = null;
    }

    public Division getDivision() {
        return _division;
    }

    public int getArrivalTurn(){ return _arrivalTurn; }

    public String getLandingBox(){ return _landingBox; }

    public BufferedImage getHeroImage() {
        return _heroImage;
    }

    public boolean hasHero() { return _heroImage != null; }
}

