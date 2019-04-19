package uk.co.jdpatrick.api.bbcsport.Models.event.Card;

public class YellowCardEvent extends CardEvent {
    public YellowCardEvent(String player, String time, TeamEnum teamEnum) {
        super("Yellow Card", player, time, teamEnum);
    }
}
