package ddob;

import ddob.game.*;
import ddob.game.board.*;
import ddob.game.board.Color;
import ddob.game.card.Card;
import ddob.game.event.Event;
import ddob.game.event.EventHandler;
import ddob.game.phase.EventPhase;
import ddob.game.phase.GermanAttackPhase;
import ddob.game.phase.LandingCheckPhase;
import ddob.game.table.LandingCheckResult;
import ddob.game.table.LandingCheckTable;
import ddob.game.unit.*;
import ddob.view.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class GameManager implements Runnable{
    private Logger _logger = Logger.getLogger( GameManager.class.getName() );

	private Model _model;
	private View _view;
    private Game _game;
    private GamePanel _gamePanel;
    private ScheduledThreadPoolExecutor _threadPool;
    private boolean _stop;

    public GameManager( Model model, View view ) {
		_model = model;
		_view = view;
        _game = model.getGame();
        _gamePanel = _view.getGamePanel();
        _gamePanel.setGameManager( this );
        _stop = false;

        _threadPool = new ScheduledThreadPoolExecutor( 1 );
        _threadPool.schedule( this, 1, TimeUnit.SECONDS );
    }

    public void stop() {
        _stop = true;
        _threadPool.shutdownNow();
    }

    public void run() {
        try {
            String phase = _game.getCurrentTurn().getCurrentPhase().getName();
            if (phase.equals(Phase.LANDING_CHECK_EAST_PHASE) || phase.equals(Phase.LANDING_CHECK_WEST_PHASE)) {
                handleLandingCheckPhase();
            } else if (phase.equals(Phase.EVENT_1_PHASE) || phase.equals(Phase.EVENT_2_PHASE)) {
                handleEventPhase();
            } else if (phase.equals(Phase.GERMAN_ATTACK_EAST_PHASE) || phase.equals(Phase.GERMAN_ATTACK_WEST_PHASE)) {
                handleGermanAttackPhase();
            } else if (phase.equals(Phase.ENGINEER_PHASE)) {

            } else if (phase.equals(Phase.US_ACTIONS_EAST_PHASE) || phase.equals(Phase.US_ACTIONS_WEST_PHASE)) {

            } else if (phase.equals(Phase.END_TURN_PHASE)) {

            }
        }
        catch( Exception e ) {
            _logger.severe( "" + e );
        }
        finally {
            if (!_stop)
                _threadPool.schedule(this, 2, TimeUnit.SECONDS);
        }
    }

    private void handleLandingCheckPhase() {
        _logger.info( "Handling Landing Check Phase" );
        Turn turn = _game.getCurrentTurn();
        Phase phase = turn.getCurrentPhase();
        int phaseProgress = phase.getProgress();

        switch( phaseProgress ) {
            case LandingCheckPhase.PLACE_UNITS_IN_LANDING_BOXES: {
                _logger.info( "   Place units in Landing Boxes" );
				_view.notifyInfo( "Landing Check " + phase.getSector() );
                StringBuilder sb = new StringBuilder();
                for (USUnit unit : turn.getArrivingUnits()) {
					// Only place Division applicable to this phase
					if( (phase.getSector() == Sector.EAST && unit.getDivision() == Division.US_1) ||
					    (phase.getSector() == Sector.WEST && unit.getDivision() == Division.US_29) ) {
                    	// Place unit in landing box
                    	// If player has choice, add unit to next phase
                    	List<Cell> cells = _game.getBoard().getLandingBox( unit.getLandingBox() );
                    	if( cells.size() > 1 ) {
                        	((LandingCheckPhase) phase).getPlayerPlacement().add( unit );
                    	} else if( cells.size() == 1 ) {
	                        cells.get( 0 ).getUnits().add( unit );
							sb.append( "Placed " + unit );
							sb.append( " in " + cells.get( 0 ) );
							sb.append( "\n" );
    	                }
        	            else {
            	            _logger.severe( "Unable to get landing boxes for '" + unit.getLandingBox() + "'" );
                	    }
					}
                }
				if( !sb.toString().equals( "" ) ) {
					_view.notifyInfoLong( sb.toString() );
				}
                phase.incProgress();
                break;
            }
            case LandingCheckPhase.PLAYER_PLACE_UNITS_IN_LANDING_BOXES: {
                _logger.info( "   Place units in Landing Boxes (Player)" );
                // TODO Only enable valid landing boxes for last unit
                // TODO Once unit is placed, pop from list
                // TODO Once list is empty, goto next phase progress
                phase.incProgress();
                break;
            }
            case LandingCheckPhase.DRAW_CARD: {
                _logger.info( "   Draw card" );
                Card card = _game.getDeck().draw();
                phase.setCard( card );
                phase.incProgress();
                break;
            }
            case LandingCheckPhase.APPLY_LANDING_CHECKS: {
                _logger.info( "   Apply Landing Checks" );
				StringBuilder sb = new StringBuilder();
                for( Cell cell: _game.getBoard().getLandingBoxes( ((LandingCheckPhase) phase).getSector() ) ) {
                    List<Unit> toremove = new ArrayList<>();
                    for( Unit unit: cell.getUnits() ) {
                        LandingCheckResult result = LandingCheckTable.get( turn.getNumber(), unit, phase.getCard() );
                        switch( result ) {
                            case NO_EFFECT:
                                break;
                            case DRIFT_ONE_BOX_EAST:
								sb.append( unit + " drifted one box East" );
                                Cell target = _game.getBoard().getRelativeLandingBox( cell, -1 );
                                if( target != null ) {
                                    target.getUnits().add( unit );
                                }
                                else {
                                    // DELAY 1 Turn
									sb.append( unit + " delayed 1 turn" );
                                    _game.getFutureTurn( 1 ).getArrivingUnits().add( (USUnit) unit );
                                }
                                toremove.add( unit );
                                break;
                            case DELAYED_TWO_TURNS:
								sb.append( unit + " delayed 2 turns" );
                                _game.getFutureTurn( 2 ).getArrivingUnits().add( (USUnit) unit );
                                toremove.add( unit );
                                break;
                        }
                    }

                    for( Unit unit: toremove ) {
                        cell.getUnits().remove( unit );
                    }
                }
				
				if( !sb.toString().equals( "" ) ) {
					_view.notifyInfoLong( sb.toString() );
				}
                phase.incProgress();
                break;
            }
            case LandingCheckPhase.CHECK_MINE_EXPLOSION: {
                _logger.info( "   Check for mine explosion" );
                if( turn.getNumber() < Game.MID_TIDE_TURN ) {
                    phase.incProgress();
                    break;
                }
                for( Cell cell: _game.getBoard().getLandingBoxes( ((LandingCheckPhase) phase).getSector() ) ) {
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
                _logger.info( "   Land units" );
                for( Cell cell: _game.getBoard().getLandingBoxes( ((LandingCheckPhase) phase).getSector() ) ) {
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
                _logger.info( "   End Phase" );
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
        EventHandler.handle( event, _game, _gamePanel );
    }

    private void handleGermanAttackPhase() {
        Turn turn = _game.getCurrentTurn();
        Phase phase = turn.getCurrentPhase();
        int phaseProgress = phase.getProgress();

        switch (phaseProgress) {
            case GermanAttackPhase.DRAW_CARD:
            {
                Card card = _game.getDeck().draw();
                phase.setCard( card );
                phase.incProgress();
                break;
            }
            case GermanAttackPhase.ATTACK1:
            {
                Card card = phase.getCard();
                Color color = card.getGermanAttackColor1();
                GermanActionSymbol symbol = card.getGermanActionSymbol1();
                int minDepth = card.getGermanAttackDepth1();
                UnitSymbol unitSymbol = card.getUnitSymbol();
                Sector sector = phase.getName().equals( Phase.GERMAN_ATTACK_EAST_PHASE)? Sector.EAST: Sector.WEST;
                handleGermanAttack( turn.getNumber(), sector, color, symbol, minDepth, unitSymbol );
                phase.incProgress();
                break;
            }
            case GermanAttackPhase.ATTACK1_PLAYER_INPUT:
            {
                phase.incProgress();
                break;
            }
            case GermanAttackPhase.ATTACK2:
            {
                Card card = phase.getCard();
                Color color = card.getGermanAttackColor2();
                GermanActionSymbol symbol = card.getGermanActionSymbol2();
                int minDepth = card.getGermanAttackDepth2();
                UnitSymbol unitSymbol = card.getUnitSymbol();
                Sector sector = phase.getName().equals( Phase.GERMAN_ATTACK_EAST_PHASE)? Sector.EAST: Sector.WEST;
                handleGermanAttack( turn.getNumber(), sector, color, symbol, minDepth, unitSymbol );
                phase.incProgress();
                break;
            }
            case GermanAttackPhase.ATTACK2_PLAYER_INPUT:
            {
                phase.incProgress();
                break;
            }
            case GermanAttackPhase.ATTACK3:
            {
                Card card = phase.getCard();
                Color color = card.getGermanAttackColor3();
                GermanActionSymbol symbol = card.getGermanActionSymbol3();
                int minDepth = card.getGermanAttackDepth3();
                UnitSymbol unitSymbol = card.getUnitSymbol();
                Sector sector = phase.getName().equals( Phase.GERMAN_ATTACK_EAST_PHASE)? Sector.EAST: Sector.WEST;
                handleGermanAttack( turn.getNumber(), sector, color, symbol, minDepth, unitSymbol );
                phase.incProgress();
                break;
            }
            case GermanAttackPhase.ATTACK3_PLAYER_INPUT:
            {
                phase.incProgress();
                break;
            }
            case GermanAttackPhase.REMOVE_DISRUPTION:
            {
                CellVisitor visitor = new CellVisitor() {
                    @Override
                    public boolean visit(Cell cell) {
                        for( Unit unit: cell.getUnits() ) {
                            if( unit.getAllegiance() == Allegiance.US ) {
                                return true;
                            }
                            unit.setDisrupted( false );
                        }
                        return true;
                    }
                };
                _game.getBoard().visitCells( visitor );
                phase.incProgress();
                break;
            }
            case GermanAttackPhase.END_PHASE:
            {
                endPhase();
                break;
            }
        }
    }

    private void handleGermanAttack(int turn, Sector sector, Color color, GermanActionSymbol actionSymbol, int minDepth, UnitSymbol unitSymbol ) {
        List<Cell> cells = new ArrayList<>();
        CellVisitor visitor = new CellVisitor() {
            @Override
            public boolean visit(Cell cell) {
                if( cell.hasAttackPosition() && cell.getAttackPositionColor() == color ) {
                    cells.add( cell );
                }
                return true;
            }
        };
        _game.getBoard().visitCells( sector, visitor );

        for( Cell cell: cells ) {

            if( cell.getUnits().size() == 0 ) {
                // If actionsymbol is "A", then this may still be OK
                if( turn < Game.BEYOND_THE_BEACH_START_TURN || actionSymbol != GermanActionSymbol.ADVANCE_AMBUSH_ARTILLERY ) {
                    // Nope, skip
                    continue;
                }
            }
            else if( cell.getUnits().get( 0 ).getAllegiance() == Allegiance.US ) {
                // Position occupied by US unit, skip
                continue;
            }

            // Make sure the german unit has sufficient depth
            // this should not apply if the action symbol is "R", since the position may gain a depth marker
            if( minDepth > 1 && (turn < Game.BEYOND_THE_BEACH_START_TURN || actionSymbol != GermanActionSymbol.RESUPPLY_REDEPLOY_REINFORCE_REOCCUPY) ) {
                boolean isOK = false;
                for( Unit unit: cell.getUnits() ) {
                    if( ((GermanUnit) unit).getDepthMarker() != null ) {
                        isOK = true;
                    }
                }
                if( !isOK )
                    continue;
            }

            Map<Intensity, List<Cell>> fof = _game.getBoard().translateFieldOfFire( cell.getAttackPositionFieldOfFire() );

            switch( actionSymbol ) {
                case FIRE: {
                    handleGermanAttackFire( turn, cell, fof, unitSymbol, false, false );
                    break;
                }
                case ARMOR_HIT_BONUS: {
                    handleGermanAttackFire( turn, cell, fof, unitSymbol, true, false );
                    break;
                }
                case PATROL: {
                    if( turn < Game.BEYOND_THE_BEACH_START_TURN ) {
                        handleGermanAttackFire( turn, cell, fof, unitSymbol, false, false );
                    }
                    else {

                    }
                    break;
                }
                case ADVANCE_AMBUSH_ARTILLERY: {
                    if( turn < Game.BEYOND_THE_BEACH_START_TURN ) {
                        handleGermanAttackFire( turn, cell, fof, unitSymbol, false, false );
                    }
                    else {
                        // TODO If not Ambush, check if unit exists
                    }
                    break;
                }
                case MORTAR: {
                    if( turn < Game.BEYOND_THE_BEACH_START_TURN ) {
                        handleGermanAttackFire( turn, cell, fof, unitSymbol, false, false );
                    }
                    else {

                    }
                    break;
                }
                case RESUPPLY_REDEPLOY_REINFORCE_REOCCUPY: {
                    if( turn < Game.BEYOND_THE_BEACH_START_TURN ) {
                        handleGermanAttackFire( turn, cell, fof, unitSymbol, false, false );
                    }
                    else {
                        // TODO If not REINFORCE(?), check if unit has minDepth
                    }
                    break;
                }
                case STAR: {
                    handleGermanAttackFire( turn, cell, fof, unitSymbol, false, true);
                    break;
                }
            }
        }
    }


    private void handleGermanAttackFire( int turn, Cell attackPosition, Map<Intensity, List<Cell>> fof, UnitSymbol unitSymbol, boolean armorHitBonus, boolean hqHitBonus ) {
        int maxAttacks = ((GermanUnit) attackPosition.getUnits().get( 0 )).getDepthMarker() != null? 2: 1;
        if( turn >= Game.BEYOND_THE_BEACH_START_TURN )
            maxAttacks *= 2;
        Intensity[] intensityOrder = new Intensity[]{
                Intensity.INTENSE,
                Intensity.STEADY,
                Intensity.SPORADIC
        };
        for( Intensity intensity: intensityOrder ) {
            List<Cell> fieldOfFire = fof.get( intensity );
            for( Cell cell: fieldOfFire ) {
                for( Unit unit: cell.getUnits() ) {
                    if( unit.getAllegiance() == Allegiance.US ) {
                        // Don't hit an HQ or General unless there is an HQ Hit Bonus
                        if( (unit.getType() == UnitType.HQ || unit.getType() == UnitType.GENERAL) && !hqHitBonus )
                            continue;

                        if( intensity == Intensity.INTENSE ) {
                            // Unit loses a step
                            unit.nextState();
                            --maxAttacks;
                        }
                        else if( intensity == Intensity.STEADY ) {
                            // Non-armored (unless armor bonus) units with same unit symbol lose a step
                            if( unit.getState().getSymbol() == unitSymbol && (!unit.getType().isArmored() || armorHitBonus) ) {
                                unit.nextState();
                                --maxAttacks;
                            }
                        }
                        else if( intensity == Intensity.STEADY ) {
                            // Non-armored (unless armor bonus) units with same unit symbol are disrupted
                            if( unit.getState().getSymbol() == unitSymbol && (!unit.getType().isArmored() || armorHitBonus) ) {
                                unit.setDisrupted( true );
                                --maxAttacks;
                            }
                        }
                    }
                    if( maxAttacks == 0 )
                        break;
                }
                if( maxAttacks == 0 )
                    break;
            }
            if( maxAttacks == 0 )
                break;
        }
    }
}
