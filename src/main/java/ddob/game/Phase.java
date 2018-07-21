package ddob.game;

import ddob.game.card.Card;

public class Phase {
    public static final String LANDING_CHECK_WEST_PHASE = "Landing Check West";
    public static final String LANDING_CHECK_EAST_PHASE = "Landing Check East";

    private String _name;

    protected Card _card;

    public Phase( String name ) {
        _name = name;
        _card = null;
    }

    public String getName(){ return _name; }

    public Card getCard(){ return _card; }
    public void setCard( Card c ){ _card = c; }
}
