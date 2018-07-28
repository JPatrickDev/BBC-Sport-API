package uk.co.jdpatrick.api.bbcsport.Models.match;

/**
 * Author: Jack
 * Date: 26/06/2018
 */
public class FinishedMatch extends LiveMatch {

    public FinishedMatch(String id,String home, String away, String score) {
        super(id,home, away,score);
        this.state = FixtureState.FULLTIME;
    }

    @Override
    public String toString() {
        return "FinishedMatch{}" + super.toString();
    }
}
