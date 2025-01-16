package BookMyShow;

public class User {
    public String userId;
    public String userPass;

    public User(String userId,String userPass){

        this.userId=userId;
        this.userPass=userPass;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPass() {
        return userPass;
    }
}
