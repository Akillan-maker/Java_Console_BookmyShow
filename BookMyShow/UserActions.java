package BookMyShow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class UserActions {

    public static User userLog(Scanner scan){
        String userloc=null;
        System.out.println("User ID: ");
        String username=scan.nextLine();
        for(User user:BookMyShow.getUsers()){
            if(user.getUserId().equals(username)){
                System.out.println("Password: ");
                String userpass=scan.nextLine();
                if(user.getUserPass().equals(userpass)){
                    System.out.println("Login Successful...");
                    return user;
                }
                System.out.println("Incorrect Password...");
                return null;
            }
        }
        System.out.println("Your ID does not Exists...");
        System.out.println("Enter Password to create new ID...");
        System.out.println("Enter your location: ");
        userloc=scan.nextLine();
        System.out.println("New Password: ");
        String newpass=scan.nextLine();
        BookMyShow.getUsers().add(new User(username,userloc,newpass));
        System.out.println("Account Created Successfully...");
        for(User users:BookMyShow.getUsers()){
            System.out.println(users.getUserId());
        }
        return null;
    }

        public static void showMovies(Scanner scan, User curuser){

            l:while(true){
                String userloc=curuser.getUserLoc();
                System.out.println("User location: "+userloc);
                LocalDate curdate=LocalDate.now();
                System.out.println("Current date: "+curdate);
                LocalDate sdate =curdate;
                System.out.println("User date: "+ sdate);
                System.out.println("***  Available Movies in your location ***");

                var mlist=BookMyShow.getMoviesHashMap().keySet();
                for(String m:mlist){
                    for(Movies mo:BookMyShow.getMoviesHashMap().get(m)){
                        if(mo.getLocation().equals(curuser.getUserLoc()) && mo.getDate().equals(sdate)){
                            System.out.println(mo.getMoviename());
                            break;
                        }
                        System.out.println("No Movies Found...");
                        return;
                    }
                }
                System.out.println("To book show or change loc/date: ");
                String ch=scan.nextLine();
                switch(ch){
                    case "date":
                        System.out.println("Date: ");
                        LocalDate newdate = LocalDate.parse(scan.nextLine(), BookMyShow.getDateTimeFormatter());
                        sdate = newdate;
                        break;

                    case "loc":
                        System.out.println("Location: ");
                        String newloc=scan.nextLine();
                        for(var u:BookMyShow.getUsers()){
                            if(u.getUserId().equals(curuser.getUserId())){
                                u.setUserLoc(newloc);
                                continue l;
                            }
                            break;
                        }
                    case "book":

                }
        }
    }
}

