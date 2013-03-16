package uk.co.jdpatrick.api.bbcsport;

/**
 * Author: Jack
 * Date: 16/03/13
 */
public enum FixtureState {

    FIXTURE("Not started"),LIVE("Latest"),HALFTIME("Half Time"),FULLTIME("Full Time");

    String name;
    FixtureState(String name){
        this.name = name;
    }
}
