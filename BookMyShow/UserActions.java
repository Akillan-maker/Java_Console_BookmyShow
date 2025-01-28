package BookMyShow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


public class UserActions {

    public static User userLog(Scanner scan){
        System.out.println("*** Menu ***\n1. Sign in\n2. Sign up\n3. Exit");
        int ch=Integer.parseInt(scan.nextLine());
        switch (ch){
            case 1:
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
                    System.out.println("No User Id Found...");
                }
                break;
            case 2:
                String userloc=null;
                System.out.println("Enter new User ID: ");
                String newusname=scan.nextLine();
                System.out.println("Enter your location: ");
                userloc=scan.nextLine();
                System.out.println("New Password: ");
                String newpass=scan.nextLine();
                BookMyShow.getUsers().add(new User(newusname,userloc,newpass));
                System.out.println("Account Created Successfully...");
                return null;
            case 3:
                System.out.println("Signing out...");
                return null;
            default:
                System.out.println("Invalid input...");
                break;
        }
        return null;
    }

        public static void showMovies(Scanner scan, User curuser){
            LocalDate curdate=LocalDate.now();
            LocalDate sdate =curdate;
            l:while(true){
                String userloc=curuser.getUserLoc();
                System.out.println("User location: "+userloc);
                System.out.println("Current date: "+curdate);
                System.out.println("User date: "+ sdate);
                System.out.println("***  Available Movies in your location ***");

                var mlist=BookMyShow.getMoviesHashMap().keySet();
                ArrayList<Movies> moviesinarea =new ArrayList<>();
                boolean y=false;
                for(String m:mlist){
                    for(Movies mo:BookMyShow.getMoviesHashMap().get(m)){
                        if(mo.getLocation().equals(curuser.getUserLoc()) && mo.getDate().equals(sdate)){
                                y=true;
                                moviesinarea.add(mo);
                        }
                    }
                    if(y){
                        System.out.println(m);
                    }
                }
                if(moviesinarea.isEmpty()){
                    System.out.println("No Movies Found...");
                }
                System.out.println("To book show or change loc/date: ");
                String ch=scan.nextLine();
                switch(ch){
                    case "date":
                        System.out.println("Date: ");
                        LocalDate newdate = LocalDate.parse(scan.nextLine(), BookMyShow.getDateTimeFormatter());
                        if(sdate!=null){
                            sdate = newdate;
                            break;
                        }
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
                        theatreandshows(scan,moviesinarea,sdate);
                        break;
                    case "exit":
                        System.out.println("Logging out...");
                        return;
                }
            }
        }

        public static void theatreandshows(Scanner scan,ArrayList<Movies> movinarea,LocalDate userdate){
            HashMap<String, HashSet<Show>> stringHashSetHashMap=new HashMap<>();
            System.out.println("Enter movie name: ");
            String bookmov=scan.nextLine();
            for(var mov:movinarea) {
                if (mov.getMoviename().equals(bookmov)) {
                    if (stringHashSetHashMap.containsKey(mov.getTheatre().getTheatreName())) {
                        stringHashSetHashMap.get(mov.getTheatre().getTheatreName()).add(mov.getShow());
                    } else {
                        HashSet<Show> m = new HashSet<>();
                        m.add(mov.getShow());
                        stringHashSetHashMap.put(mov.getTheatre().getTheatreName(), m);
                    }
                }
            }
                for(String out:stringHashSetHashMap.keySet()){
                    System.out.println("Theatre: "+out);
                    var cobj=stringHashSetHashMap.get(out);
                    for(var s:cobj){
                        System.out.println("Shows:"+s.toString());
                    }
                }
                HashSet<Show> shows=new HashSet<>();
                LocalTime showtime=null;
                while(true){
                    System.out.println("Enter Theatre name: ");
                    String tname=scan.nextLine();

                    System.out.println("Enter Show time: ");
                    showtime= LocalTime.parse(scan.nextLine(),BookMyShow.getTimeFormatter());
                    if(!stringHashSetHashMap.containsKey(tname)){
                        System.out.println("No Theatre found...");
                        continue;
                    }
                    shows=stringHashSetHashMap.get(tname);
                    break;
                }
                for(var s:shows){
                    if(s.getStarttime().equals(showtime) && s.getDateofshow().equals(userdate)){

                        var curshow=s;
                    }
                }
                System.out.println("No shows found");
                return;
    }
}



