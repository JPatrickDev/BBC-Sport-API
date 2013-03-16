package uk.co.jdpatrick.api.bbcsport;

/**
 * Author: Jack
 * Date: 16/03/13
 */
public class Fixture {

    String home;
    String away;
    String score;
    FixtureState state;
    public Fixture(String home,String away,String score,FixtureState state){
        this.home = home;
        this.away = away;
        this.score = score;
        this.state = state;
    }

    @Override
    public String toString(){
        return home + "-" + away + "(" + score + ")" + state.name;
    }
}
