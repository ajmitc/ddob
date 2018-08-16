package ddob;

import ddob.game.*;
import ddob.game.board.*;
import ddob.game.board.Color;
import ddob.game.card.Card;
import ddob.game.event.Event;
import ddob.game.event.EventHandler;
import ddob.game.phase.EngineerPhase;
import ddob.game.phase.EventPhase;
import ddob.game.phase.GermanAttackPhase;
import ddob.game.phase.LandingCheckPhase;
import ddob.game.table.LandingCheckResult;
import ddob.game.table.LandingCheckTable;
import ddob.game.unit.*;
import ddob.view.GamePanel;
import ddob.view.View;

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
                handleEngineerPhase();
            } else if (phase.equals(Phase.US_ACTIONS_EAST_PHASE) || phase.equals(Phase.US_ACTIONS_WEST_PHASE)) {
                _view.notifyInfoLong( "US Action Phase (not yet implemented)" );
                endPhase();
            } else if (phase.equals(Phase.END_TURN_PHASE)) {
                _game.nextTurn();
            }
        }
        catch( Exception e ) {
            _logger.severe( "" + e );
            e.printStackTrace();
            stop();
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
            case LandingCheckPhase.RESET: {
                _view.notifyInfo( "Landing Check " + ((LandingCheckPhase) phase).getSector() );
                ((LandingCheckPhase) phase).getPlayerPlacementWest().clear();
                ((LandingCheckPhase) phase).getPlayerPlacementEast().clear();
                ((LandingCheckPhase) phase).setWaitForUserSelection( false );
                ((LandingCheckPhase) phase).setSelectedCell( null );
                phase.incProgress();
                break;
            }
            case LandingCheckPhase.PLACE_UNITS_IN_LANDING_BOXES: {
                _logger.info( "   Place units in Landing Boxes" );
				Sector sector = ((LandingCheckPhase) phase).getSector();
                StringBuilder sb = new StringBuilder();
                for (USUnit unit : turn.getArrivingUnits()) {
					// Only place Division applicable to this phase
					if( (sector == Sector.EAST && unit.getDivision() == Division.US_1) ||
					    (sector == Sector.WEST && unit.getDivision() == Division.US_29) ) {
                    	// Place unit in landing box
                    	// If player has choice, add unit to next phase
                    	List<Cell> cells = _game.getBoard().getLandingBox( unit.getLandingBox() );
                    	if( cells.size() > 1 ) {
                    	    if( sector == Sector.EAST )
                                ((LandingCheckPhase) phase).getPlayerPlacementEast().add( unit );
                            else
                                ((LandingCheckPhase) phase).getPlayerPlacementWest().add( unit );
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
                // Once list is empty, goto next phase progress
                LandingCheckPhase lcp = (LandingCheckPhase) phase;
                Sector sector = lcp.getSector();
                if( (sector == Sector.EAST && lcp.getPlayerPlacementEast().size() == 0) ||
                    (sector == Sector.WEST && lcp.getPlayerPlacementWest().size() == 0) ) {
                    lcp.setWaitForUserSelection( false );
                    phase.incProgress();
                }
                else if( !lcp.shouldWaitForUserSelection() ) {
                    // Only enable valid landing boxes for last unit
                    _model.getGame().getBoard().disableCells();
                    _view.notifyInfo( "Place " + sector );
                    StringBuilder sb = new StringBuilder();
                    USUnit unit = sector == Sector.EAST? lcp.getPlayerPlacementEast().get( 0 ): lcp.getPlayerPlacementWest().get( 0 );
                    sb.append( "Place " + unit );
                    sb.append( " in " + unit.getLandingBox() );
                    // Enable cells
                    List<Cell> cells = _game.getBoard().getLandingBox( unit.getLandingBox() );
                    for( Cell cell : cells ) {
                        cell.setSelectable( true );
                    }
                    lcp.setWaitForUserSelection( true );
                    _view.getGamePanel().getBoardView().addBoardListener( BoardListenerType.CELL_SELECTED, lcp );
                    _view.notifyInfoLong( sb.toString() );
                }
                else if( lcp.getSelectedCell() != null ) {
                    // Once unit is placed, pop from list
                    USUnit unit = sector == Sector.EAST? lcp.getPlayerPlacementEast().remove( 0 ): lcp.getPlayerPlacementWest().remove( 0 );
                    lcp.getSelectedCell().getUnits().add( unit );
                    lcp.setSelectedCell( null );
                    lcp.setWaitForUserSelection( false );
                }
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
                for( Cell cell: _game.getBoard().getLandingBoxes( ((LandingCheckPhase) phase).getSector() ) ) {
                    List<Unit> toremove = new ArrayList<>();
                    for( Unit unit: cell.getUnits() ) {
                        LandingCheckResult result = LandingCheckTable.get( turn.getNumber(), unit, phase.getCard(), ((USUnit) unit).getLandingBox() );
                        switch( result ) {
                            case NO_EFFECT:
                                _view.getGamePanel().getBoardView().addUnitNotification( unit, "No Effect" );
                                break;
                            case ELIMINATED:
                                _view.notifyInfoLong( unit + " eliminated" );
                                toremove.add( unit );
                                break;
                            case LOSE_ONE_STEP:
                                if( !unit.loseSteps( 1 ) ) {
                                    // Unit eliminated
                                    _view.notifyInfoLong( unit + " eliminated" );
                                    toremove.add( unit );
                                }
                                break;
                            case LOSE_ONE_STEP_DRIFT_TWO_EAST:
                                if( !unit.loseSteps( 1 ) ) {
                                    // Unit eliminated
                                    _view.notifyInfoLong( unit + " eliminated" );
                                    toremove.add( unit );
                                }
                                else {
                                    // Unit still alive, drive two East
                                    driftUnit( unit, cell, -2, toremove );
                                }
                                break;
                            case LOSE_TWO_STEPS:
                                if( !unit.loseSteps( 2 ) ) {
                                    // Unit eliminated
                                    _view.notifyInfoLong( unit + " eliminated" );
                                    toremove.add( unit );
                                }
                                break;
                            case DRIFT_ONE_BOX_EAST:
                                driftUnit( unit, cell, -1, toremove );
                                break;
                            case DRIFT_TWO_BOXES_EAST:
                                driftUnit( unit, cell, -2, toremove );
                                break;
                            case DRIFT_THREE_BOXES_EAST:
                                driftUnit( unit, cell, -3, toremove );
                                break;
                            case DRIFT_FOUR_BOXES_EAST:
                                driftUnit( unit, cell, -4, toremove );
                                break;
                            case DRIFT_NINE_BOXES_EAST:
                                driftUnit( unit, cell, -9, toremove );
                                break;
                            case DRIFT_ONE_BOX_WEST:
                                driftUnit( unit, cell, 1, toremove );
                                break;
                            case DELAYED_ONE_TURN:
                                _view.notifyInfoLong( unit + " delayed 1 turn" );
                                _game.getFutureTurn( 1 ).getArrivingUnits().add( (USUnit) unit );
                                toremove.add( unit );
                                _view.getGamePanel().getBoardView().addUnitNotification( unit, "Delayed 1 Turn" );
                                break;
                            case DELAYED_TWO_TURNS:
                                _view.notifyInfoLong( unit + " delayed 2 turns" );
                                _game.getFutureTurn( 2 ).getArrivingUnits().add( (USUnit) unit );
                                toremove.add( unit );
                                _view.getGamePanel().getBoardView().addUnitNotification( unit, "Delayed 2 Turns" );
                                break;
                            case DELAYED_THREE_TURNS:
                                _view.notifyInfoLong( unit + " delayed 3 turns" );
                                _game.getFutureTurn( 3 ).getArrivingUnits().add( (USUnit) unit );
                                toremove.add( unit );
                                _view.getGamePanel().getBoardView().addUnitNotification( unit, "Delayed 3 Turns" );
                                break;
                            case PLAYER_DRIFT_1_4_BOXES_EAST:
                                // TODO Player may voluntarily drift 1-4 boxes east
                                break;
                            default:
                                _logger.warning( "Unsupported Landing Check: " + result );
                        }
                    }

                    for( Unit unit: toremove ) {
                        cell.getUnits().remove( unit );
                    }
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


    private void driftUnit( Unit unit, Cell cell, int num, List<Unit> toremove ) {
        _view.notifyInfoLong( unit + " drifted " + num + " " + (num == 1? "box": "boxes") + " " + (num < 0? "East": "West")  );
        Cell target = _game.getBoard().getRelativeLandingBox( cell, num );
        if( target != null ) {
            target.getUnits().add( unit );
            _view.getGamePanel().getBoardView().addUnitNotification( unit, "Drifting " + num + " " + (num == 1? "Box": "Boxes") + " " + (num < 0? "East": "West") );
        } else {
            // DELAY 1 Turn
            _view.notifyInfoLong( unit + " delayed 1 turn" );
            _game.getFutureTurn( 1 ).getArrivingUnits().add( (USUnit) unit );
            _view.getGamePanel().getBoardView().addUnitNotification( unit, "Delayed 1 Turn" );
        }
        toremove.add( unit );
    }


    private void handleEventPhase() {
        Turn turn = _game.getCurrentTurn();
        Phase phase = turn.getCurrentPhase();
        _logger.info( "Handle " + phase.getName() );
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


    private void applyEvent( Event event ) {
        _view.notifyInfoLong( "Event Phase" );
        EventHandler.handle( event, _model, _view );
    }

    private void handleGermanAttackPhase() {
        Turn turn = _game.getCurrentTurn();
        Phase phase = turn.getCurrentPhase();
        _logger.info( "Handle " + phase.getName() );
        int phaseProgress = phase.getProgress();

        switch (phaseProgress) {
            case GermanAttackPhase.RESET:
            {
                _view.notifyInfoLong( "German Attack Phase" );
                CellVisitor visitor = new CellVisitor() {
                    @Override
                    public boolean visit( Cell cell ) {
                        for( Unit unit: cell.getUnits() ) {
                            if( unit.getAllegiance() == Allegiance.US ) {
                                ((USUnit) unit).setHitThisTurn( false );
                            }
                        }
                        return true;
                    }
                };
                _model.getGame().getBoard().visitCells( visitor );
                phase.incProgress();
                break;
            }
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
                            if( unit.isDisrupted() )
                                _view.notifyInfo( unit + " no longer disrupted" );
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
        CellCollector collector = new CellCollector() {
            @Override
            public boolean shouldCollect(Cell cell) {
                return cell.hasAttackPosition() && cell.getAttackPositionColor() == color;
            }
        };
        _game.getBoard().visitCells( sector, collector );
        List<Cell> cells = collector.getCells();

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
            // this should not apply if the action symbol is "R" (and not playing FIRST WAVES), since the position may gain a depth marker
            if( minDepth > 1 && (turn <= Game.BEYOND_THE_BEACH_START_TURN || actionSymbol != GermanActionSymbol.RESUPPLY_REDEPLOY_REINFORCE_REOCCUPY) ) {
                boolean isOK = false;
                for( Unit unit: cell.getUnits() ) {
                    if( ((GermanUnit) unit).getDepthMarker() != null ) {
                        isOK = true;
                        break;
                    }
                }
                if( !isOK )
                    continue;
            }

            Map<Intensity, List<Cell>> fof = _game.getBoard().translateFieldOfFire( cell.getAttackPositionFieldOfFire() );

            // Don't let the action symbol be null, default to FIRE
            if( actionSymbol == null )
                actionSymbol = GermanActionSymbol.FIRE;

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
                if( cell.getUnits().size() == 0 )
                    continue;

                // Check for Concentrated Targets (5 or more steps within same cell)
                int totalSteps = 0;
                for( Unit unit: cell.getUnits() ) {
                    totalSteps += unit.getSteps();
                }

                boolean concentratedTarget = totalSteps >= 5;

                for( Unit unit: cell.getUnits() ) {
                    if( unit.getAllegiance() == Allegiance.US ) {

                        // Don't let a unit get hit more than once per turn
                        if( ((USUnit) unit).isHitThisTurn() )
                            continue;

                        // Don't hit an HQ or General unless there is an HQ Hit Bonus
                        if( (unit.getType() == UnitType.HQ || unit.getType() == UnitType.GENERAL) && !hqHitBonus )
                            continue;

                        if( intensity == Intensity.INTENSE ) {
                            // Unit loses a step
                            _view.notifyInfo( unit + " has been hit by " + attackPosition.getUnits().get( 0 ) + " from " + cell );
                            unit.nextState();
                            --maxAttacks;
                            ((USUnit) unit).setHitThisTurn( true );
                        }
                        else if( intensity == Intensity.STEADY ) {
                            // Non-armored (unless armor bonus) units with same unit symbol lose a step
                            if( (unit.getState().getSymbol() == unitSymbol || concentratedTarget) && (!unit.getType().isArmored() || armorHitBonus) ) {
                                _view.notifyInfo( unit + " has been hit by " + attackPosition.getUnits().get( 0 ) + " from " + cell );
                                unit.nextState();
                                --maxAttacks;
                                ((USUnit) unit).setHitThisTurn( true );
                            }
                        }
                        else if( intensity == Intensity.STEADY ) {
                            // Non-armored (unless armor bonus) units with same unit symbol are disrupted
                            if( (unit.getState().getSymbol() == unitSymbol || concentratedTarget) && (!unit.getType().isArmored() || armorHitBonus) ) {
                                _view.notifyInfo( unit + " has been disrupted by " + attackPosition.getUnits().get( 0 ) + " from " + cell );
                                unit.setDisrupted( true );
                                --maxAttacks;
                                ((USUnit) unit).setHitThisTurn( true );
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


    private void handleEngineerPhase() {
        Turn turn = _game.getCurrentTurn();
        Phase phase = turn.getCurrentPhase();
        _logger.info( "Handle " + phase.getName() );
        int phaseProgress = phase.getProgress();

        switch (phaseProgress) {
            case EngineerPhase.RESET_PHASE:
            {
                _view.notifyInfoLong( "Engineer Phase" );
                phase.incProgress();
                break;
            }
            case EngineerPhase.PLAYER_ACTIONS:
            {
                if( turn.getNumber() < Game.BEYOND_THE_BEACH_START_TURN ) {
                    // Clear beach
                }
                else {
                    // Command Post/Engineer Bases
                }
                phase.incProgress();
                break;
            }
            case EngineerPhase.END_PHASE:
            {
                endPhase();
                break;
            }
        }
    }


    private void endPhase() {
        _game.getCurrentTurn().getCurrentPhase().incProgress();
        _game.getCurrentTurn().nextPhase();
    }


}
