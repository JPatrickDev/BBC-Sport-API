package uk.co.jdpatrick.api.bbcsport.Models;

import uk.co.jdpatrick.api.bbcsport.Event;

import java.util.ArrayList;

/**
 * Author: Jack
 * Date: 26/06/2018
 */
public class FootballFixture extends FootballMatch {

    private String ko;

    public FootballFixture(String home, String away, String ko) {
        super(home, away);
        this.ko = ko;
    }

    public String getKo() {
        return ko;
    }

    public void setKo(String ko) {
        this.ko = ko;
    }
}
