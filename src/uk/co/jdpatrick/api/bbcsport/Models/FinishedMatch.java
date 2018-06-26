package uk.co.jdpatrick.api.bbcsport.Models;

import uk.co.jdpatrick.api.bbcsport.Event;

import java.util.ArrayList;

/**
 * Author: Jack
 * Date: 26/06/2018
 */
public class FinishedMatch extends LiveMatch {

    public FinishedMatch(String home, String away, String score) {
        super(home, away,score);
        this.state = FixtureState.FULLTIME;
    }

    @Override
    public String toString() {
        return "FinishedMatch{}" + super.toString();
    }
}
