package ddob.game.event;

import ddob.Model;
import ddob.game.Game;
import ddob.view.GamePanel;
import ddob.view.View;

public class EventHandler {

    public static void handle( Event event, Model model, View view ) {
        switch( event ) {
            // German artillery fire prevents engineer operations this turn (19.33)
            case ARTILLERY_PREVENTS_ENGINEER_OPS:
            {
                break;
            }

            // Germans capture US plans.  Place German reinforcements, one each in Zones A, C, D, and E (9.3)
            case CAPTURE_PLANS_A_C_D_E:
            {
                break;
            }

            // Disrupt all circle/diamond US units adjacent to unrevealed German reinforcement units
            case CONCEALED_CIRCLE_DIAMOND:
            {
                break;
            }

            // Disrupt all circle/triangle US units adjacent to unrevealed German reinforcement units
            case CONCEALED_CIRCLE_TRIANGLE:
            {
                break;
            }

            // Add a Depth marker to one German unit (9.2)
            case DEPTH_MARKER:
            {
                break;
            }

            // Add a Depth marker to one German unit in each sector (9.2)
            case DEPTH_MARKER_EACH_SECTOR:
            {
                break;
            }

            // Place a Hero marker on any US unit in 29th Division, and add a depth marker to one German unit. (9.2)
            case HERO_29_DEPTH_MARKER:
            {
                break;
            }

            // GI Initiative.  Conduct one extra action this turn in each division.
            case INIATIVE:
            {
                break;
            }

            // Lost boat teams catch up.  Add a step to a 1-step infantry unit in 1st Division
            case LOST_BOAT_TEAMS_CATCH_UP_1:
            {
                break;
            }

            // German reinforcements; place 1 each in Zones A, C, and G (9.3)
            case REINFORCEMENTS_A_C_G:
            {
                break;
            }

            // German reinforcements; place 1 each in Zones A and D (9.3)
            case REINFORCEMENTS_A_D:
            {
                break;
            }

            // German reinforcements; place 1 each in Zones A, D, and E (9.3)
            case REINFORCEMENTS_A_D_E:
            {
                break;
            }

            // German reinforcements; place 1 each in Zones C and E (9.3)
            case REINFORCEMENTS_C_E:
            {
                break;
            }

            // German reinforcements; place 1 each in Zones C, D, and G (9.3)
            case REINFORCEMENTS_C_D_G:
            {
                break;
            }

            // Remove disruption from all German positions
            case REMOVE_DISRUPTION:
            {
                break;
            }

            // Smoke obscures beach.  No fire from WN 66 and 68 this turn.
            case SMOKE_WN66_WN68:
            {
                break;
            }
        }
    }

    private EventHandler(){}
}
