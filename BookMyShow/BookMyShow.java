package BookMyShow;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class BookMyShow {

    private static ArrayList<Admin> admins=new ArrayList<>();         // To store admin objects
    private static ArrayList<User> users=new ArrayList<>();               // To store user objects
    private static ArrayList<String> locations=new ArrayList<>();               // To store locations
    private static HashMap<String,Theatre> theatreHashMap=new HashMap<>();              // To store theatre keys and its objects
    private static HashMap<String,ArrayList<Movies>> moviesHashMap=new HashMap<>();          // To store movie keys and its objects in ArrayList
    private static DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");         // To get the date in a given format
    private static DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("HH:mm");              // To get time in given format

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
