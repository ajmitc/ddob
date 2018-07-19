package ddob.game.card;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private List<Card> _cards;
	private List<Card> _discard;
	
	public Deck() {
		_cards = new ArrayList<>();
		_discard = new ArrayList<>();
	}
	
	public Card draw() {
		return _cards.pop();
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