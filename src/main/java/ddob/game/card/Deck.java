package ddob.game.card;

import ddob.game.GermanActionSymbol;
import ddob.game.unit.UnitSymbol;
import ddob.game.board.Color;
import ddob.game.event.Event;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private List<Card> _cards;
	private List<Card> _discard;
	
	public Deck() {
		_cards = new ArrayList<>();
		_discard = new ArrayList<>();

		// 1
		// 09
        _cards.add( new Card( "Card_09",
                LandingCheckColumn.A, LandingCheckColumn.D, LandingCheckColumn.A, false,
                Event.SMOKE_WN66_WN68, Event.REINFORCEMENTS_A_D_E, Event.CONCEALED_CIRCLE_DIAMOND,
                Color.YELLOW, 1, GermanActionSymbol.ARMOR_HIT_BONUS,
                Color.PURPLE, 2, GermanActionSymbol.RESUPPLY_REDEPLOY_REINFORCE_REOCCUPY,
                Color.BLUE, 1, GermanActionSymbol.MORTAR, UnitSymbol.DIAMOND ) );
        // 12
        _cards.add( new Card( "Card_12",
                LandingCheckColumn.A, LandingCheckColumn.B, LandingCheckColumn.A, true,
                Event.HERO_29_DEPTH_MARKER, Event.ARTILLERY_PREVENTS_ENGINEER_OPS, Event.CONCEALED_CIRCLE_TRIANGLE,
                Color.YELLOW, 1, GermanActionSymbol.RESUPPLY_REDEPLOY_REINFORCE_REOCCUPY,
                Color.PURPLE, 2, GermanActionSymbol.PATROL,
                Color.GREEN, 2, GermanActionSymbol.STAR, UnitSymbol.CIRCLE ) );
        // 13
        _cards.add( new Card( "Card_13",
                LandingCheckColumn.A, LandingCheckColumn.B, LandingCheckColumn.D, false,
                Event.REINFORCEMENTS_A_D, Event.CAPTURE_PLANS_A_C_D_E, Event.REINFORCEMENTS_C_E,
                Color.YELLOW, 2, GermanActionSymbol.ARMOR_HIT_BONUS,
                Color.PURPLE, 1, GermanActionSymbol.RESUPPLY_REDEPLOY_REINFORCE_REOCCUPY,
                Color.BROWN, 1, GermanActionSymbol.MORTAR, UnitSymbol.DIAMOND ) );
        // 18
        _cards.add( new Card( "Card_18",
                LandingCheckColumn.D, LandingCheckColumn.B, LandingCheckColumn.A, false,
                Event.INIATIVE, Event.INIATIVE, Event.LOST_BOAT_TEAMS_CATCH_UP_1,
                Color.YELLOW, 1, GermanActionSymbol.RESUPPLY_REDEPLOY_REINFORCE_REOCCUPY,
                Color.GREEN, 1, GermanActionSymbol.MORTAR,
                Color.BROWN, 2, GermanActionSymbol.ARMOR_HIT_BONUS, UnitSymbol.TRIANGLE ) );
        // 20
        _cards.add( new Card( "Card_20",
                LandingCheckColumn.B, LandingCheckColumn.A, LandingCheckColumn.D, false,
                Event.DEPTH_MARKER_EACH_SECTOR, Event.REINFORCEMENTS_A_C_G, Event.REINFORCEMENTS_A_C_G,
                Color.RED, 1, GermanActionSymbol.MORTAR,
                Color.PURPLE, 1, GermanActionSymbol.PATROL,
                Color.BLUE, 2, GermanActionSymbol.STAR, UnitSymbol.DIAMOND ) );
        // 27
        _cards.add( new Card( "Card_27",
                LandingCheckColumn.B, LandingCheckColumn.C, LandingCheckColumn.A, false,
                Event.DEPTH_MARKER, Event.DEPTH_MARKER, Event.REMOVE_DISRUPTION,
                Color.RED, 2, GermanActionSymbol.ADVANCE_AMBUSH_ARTILLERY,
                Color.BLUE, 1, GermanActionSymbol.STAR,
                Color.GREEN, 2, GermanActionSymbol.ADVANCE_AMBUSH_ARTILLERY, UnitSymbol.TRIANGLE ) );
        // 29
        _cards.add( new Card( "Card_29",
                LandingCheckColumn.C, LandingCheckColumn.A, LandingCheckColumn.B, false,
                Event.DEPTH_MARKER, Event.REINFORCEMENTS_C_D_G, Event.CONCEALED_CIRCLE_TRIANGLE,
                Color.RED, 2, GermanActionSymbol.STAR,
                Color.BLUE, 2, GermanActionSymbol.ADVANCE_AMBUSH_ARTILLERY,
                Color.BROWN, 1, GermanActionSymbol.RESUPPLY_REDEPLOY_REINFORCE_REOCCUPY, UnitSymbol.TRIANGLE ) );
	}
	
	public Card draw() {
		return _cards.remove( 0 );
	}
	
	public void discard( Card card ) {
		_discard.add( card );
	}
	
	public void shuffle() {
		_cards.addAll( _discard );
		Collections.shuffle( _cards );
	}
	
	public List<Card> getCards(){ return _cards; }
	public List<Card> getDiscard(){ return _discard; }
}