package BookMyShow;

import java.util.ArrayList;

public class User {
    public String userId;     //To store use id
    public String userPass;     // To store user password
    public String userLoc;    // To store user location
    ArrayList<Ticket> ticketArrayList=new ArrayList<>();      // To store tickets of user

    public User(String userId,String userPass,String userLoc){     // Creating a user constructor

        this.userId=userId;
        this.userLoc=userLoc;
        this.userPass=userPass;

    }

    public String getUserId() {

        return userId;

    }

    public String getUserPass() {

        return userPass;

    }

    public String getUserLoc() {

        return userLoc;

    }

    public ArrayList<Ticket> getTicketArrayList() {

        return ticketArrayList;

    }

    public void setUserLoc(String userLoc) {

        this.userLoc = userLoc;

    }
}
