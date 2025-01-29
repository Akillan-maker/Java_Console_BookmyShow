package BookMyShow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


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
            Show curShow = null;
            while(true){
                System.out.println("Enter Theatre name: ");
                String tname=scan.nextLine();
                if(!stringHashSetHashMap.containsKey(tname)){
                    System.out.println("No Theatre found...");
                    continue;
                }
                shows=stringHashSetHashMap.get(tname);
                System.out.println("Enter Show time: ");
                showtime= LocalTime.parse(scan.nextLine(),BookMyShow.getTimeFormatter());
                for(var s:shows){
                    if(!s.getStarttime().equals(showtime) && !s.getDateofshow().equals(userdate)){
                        System.out.println("No shows found");
                        continue;
                    }
                    curShow=s;
                }
                break;
            }
            var seatstobook=curShow.getSeatsofshow();
            for(var seats:seatstobook.entrySet()){
                System.out.println(seats.getKey()+" "+seats.getValue());
            }
            System.out.println("Enter seat_no to book: ");
            int numberOfseats =Integer.parseInt(scan.nextLine());
            ArrayList<String> allSeatstobook=new ArrayList<>();
            HashMap<Character,ArrayList<String>> dupseatsandGrids=new HashMap<>();
            for (var sh:curShow.getSeatsofshow().entrySet()){
                dupseatsandGrids.put(sh.getKey(),new ArrayList<>());
                dupseatsandGrids.get(sh.getKey()).addAll(sh.getValue());
            }
            for(int i=1;i<=numberOfseats;i++){
                dupseatsandGrids = UserActions.bookTickets(scan,i,curShow,allSeatstobook,dupseatsandGrids);
            }
            System.out.println("Confirm your Booking (y/n): ");
            String confirm=scan.nextLine();
            switch (confirm){
                case "y":
                    curShow.setSeatsofshow(dupseatsandGrids);
                    System.out.println("Booking Successful...");
                    for(var originalseatsbooked:curShow.getSeatsofshow().entrySet()){
                        System.out.println(originalseatsbooked.getKey()+" "+originalseatsbooked.getValue());
                    }
                    break;
                case "n":
                    System.out.println("Booking cancelled Successfully...");
                    break;
            }
    }


    public static HashMap<Character,ArrayList<String>> bookTickets(Scanner scan,int i,Show cshow,ArrayList<String> allseatstobook, HashMap<Character,ArrayList<String>> dupseats){

        while(true){
            System.out.println("Enter seat_no "+i+": ");
            String seat_no=scan.nextLine();
            if(allseatstobook.contains(seat_no)){
                System.out.println("Seat Already Booked...");
                continue;
            }
            Character rowName=seat_no.charAt(0);
            int seat =Integer.parseInt(seat_no.substring(1));
            var allRows=dupseats.keySet();
            if (allRows.contains(rowName)) {
                if(seat>dupseats.get(rowName).size()){
                    System.out.println("Unavailable seat...");
                    continue;
                }
                String seatGird=cshow.getScreens().getGrids();
                var spaceseat=seatGird.split("\\*");
                int sumofGrids=0;
                for(String nofSeats:spaceseat){
                    sumofGrids += Integer.parseInt(nofSeats);
                }
                String index1 =spaceseat[0];
                String index2=spaceseat[2];
                if(seat<Integer.parseInt(index1)){
                    dupseats.get(rowName).set(seat-1,"X");
                    System.out.println(dupseats.get(rowName));
                    allseatstobook.add(seat_no);
                    break;
                }
                else if(seat>Integer.parseInt(index2)){
                    dupseats.get(rowName).set(seat+1,"X");
                    System.out.println(dupseats.get(rowName));
                    allseatstobook.add(seat_no);
                    break;
                }
               else {
                    dupseats.get(rowName).set(seat,"X");
                    System.out.println(dupseats.get(rowName));
                    allseatstobook.add(seat_no);
                    break;
                }
            }
            System.out.println("no rows found");
        }
        return dupseats;
    }

    public static void Ticket(){

    }
}



