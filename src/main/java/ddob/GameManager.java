package ddob;

import ddob.game.Game;
import ddob.game.Phase;
import ddob.game.Turn;
import ddob.game.Unit;
import ddob.game.board.Cell;
import ddob.game.card.Card;
import ddob.game.event.Event;
import ddob.game.phase.EventPhase;
import ddob.game.phase.LandingCheckPhase;
import ddob.game.table.LandingCheckResult;
import ddob.game.table.LandingCheckTable;
import ddob.view.GamePanel;

public class GameManager {
    private Game _game;
    private GamePanel _gamePanel;

    public GameManager( Game game, GamePanel gamePanel ) {
        _game = game;
        _gamePanel = gamePanel;
        _gamePanel.setGameManager( this );
    }

    public void run() {
        String phase = _game.getCurrentTurn().getCurrentPhase().getName();
        if( phase.equals( Phase.LANDING_CHECK_EAST_PHASE ) || phase.equals( Phase.LANDING_CHECK_WEST_PHASE ) ) {
            handleLandingCheckPhase();
        }
        else if( phase.equals( Phase.EVENT_1_PHASE ) || phase.equals( Phase.EVENT_2_PHASE ) ) {
            handleEventPhase();
        }
        else if( phase.equals( Phase.GERMAN_ATTACK_EAST_PHASE ) || phase.equals( Phase.GERMAN_ATTACK_WEST_PHASE ) ) {

        }
        else if( phase.equals( Phase.ENGINEER_PHASE ) ) {

        }
        else if( phase.equals( Phase.US_ACTIONS_EAST_PHASE ) || phase.equals( Phase.US_ACTIONS_WEST_PHASE ) ) {

        }
        else if( phase.equals( Phase.END_TURN_PHASE ) ) {

        }
    }

    private void handleLandingCheckPhase() {
        Turn turn = _game.getCurrentTurn();
        Phase phase = turn.getCurrentPhase();
        int phaseProgress = phase.getProgress();

        switch( phaseProgress ) {
            case LandingCheckPhase.PLACE_UNITS_IN_LANDING_BOXES: {
                // TODO Only place Division applicable to this phase
                for (Unit unit : turn.getArrivingUnits()) {
                    // Place unit in landing box
                    // If player has choice, add unit to next phase
                    if (unit.getLandingBox().equals(Unit.LANDING_BOX_1st_DIVISION) ||
                            unit.getLandingBox().equals(Unit.LANDING_BOX_29th_DIVISION)) {
                        ((LandingCheckPhase) phase).getPlayerPlacement().add(unit);
                    } else {
                        _game.getBoard().getLandingBox(unit.getLandingBox()).getUnits().add(unit);
                    }
                }
                phase.incProgress();
                break;
            }
            case LandingCheckPhase.PLAYER_PLACE_UNITS_IN_LANDING_BOXES: {
                // TODO Only enable valid landing boxes for last unit
                // TODO Once unit is placed, pop from list
                // TODO Once list is empty, goto next phase progress
                break;
            }
            case LandingCheckPhase.DRAW_CARD: {
                Card card = _game.getDeck().draw();
                phase.setCard( card );
                phase.incProgress();
                break;
            }
            case LandingCheckPhase.APPLY_LANDING_CHECKS: {
                for( int i = 0; i < _game.getBoard().getLandingBoxes().length; ++i ) {
                    Cell cell = _game.getBoard().getLandingBoxes()[ i ];
                    for( Unit unit: cell.getUnits() ) {
                        LandingCheckResult result = LandingCheckTable.get( turn.getNumber(), unit, phase.getCard() );
                        switch( result ) {
                            case NO_EFFECT:
                                break;
                            case DRIFT_ONE_BOX_EAST:
                                if( i - 1 >= 0 ) {
                                    _game.getBoard().getLandingBoxes()[ i -1 ].getUnits().add( unit );
                                }
                                else {
                                    // DELAY 1 Turn
                                    _game.getFutureTurn( 1 ).getArrivingUnits().add( unit );
                                }
                                break;
                            case DELAYED_TWO_TURNS:
                                _game.getFutureTurn( 2 ).getArrivingUnits().add( unit );
                                break;
                        }
                    }
                }
                phase.incProgress();
                break;
            }
            case LandingCheckPhase.CHECK_MINE_EXPLOSION: {
                if( turn.getNumber() < Game.MID_TIDE_TURN ) {
                    phase.incProgress();
                    break;
                }
                for( int i = 0; i < _game.getBoard().getLandingBoxes().length; ++i ) {
                    Cell cell = _game.getBoard().getLandingBoxes()[i];
                    Cell targetCell = _game.getBoard().getLandingBoxBeachHex( cell, turn.getTide() );
                    if( !targetCell.isCleared() ) {
                        for (Unit unit : cell.getUnits()) {
                            // TODO Apply mine explosion
                        }
                    }
                }
                phase.incProgress();
                break;
            }
            case LandingCheckPhase.LAND_UNITS: {
                for( int i = 0; i < _game.getBoard().getLandingBoxes().length; ++i ) {
                    Cell cell = _game.getBoard().getLandingBoxes()[i];
                    Cell targetCell = _game.getBoard().getLandingBoxBeachHex( cell, turn.getTide() );
                    for (Unit unit : cell.getUnits()) {
                        targetCell.getUnits().add( unit );
                        // TODO What happens if we exceed the stacking limit?
                    }
                    cell.getUnits().clear();
                }
                phase.incProgress();
                break;
            }
            case LandingCheckPhase.END_PHASE: {
                endPhase();
                break;
            }
        }
    }


    private void handleEventPhase() {
        Turn turn = _game.getCurrentTurn();
        Phase phase = turn.getCurrentPhase();
        int phaseProgress = phase.getProgress();

        switch( phaseProgress ) {
            case EventPhase.DRAW_CARD: {
                Card card = _game.getDeck().draw();
                phase.setCard( card );
                if( turn.getNumber() >= 2 && turn.getNumber() <= 10 )
                    applyEvent( card.getEvent2_10() );
                else if( turn.getNumber() >= 11 && turn.getNumber() <= 20 )
                    applyEvent( card.getEvent11_20() );
                else if( turn.getNumber() >= 21 )
                    applyEvent( card.getEvent21_31() );
                phase.incProgress();
                break;
            }
            case EventPhase.END_PHASE: {
                endPhase();
                break;
            }
        }
    }


    private void endPhase() {
        _game.getCurrentTurn().getCurrentPhase().incProgress();
        _game.getCurrentTurn().nextPhase();
    }


    private void applyEvent( Event event ) {

    }
}
