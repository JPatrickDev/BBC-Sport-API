package uk.co.jdpatrick.api.bbcsport.Models.event.Goal;

import uk.co.jdpatrick.api.bbcsport.Models.event.Event;

public class GoalEvent extends Event {
    public GoalEvent(String player, String time) {
        super("Goal", player, time);
    }
}
