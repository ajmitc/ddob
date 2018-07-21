package ddob.game.event;

public enum Event {
    // German artillery fire prevents engineer operations this turn (19.33)
    ARTILLERY_PREVENTS_ENGINEER_OPS,

    // Germans capture US plans.  Place German reinforcements, one each in Zones A, C, D, and E (9.3)
    CAPTURE_PLANS_A_C_D_E,

    // Disrupt all circle/diamond US units adjacent to unrevealed German reinforcement units
    CONCEALED_CIRCLE_DIAMOND,

    // Disrupt all circle/triangle US units adjacent to unrevealed German reinforcement units
    CONCEALED_CIRCLE_TRIANGLE,

    // Add a Depth marker to one German unit (9.2)
    DEPTH_MARKER,

    // Add a Depth marker to one German unit in each sector (9.2)
    DEPTH_MARKER_EACH_SECTOR,

    // Place a Hero marker on any US unit in 29th Division, and add a depth marker to one German unit. (9.2)
    HERO_29_DEPTH_MARKER,

    // GI Initiative.  Conduct one extra action this turn in each division.
    INIATIVE,

    // Lost boat teams catch up.  Add a step to a 1-step infantry unit in 1st Division
    LOST_BOAT_TEAMS_CATCH_UP_1,

    // German reinforcements; place 1 each in Zones A, C, and G (9.3)
    REINFORCEMENTS_A_C_G,

    // German reinforcements; place 1 each in Zones A and D (9.3)
    REINFORCEMENTS_A_D,

    // German reinforcements; place 1 each in Zones A, D, and E (9.3)
    REINFORCEMENTS_A_D_E,

    // German reinforcements; place 1 each in Zones C and E (9.3)
    REINFORCEMENTS_C_E,

    // German reinforcements; place 1 each in Zones C, D, and G (9.3)
    REINFORCEMENTS_C_D_G,

    // Remove disruption from all German positions
    REMOVE_DISRUPTION,

    // Smoke obscures beach.  No fire from WN 66 and 68 this turn.
    SMOKE_WN66_WN68
}
