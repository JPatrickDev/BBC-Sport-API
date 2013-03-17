package uk.co.jdpatrick.api.bbcsport;

/**
 * Author: Jack
 * Date: 16/03/13
 */
public enum EventType {

    GOAL("Goal"), REDCARD("Red Card"), YELLOWCARD("Yellow Card");

    String name;

    EventType(String name) {
        this.name = name;
    }

    public static EventType getFromBBCName(String name) {
        EventType type = null;
        if (name.contains("goal"))
            type = EventType.GOAL;
        else if (name.contains("dismissal"))
            type = EventType.REDCARD;
         return type;

    }
}
