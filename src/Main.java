import uk.co.jdpatrick.api.bbcsport.Models.match.FootballMatch;
import uk.co.jdpatrick.api.bbcsport.Models.match.LiveMatch;
import uk.co.jdpatrick.api.bbcsport.SportAPI;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        SportAPI api = new SportAPI(true);
        api.async(new SportAPI.FixtureCallback() {
            @Override
            public void callback(ArrayList<FootballMatch> fixtures) {
                for (FootballMatch f : fixtures) {
                    if(f instanceof LiveMatch){
                        LiveMatch match = (LiveMatch) f;
                        try {
                            match.loadEvents();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(f);
                }
            }

            @Override
            public void error(IOException ex) {
                ex.printStackTrace();
            }
        }, false);
    }
}
