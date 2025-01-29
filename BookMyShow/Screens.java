package BookMyShow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Screens {
    String screen_no;
    int no_seats;
    String grids;
    public HashMap<Character, ArrayList<String>> nseats=new HashMap<>();
    public HashSet<Show> shows=new HashSet<>();

    public Screens(String screen_no, int no_seats,String grids,HashMap<Character,ArrayList<String>> nseats){

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
