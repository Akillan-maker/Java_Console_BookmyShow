package BookMyShow;

import java.util.HashMap;

public class Theatre {
    public String theatreName;        // To store theatre name
    public String location;        // To store theatre location
    public  HashMap<String,Screens> screensHashMap=new HashMap<>();    // To store screen name and its objects

    public Theatre(String theatreName,String location,HashMap<String,Screens> screensHashMap){       // Creating theatre constructor

        this.screensHashMap=screensHashMap;
        this.theatreName=theatreName;
        this.location=location;

    }

    public HashMap<String,Screens> getScreensHashMap() {

        return screensHashMap;

    }

    public String getTheatreName(){

        return theatreName;

    }

    public String getLocation() {

        return location;

    }
}
