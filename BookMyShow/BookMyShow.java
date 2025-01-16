package BookMyShow;

import java.util.ArrayList;

public class BookMyShow {

    public static ArrayList<Admin> admins=new ArrayList<>();
    public static ArrayList<User> users=new ArrayList<>();
    public static ArrayList<String> locations=new ArrayList<>();
    public static ArrayList<Theatre> theatres=new ArrayList<>();

    public static ArrayList<Theatre> getTheatres() {

        return theatres;
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
