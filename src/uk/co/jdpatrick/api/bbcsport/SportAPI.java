package uk.co.jdpatrick.api.bbcsport;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author: Jack
 * Date: 16/03/13
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
    }

    public ArrayList<Fixture> getFixtures(boolean liveOnly) throws IOException {
        ArrayList<Fixture> fixtures = new ArrayList<Fixture>();
        Document doc = Jsoup.parse(new java.net.URL(URL), 5000);
        fixtures.addAll(getLiveMatches(doc));
        fixtures.addAll(getHalfTimeMatches(doc));
        if(!liveOnly)
        fixtures.addAll(getFullTimeMatches(doc));
        if(!liveOnly){
            Elements e = doc.getElementsByClass("type-fixture");
            String last = "";
            for (Element el : e) {
                Elements clubs = el.getElementsByClass("club");
                for (Element club : clubs) {
                    String text = "";
                    if (!club.tagName().equalsIgnoreCase("abbr")) {
                        text = club.text();
                    } else {
                        Elements abbr = club.getElementsByTag("span");
                        text = abbr.text();
                    }
                    String ko = el.getElementsByClass("ko").text();
                    if (last.equalsIgnoreCase("")) {
                        last = text;
                    } else {
                        String home = last;
                        String away = text;
                        last = "";
                        Fixture fix = new Fixture(home, away, "0-0",FixtureState.FIXTURE);
                        fix.setKo(ko);
                        fixtures.add(fix);
                    }
                }
            }
        }
        return fixtures;
    }

    private ArrayList<Fixture> getLiveMatches(Document doc){
    ArrayList<Fixture> fixtures = new ArrayList<Fixture>();
        Elements e = doc.getElementsByClass("type-live");
        String last = "";
        for (Element el : e) {
            Elements clubs = el.getElementsByClass("club");
            for (Element club : clubs) {
                String text = "";
                String score = el.getElementsByClass("score").text();
                if (!club.tagName().equalsIgnoreCase("abbr")) {
                    text = club.text();
                } else {
                    Elements abbr = club.getElementsByTag("span");
                    text = abbr.text();
                }
                if (last.equalsIgnoreCase("")) {
                    last = text;
                } else {
                    String home = last;
                    String away = text;
                    last = "";
                    String[] scoreSplit = score.split(" ");
                    String scoreFormatted = scoreSplit[0] + "-" + scoreSplit[1];
                    Fixture fixture = new Fixture(home, away, scoreFormatted,FixtureState.LIVE);
                    ArrayList<Event> events = this.loadEvents(el);
                    fixture.setEvents(events);
                    fixtures.add(fixture);
                }
            }
        }
        return fixtures;
    }

    private ArrayList<Fixture> getHalfTimeMatches(Document doc){
        ArrayList<Fixture> fixtures = new ArrayList<Fixture>();
        Elements e = doc.getElementsByClass("type-halftime");
        String last = "";
        for (Element el : e) {
            Elements clubs = el.getElementsByClass("club");
            for (Element club : clubs) {
                String text = "";
                String score = el.getElementsByClass("score").text();
                if (!club.tagName().equalsIgnoreCase("abbr")) {
                    text = club.text();
                } else {
                    Elements abbr = club.getElementsByTag("span");
                    text = abbr.text();
                }
                if (last.equalsIgnoreCase("")) {
                    last = text;
                } else {
                    String home = last;
                    String away = text;
                    last = "";
                    String[] scoreSplit = score.split(" ");
                    String scoreFormatted = scoreSplit[0] + "-" + scoreSplit[1];
                    Fixture fixture =new Fixture(home, away, scoreFormatted,FixtureState.HALFTIME);
                    ArrayList<Event> events = this.loadEvents(el);
                    fixture.setEvents(events);
                    fixtures.add(fixture);
    }
}
}
        return fixtures;
}

private ArrayList<Fixture> getFullTimeMatches(Document doc){
        ArrayList<Fixture> fixtures = new ArrayList<Fixture>();
Elements e = doc.getElementsByClass("type-result");
        String last = "";
        for (Element el : e) {
            Elements clubs = el.getElementsByClass("club");
            for (Element club : clubs) {
                String text = "";
                String score = el.getElementsByClass("score").text();
                if (!club.tagName().equalsIgnoreCase("abbr")) {
                    text = club.text();
                } else {
                    Elements abbr = club.getElementsByTag("span");
                    text = abbr.text();
                }
                if (last.equalsIgnoreCase("")) {
                    last = text;
                } else {
                    String home = last;
                    String away = text;
                    last = "";
                    String[] scoreSplit = score.split(" ");
                    String scoreFormatted = scoreSplit[0] + "-" + scoreSplit[1];
                    Fixture fixture = new Fixture(home, away, scoreFormatted,FixtureState.FULLTIME);
                    ArrayList<Event> events = this.loadEvents(el);
                    fixture.setEvents(events);
                    fixtures.add(fixture);
                }
            }
        }
        return fixtures;
    }


    private ArrayList<Event> loadEvents(Element fixture){
        try{
        Element info = fixture.getElementsByClass("info").get(0);
        Elements eventLi = info.getElementsByTag("li");
        ArrayList<Event> events = new ArrayList<Event>();
        for(Element e : eventLi){
            String team = e.getElementsByClass("visually-hidden").get(0).text();
            String event = e.getElementsByClass("spr-football").get(0).text();
            String[] classSplit =e.getElementsByClass("spr-football").get(0).className().split(" ");
            String bbcEvent = classSplit[classSplit.length-1];
            String[] pinfo = e.getElementsByClass("player").get(0).text().split(" ");
            String name = pinfo[0];
            String time = "";
            for(int i = 1;i!= pinfo.length;i++){
                time+=pinfo[i];
            }
            events.add(new Event(EventType.getFromBBCName(bbcEvent),time,name,team));
        }
        return events;
        }catch(Exception ex){return new ArrayList<Event>();}
    }

}
