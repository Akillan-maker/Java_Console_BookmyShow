package BookMyShow;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BookMyShow {

    public static ArrayList<Admin> admins=new ArrayList<>();
    public static ArrayList<User> users=new ArrayList<>();
    public static ArrayList<String> locations=new ArrayList<>();
    public static HashMap<String,Theatre> theatreHashMap=new HashMap<>();
    public static HashMap<String,ArrayList<Movies>> moviesHashMap=new HashMap<>();
    public static DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("HH:mm");

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
