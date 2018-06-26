import uk.co.jdpatrick.api.bbcsport.Models.FootballMatch;
import uk.co.jdpatrick.api.bbcsport.SportAPI;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        SportAPI api = new SportAPI();
        ArrayList<FootballMatch> fixtures = api.getFixtures(false);
        for(FootballMatch f : fixtures){
            System.out.println(f);
        }

    }
}
