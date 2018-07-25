package ddob.game.unit;

public enum UnitType {
    HQ,
    GENERAL,
    ENGINEER,
	INFANTRY,
    RANGER_INFANTRY,
	TANK,
    ARTILLERY,
    SELF_PROPELLED_ARTILLERY,
    ARTILLERY_DUKW, // Amphibious Artillery
    ANTI_AIRCRAFT,
    SELF_PROPELLED_ANTI_AIRCRAFT,
    ANTI_TANK,

    // German-only types
    ARTILLERY_88,
    ARTILLERY_105,
    WN,
    WN_ROCKET,
    WN_ARTILLERY_75,
    WN_ARTILLERY_88,
    WN_ARTILLERY_105;

	UnitType() {

    }

    public boolean isArmored() {
	    return (this == UnitType.TANK || this == UnitType.SELF_PROPELLED_ARTILLERY || this == UnitType.SELF_PROPELLED_ANTI_AIRCRAFT);
    }
}