package ddob.game;

import ddob.game.card.Card;

public abstract class Phase {
    public static final String LANDING_CHECK_WEST_PHASE = "Landing Check West";
    public static final String LANDING_CHECK_EAST_PHASE = "Landing Check East";
    public static final String EVENT_1_PHASE = "Event 1";
    public static final String GERMAN_ATTACK_EAST_PHASE = "German Attack East";
    public static final String GERMAN_ATTACK_WEST_PHASE = "German Attack West";
    public static final String EVENT_2_PHASE = "Event 2";
    public static final String ENGINEER_PHASE = "Engineer";
    public static final String US_ACTIONS_EAST_PHASE = "US Actions East";
    public static final String US_ACTIONS_WEST_PHASE = "US Actions West";
    public static final String END_TURN_PHASE = "End Turn";

    private String _name;

    protected Card _card;

    protected int _progress;

    public Phase( String name ) {
        _name = name;
        _card = null;
        _progress = 0;
    }

    public String getName(){ return _name; }

    public Card getCard(){ return _card; }
    public void setCard( Card c ){ _card = c; }

    public int getProgress(){ return _progress; }
    public abstract void incProgress();
}
