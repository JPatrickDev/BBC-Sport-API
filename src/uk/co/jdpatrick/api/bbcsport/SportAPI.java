package uk.co.jdpatrick.api.bbcsport;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import uk.co.jdpatrick.api.bbcsport.Models.FixtureState;
import uk.co.jdpatrick.api.bbcsport.Models.FootballFixture;
import uk.co.jdpatrick.api.bbcsport.Models.FootballMatch;
import uk.co.jdpatrick.api.bbcsport.Models.LiveMatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author: Jack
 * Date: 16/03/13
 * Last updated: 10/11/2017
 */
public class SportAPI {
    private String league;
    private String URL;

    public SportAPI() {
        this("football");
    }


    Logger log = Logger.getLogger(SportAPI.class.getName());

    public SportAPI(String league) {
        this.league = league;
        if (league.equalsIgnoreCase("football")) {
            this.URL = "http://m.bbc.co.uk/sport/football/live-scores";
        } else {
            this.URL = "http://m.bbc.co.uk/sport/football/" + league + "/live-scores";
        }

        log.log(Level.INFO, "New SportAPI object created, using league " + league);
        log.log(Level.INFO, this.URL);
    }

    public ArrayList<FootballMatch> getFixtures(boolean liveOnly) throws IOException {
        ArrayList<FootballMatch> fixtures = new ArrayList<FootballMatch>();
        Document doc = Jsoup.parse(new java.net.URL(URL), 5000);
        Elements e = doc.getElementsByClass("gs-o-list-ui__item");
        String last = "";
        for (Element el : e) {
            Elements clubs = el.getElementsByClass("sp-c-fixture__team-name-trunc");
            Elements names = clubs.select("abbr");
            String home = names.get(0).text();
            String away = names.get(1).text();

            Elements score = el.getElementsByClass("sp-c-fixture__number");
            String KO = "";
            String homeScore = "0";
            String awayScore = "";
            String time = "";
            if (score.size() == 1) {
                KO = score.get(0).text();
                FootballFixture fixture = new FootballFixture(home,away,time);
                fixtures.add(fixture);
            } else {
                homeScore = score.get(0).text();
                awayScore = score.get(1).text();
                Elements status = el.getElementsByClass("sp-c-fixture__status");
                String statusString = status.get(0).getElementsByTag("abbr").text();
                if (statusString.equalsIgnoreCase("")) {
                    //  System.out.println("Live game");
                    Elements elmt = status.get(0).getElementsByTag("span");
                    for (Element child : elmt) {
                        if (!child.text().equals("")) {
                            time = child.text();
                            break;
                        }
                    }
                } else {
                    time = statusString;
                }
                FixtureState state = FixtureState.LIVE;
                if (time.equalsIgnoreCase("HT")) {
                    state = FixtureState.HALFTIME;
                } else if (time.equalsIgnoreCase("FT")) {
                    state = FixtureState.FULLTIME;
                }
                if (!KO.equalsIgnoreCase("")) {
                    state = FixtureState.FIXTURE;
                }
                LiveMatch f = new LiveMatch(home, away, homeScore + "-" + awayScore);
                fixtures.add(f);
            }
        }

        return fixtures;
    }


}
