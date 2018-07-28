package uk.co.jdpatrick.api.bbcsport.Models.match;

/**
 * Author: Jack
 * Date: 26/06/2018
 */
public class FootballFixture extends FootballMatch {

    private String ko;

    public FootballFixture(String id,String home, String away, String ko) {
        super(id,home, away, FixtureState.FIXTURE);
        this.ko = ko;
    }

    public String getKo() {
        return ko;
    }

    public void setKo(String ko) {
        this.ko = ko;
    }

    @Override
    public String toString() {
        return "FootballFixture{" +
                "ko='" + ko + '\'' +
                '}' + super.toString();
    }
}
