package uk.co.jdpatrick.api.bbcsport.Models.match;

/**
 * Author: Jack
 * Date: 16/03/13
 */
public enum FixtureState {

    FIXTURE("Not started"),LIVE("Latest"),HALFTIME("Half Time"),FULLTIME("Full Time");

    public String name;
    FixtureState(String name){
        this.name = name;
    }
}
