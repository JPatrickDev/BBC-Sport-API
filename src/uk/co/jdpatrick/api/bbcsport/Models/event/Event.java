package uk.co.jdpatrick.api.bbcsport.Models.event;

public class Event {

    private final String player, eventName, time;
    private final TeamEnum team;


    public Event(String eventName, String player, String time, TeamEnum team) {
        this.player = player;
        this.time = time;
        this.eventName = eventName;
        this.team = team;
    }

    public String getPlayer() {
        return player;
    }

    public String getTime() {
        return time;
    }

    public String getEventName() {
        return eventName;
    }

    public TeamEnum getTeam() {
        return team;
    }

    public enum TeamEnum {
        HOME, AWAY
    }
}
