package BookMyShow;

import java.util.ArrayList;
import java.util.HashMap;

public class BookMyShow {

    public static ArrayList<Admin> admins=new ArrayList<>();
    public static ArrayList<User> users=new ArrayList<>();
    public static ArrayList<String> locations=new ArrayList<>();
    public static HashMap<String,Theatre> theatreHashMap=new HashMap<>();

    public static HashMap<String,Theatre> getTheatreHashMap() {

        return theatreHashMap;

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
