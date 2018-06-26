package uk.co.jdpatrick.api.bbcsport.Models;

import uk.co.jdpatrick.api.bbcsport.Event;

import java.util.ArrayList;

/**
 * Author: Jack
 * Date: 16/03/13
 */
public class LiveMatch extends FootballMatch {

    private String score;
    ArrayList<Event> events = new ArrayList<Event>();

    public LiveMatch(String home, String away, String score) {
        super(home, away);
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


    @Override
    public String toString() {
        return "LiveMatch{" +
                "score='" + score + '\'' +
                ", events=" + events +
                '}';
    }
}
