package uk.co.jdpatrick.api.bbcsport.Models.match;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import uk.co.jdpatrick.api.bbcsport.Event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Author: Jack
 * Date: 16/03/13
 */
public class LiveMatch extends FootballMatch {

    private String score;
    private ArrayList<Event> events = new ArrayList<Event>();

    public LiveMatch(String id, String home, String away, String score) {
        super(id, home, away, FixtureState.LIVE);
        this.score = score;

    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }


    public void loadEvents() throws IOException {
        if(this.getId() == null || this.getId().equalsIgnoreCase("")){
            return;
        }
        String url = "https://www.bbc.co.uk/sport/football/" + this.getId();
        Document doc =  Jsoup.parse(getText(url));
        Element header = doc.getElementsByClass("match-overview-header").get(0);
        Element details = header.getAllElements().get(0);
        Element eventListsContainer = details.getElementsByTag("aside").get(0);
        Elements eventLists = eventListsContainer.getElementsByTag("ul");
        Elements homeEvents = eventLists.get(0).getElementsByTag("li");
        Elements awayEvents = eventLists.get(1).getElementsByTag("li");


    }

    public static String getText(String url) throws IOException {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        return response.toString();
    }

    @Override
    public String toString() {
        return "LiveMatch{" +
                "score='" + score + '\'' +
                ", events=" + events +
                '}' + super.toString();
    }
}
