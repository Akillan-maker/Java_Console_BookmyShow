package BookMyShow;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class BookMyShow {

    public static ArrayList<Admin> admins=new ArrayList<>();         // To store admin objects
    public static ArrayList<User> users=new ArrayList<>();               // To store user objects
    public static ArrayList<String> locations=new ArrayList<>();               // To store locations
    public static HashMap<String,Theatre> theatreHashMap=new HashMap<>();              // To store theatre keys and its objects
    public static HashMap<String,ArrayList<Movies>> moviesHashMap=new HashMap<>();          // To store movie keys and its objects in ArrayList
    public static DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");         // To get the date in a given format
    public static DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("HH:mm");              // To get time in given format

    public static HashMap<String,Theatre> getTheatreHashMap() {

        return theatreHashMap;

    }

    public static HashMap<String,ArrayList<Movies>> getMoviesHashMap() {

        return moviesHashMap;

    }

    public static DateTimeFormatter getDateTimeFormatter() {

        return dateTimeFormatter;

    }

    public static DateTimeFormatter getTimeFormatter() {

        return timeFormatter;

    }

    public static ArrayList<String> getLocations() {

        return locations;

    }

    public static ArrayList<Admin> getAdmins() {

        return admins;

    }

    public static ArrayList<User> getUsers() {

        return users;

    }


}
