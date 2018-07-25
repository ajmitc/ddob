package ddob.game.unit;

public enum UnitType {
    HQ,
    GENERAL,
    ARTILLERY,
    ARTILLERY_DUKW,
	INFANTRY,
	TANK,
	ANTI_AIR,
    ANTI_TANK,
    SELF_PROPELLED_ARTILLERY,
    SELF_PROPELLED_ANTI_TANK;

	UnitType() {

    }

    public boolean isArmored() {
	    return this == UnitType.TANK;
    }
}