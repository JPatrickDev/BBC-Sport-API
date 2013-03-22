package uk.co.jdpatrick.api.bbcsport;

import java.util.ArrayList;

/**
 * Author: Jack
 * Date: 16/03/13
 */
public class Fixture {

    String home;
    String away;
    String score;
    FixtureState state;
    ArrayList<Event> events = new ArrayList<Event>();
    private String ko;

    public Fixture(String home,String away,String score,FixtureState state){
        this.home = home;
        this.away = away;
        this.score = score;
        this.state = state;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public FixtureState getState() {
        return state;
    }

    public void setState(FixtureState state) {
        this.state = state;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    @Override
    public String toString(){
        String str = home + "-" + away + "(" + score + ")" + state.name + ":";
        for(Event e: this.getEvents()){
            str += e.toString();
        }
        if(getState() == FixtureState.FIXTURE)
            str += ".." + getKo();
        return str;
    }

    public String getKo() {
        if(this.getState() == FixtureState.FIXTURE)
        return ko;
        else
            return "Game started";
    }

    public void setKo(String ko) {
        this.ko = ko;
    }
}
