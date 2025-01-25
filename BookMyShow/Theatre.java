package BookMyShow;

import java.util.ArrayList;
import java.util.HashMap;

public class Theatre {
    public String theatreName;
    public String location;
    public  HashMap<String,Screens> screensHashMap=new HashMap<>();

    public Theatre(String theatreName,String location,HashMap<String,Screens> screensHashMap){

//        this.screens=screens;
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
