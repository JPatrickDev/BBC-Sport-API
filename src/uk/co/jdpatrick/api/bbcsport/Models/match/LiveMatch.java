package uk.co.jdpatrick.api.bbcsport.Models.match;

import uk.co.jdpatrick.api.bbcsport.Event;

import java.util.ArrayList;

/**
 * Author: Jack
 * Date: 16/03/13
 */
public class LiveMatch extends FootballMatch {

    private String score;
    private ArrayList<Event> events = new ArrayList<Event>();

    public LiveMatch(String id, String home, String away, String score) {
        super(id, home, away, FixtureState.LIVE);
        this.score = score;

    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }


    public void loadEvents() {
        //TODO
    }

    @Override
    public String toString() {
        return "LiveMatch{" +
                "score='" + score + '\'' +
                ", events=" + events +
                '}' + super.toString();
    }
}
