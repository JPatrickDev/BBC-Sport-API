package uk.co.jdpatrick.api.bbcsport.Models.match;

/**
 * Author: Jack
 * Date: 26/06/2018
 */
public class HalfTimeMatch extends LiveMatch {

    public HalfTimeMatch(String id,String home, String away, String score) {
        super(id,home, away,score);
        this.state = FixtureState.HALFTIME;
    }

}
