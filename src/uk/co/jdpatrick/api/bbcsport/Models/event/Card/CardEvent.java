package uk.co.jdpatrick.api.bbcsport.Models.event.Card;

import uk.co.jdpatrick.api.bbcsport.Models.event.Event;

public abstract class CardEvent extends Event {
    public CardEvent(String cardType, String player, String time, TeamEnum teamEnum) {
        super(cardType, player, time,teamEnum);
    }
}
