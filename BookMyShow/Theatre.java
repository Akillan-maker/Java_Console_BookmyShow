package BookMyShow;

import java.util.ArrayList;

public class Theatre {
    public String theatreName;
    public String location;
    public static ArrayList<Screens> screens=new ArrayList<>();

    public Theatre(String theatreName,String location){

        this.theatreName=theatreName;
        this.location=location;

    }

    public static ArrayList<Screens> getScreens() {

        return screens;

    }

    public String getTheatreName(){

        return theatreName;

    }

    public String getLocation() {

        return location;

    }
}
