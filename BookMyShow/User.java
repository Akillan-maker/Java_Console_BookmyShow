package BookMyShow;

public class User {
    public String userId;
    public String userPass;
    public String userLoc;

    public User(String userId,String userLoc,String userPass){

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

    public void setUserLoc(String userLoc) {
        this.userLoc = userLoc;
    }
}
