package uk.co.jdpatrick.api.bbcsport.Models.event.Card;

public class RedCardEvent extends CardEvent{
    public RedCardEvent(String player, String time, TeamEnum teamEnum) {
        super("Red Card", player, time,teamEnum);
    }
}
