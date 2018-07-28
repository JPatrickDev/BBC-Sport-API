package uk.co.jdpatrick.api.bbcsport.Models.event;

public class Event {

    private final String player, eventName, time;


    public Event(String eventName, String player, String time) {
        this.player = player;
        this.time = time;
        this.eventName = eventName;
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
    
}
