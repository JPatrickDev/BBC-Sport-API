import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import uk.co.jdpatrick.api.bbcsport.Fixture;
import uk.co.jdpatrick.api.bbcsport.SportAPI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        SportAPI api = new SportAPI();
        ArrayList<Fixture> fixtures = api.getFixtures(false);
        for(Fixture f : fixtures){
            System.out.println(f);
        }
    }
}
