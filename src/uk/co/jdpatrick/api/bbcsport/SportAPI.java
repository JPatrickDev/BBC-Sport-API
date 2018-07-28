package uk.co.jdpatrick.api.bbcsport;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import uk.co.jdpatrick.api.bbcsport.Models.match.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author: Jack
 * Date: 16/03/13
 * Last updated: 14/07/2018
 */
public class SportAPI {
    private final boolean htmlunit;
    private final String league;
    private final String url;

    public SportAPI(boolean htmlunit) {
        this("football", htmlunit);
    }


    Logger log = Logger.getLogger(SportAPI.class.getName());

    public SportAPI(String league, boolean htmlunit) {
        this.league = league;
        if (league.equalsIgnoreCase("football")) {
            this.url = "https://www.bbc.co.uk/sport/football/scores-fixtures";
        } else {
            this.url = "https://www.bbc.co.uk/sport/football/" + league + "/scores-fixtures";
        }
        this.htmlunit = htmlunit;
        log.log(Level.INFO, "New SportAPI object created, using league " + league);
        log.log(Level.INFO, this.url);
        log.log(Level.INFO, "HTMLUnit: " + this.htmlunit);

        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");

        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
    }

    public ArrayList<FootballMatch> getFixtures(boolean liveOnly) throws IOException {
        ArrayList<FootballMatch> fixtures = new ArrayList<FootballMatch>();
        Document doc = getDocument();
        Elements e = doc.getElementsByClass("gs-o-list-ui__item");
        for (Element el : e) {
            String id = null;
            try {
                Elements aTag = el.getElementsByClass("sp-c-fixture__block-link");
                id = aTag.get(0).attr("href").split("/")[3];
            } catch (Exception exe) {
            }


            Elements clubs = el.getElementsByClass("sp-c-fixture__team-name-trunc");
            Elements names = clubs.select("abbr");
            String home = names.get(0).text();
            String away = names.get(1).text();

            Elements score = el.getElementsByClass("sp-c-fixture__number");
            String KO = "";
            String homeScore = "";
            String awayScore = "";
            String time = "";
            if (score.size() == 1) {
                if (!liveOnly) {
                    KO = score.get(0).text();
                    FootballFixture fixture = new FootballFixture(id, home, away, KO);
                    fixtures.add(fixture);
                }
            } else {
                homeScore = score.get(0).text();
                awayScore = score.get(1).text();
                Elements status = el.getElementsByClass("sp-c-fixture__status");
                String statusString = status.get(0).getElementsByTag("abbr").text();
                if (statusString.equalsIgnoreCase("")) {
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


                FootballMatch match = null;
                if (time.equalsIgnoreCase("HT")) {
                    if (!liveOnly)
                        match = new HalfTimeMatch(id, home, away, homeScore + "-" + awayScore);
                } else if (time.equalsIgnoreCase("FT")) {
                    if (!liveOnly)
                        match = new FinishedMatch(id, home, away, homeScore + "-" + awayScore);
                } else {
                    match = new LiveMatch(id, home, away, homeScore + "-" + awayScore);
                }
                if (match != null)
                    fixtures.add(match);
            }
        }
        return fixtures;
    }

    public Document getDocument() throws IOException {
        if (htmlunit) {
            WebClient webClient = new WebClient();
            HtmlPage page = webClient.getPage(this.url);
            //Seems to be the smallest wait that reliably gives enough time for the page to have all the matches
            webClient.waitForBackgroundJavaScript(1500);
            return Jsoup.parse(page.asXml());
        } else {
            return Jsoup.parse(getText(this.url));
        }
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

    public void async(FixtureCallback callback, boolean liveOnly) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ArrayList<FootballMatch> fixtures = getFixtures(liveOnly);
                    callback.callback(fixtures);
                } catch (IOException e) {
                    callback.error(e);
                }
            }
        }).start();
    }

    public interface FixtureCallback {
        void callback(ArrayList<FootballMatch> fixtures);

        void error(IOException ex);
    }


}
