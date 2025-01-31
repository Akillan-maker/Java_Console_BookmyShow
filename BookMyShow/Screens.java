package BookMyShow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Screens {
    String screen_no;      // To store screen name
    int no_seats;       // To store total number of seats
    String grids;         // To store grid
    public HashMap<Character, ArrayList<String>> nseats=new HashMap<>();  // To store rows as keys and its seats as ArrayList
    public HashSet<Show> shows=new HashSet<>();      // To store show objects

    public Screens(String screen_no, int no_seats,String grids,HashMap<Character,ArrayList<String>> nseats){      // Creating a screen constructor

        this.grids=grids;
        this.nseats=nseats;
        this.screen_no=screen_no;
        this.no_seats= no_seats;

    }
    public HashSet<Show> getShows() {
        return shows;
    }

    public HashMap<Character, ArrayList<String>> getNseats() {

        return nseats;

    }

    public String getScreen_no() {

        return screen_no;

    }

    public int getNo_seats() {

        return no_seats;

    }

    public String getGrids() {
        return grids;
    }
}
