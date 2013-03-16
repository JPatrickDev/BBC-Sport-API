package uk.co.jdpatrick.api.bbcsport;

/**
 * Author: Jack
 * Date: 16/03/13
 */
public class Event {

    private EventType type;
    private String time;
    private String player;
    private String team;

    public Event(EventType type, String time, String player,String team) {
        this.type = type;
        this.time = time;
        this.player = player;
        this.team = team;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Event{" +
                "type=" + type +
                ", time='" + time + '\'' +
                ", player='" + player + '\'' +
                '}';
    }
}
