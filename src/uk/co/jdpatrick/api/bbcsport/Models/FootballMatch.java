package uk.co.jdpatrick.api.bbcsport.Models;

/**
 * Created by Jack on 26/06/2018.
 */
public abstract class FootballMatch {
    private String homeTeam, awayTeam;
    protected FixtureState state;

    public FootballMatch(String homeTeam, String awayTeam, FixtureState state) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.state = state;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public FixtureState getState() {
        return state;
    }

    @Override
    public String toString() {
        return "FootballMatch{" +
                "homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", state=" + state +
                '}';
    }
}
