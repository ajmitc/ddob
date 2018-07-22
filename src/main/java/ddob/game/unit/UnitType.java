package ddob.game.unit;

public enum UnitType {
    HQ,
    GENERAL,
	INFANTRY,
	TANK,
	ANTI_AIR;

	UnitType() {

    }

    public boolean isArmored() {
	    return this == UnitType.TANK;
    }
}