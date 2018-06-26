package uk.co.jdpatrick.api.bbcsport.Models;

/**
 * Author: Jack
 * Date: 26/06/2018
 */
public class HalfTimeMatch extends LiveMatch {

    public HalfTimeMatch(String home, String away, String score) {
        super(home, away,score);
        this.state = FixtureState.HALFTIME;
    }

}
