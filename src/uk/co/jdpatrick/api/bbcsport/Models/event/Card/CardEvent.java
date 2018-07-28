package uk.co.jdpatrick.api.bbcsport.Models.event.Card;

import uk.co.jdpatrick.api.bbcsport.Models.event.Event;

public abstract class CardEvent extends Event {
    public CardEvent(String cardType, String player, String time) {
        super(cardType, player, time);
    }
}
