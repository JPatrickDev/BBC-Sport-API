package uk.co.jdpatrick.api.bbcsport.Models.match;

/**
 * Created by Jack on 26/06/2018.
 */
public abstract class FootballMatch {
    private String homeTeam, awayTeam,id;
    protected FixtureState state;

    public FootballMatch(String id,String homeTeam, String awayTeam, FixtureState state) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.state = state;
        this.id = id;
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


    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "FootballMatch{" +
                "homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", id='" + id + '\'' +
                ", state=" + state +
                '}';
    }
}
