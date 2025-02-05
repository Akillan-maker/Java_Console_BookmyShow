package BookMyShow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class UserActions {

    public static User userLogin(Scanner scan){      // Function for user login

        System.out.println("*** Menu ***\n1. Sign in\n2. Sign up\n3. Exit");
        int ch=Integer.parseInt(scan.nextLine());        // To get the user choice
        switch (ch){

            // Case to sign in
            case 1:       // If user choice is 1

                System.out.println("User ID: ");
                String username=scan.nextLine();       // To get user id
                for(User curuser:BookMyShow.getUsers()){     // Loops over user ArrayList
                    if(curuser.getUserId().equals(username)){      // Checks if user available
                        System.out.println("Password: ");
                        String userpass=scan.nextLine();     // To get user password
                        if(curuser.getUserPass().equals(userpass)){     // Checks if the user password matches
                            System.out.println("Login Successful...");
                            return curuser;      // Returns user object
                        }
                        System.out.println("Incorrect Password...");
                        return null;    // Returns null if password is incorrect
                    }
                }
                System.out.println("No User Id Found...");
                return null;     // Returns null is user id not found

            // Case to sign up
            case 2:     // If user choice is 2

                String userloc=null;        // To store user location
                System.out.println("Enter new User ID: ");
                String newusname=scan.nextLine();       // To get new user id
                for(User user:BookMyShow.getUsers()){         // Loops over user ArrayList
                    if(user.getUserId().equals(newusname)){       // Checks for new user id

                        System.out.println("User ID Already Exitsts...");
                        return null;   // Breaks if new user id already exists

                    }
                }
                System.out.println("Enter your location: ");
                userloc=scan.nextLine();     // To get location
                System.out.println("New Password: ");
                String newpass=scan.nextLine();    // To get new password
                BookMyShow.getUsers().add(new User(newusname,newpass,userloc));     // Adding new user object in user ArrayList
                System.out.println("Account Created Successfully...");
                return null;

            // Case to exit
            case 3:

                System.out.println("Signing out...");
                return null;

            default:

                System.out.println("Invalid input...");
                break;
        }
        return null;
    }

        public static void showMovies(Scanner scan, User curuser){     // Function to display movies to user

            LocalDate curdate=LocalDate.now();  // To store the current date
            LocalDate sdate =curdate;       // To store show date and assigning current date
            while(true){      // Loops until user exists
                String userloc=curuser.getUserLoc();     // To get and store the current user location
                System.out.println("User location: "+userloc);
                System.out.println("Current date: "+curdate);
                System.out.println("User date: "+ sdate);
                System.out.println("***  Available Movies in your location ***");

                var mlist=BookMyShow.getMoviesHashMap().keySet();    // To store movies in a ArrayList
                ArrayList<Movies> moviesinarea =new ArrayList<>();      // Creating a new ArrayList to store movies in current location
                boolean y=false;
                for(String m:mlist){     // Loops over movie name ArrayList
                    for(Movies movies:BookMyShow.getMoviesHashMap().get(m)){     // Loops movie HashMap and gets object of that movie
                        if(movies.getLocation().equals(curuser.getUserLoc()) && movies.getDate().equals(sdate)){    // Checks if the movie location and movie date matches user location and user date respectively
                                y=true;
                                moviesinarea.add(movies);   // Adds movie to moviesinares ArrayList
                        }
                    }
                    if(y){     // Checks if y is true
                        System.out.println(m);
                    }
                }
                if(moviesinarea.isEmpty()){     // Checks for empty ArrayList
                    System.out.println("No Movies Found...");
                }
                System.out.println("To book show or change loc/date: ");
                String ch=scan.nextLine();      // To get user choice
                switch(ch){

                    // Case to change user date
                    case "date":    // If user choice is "date"
                        System.out.println("Date: ");
                        LocalDate newdate = LocalDate.parse(scan.nextLine(), BookMyShow.getDateTimeFormatter());   // To get new user date and formating it
                        if(sdate!=null){    // Checks new date is not null
                            sdate = newdate;
                            break;
                        }
                        break;

                    // Case to change location of user
                    case "location":   // If user choice is "location"

                        System.out.println("Location: ");
                        String newloc=scan.nextLine();     // To get new location
                        curuser.setUserLoc(newloc);     // Sets new location
                        break;

                    // Case to book tickets
                    case "book":    // If user choice is "book"

                        boolean t=false;
                        t=theatreandshows(scan,curuser,moviesinarea,sdate);    // Calls theatreandshows function
                        if(t){

                            userOptions(scan,curuser);
                            
                        }
                        break;

                    // Case to exit
                    case "exit":    // If user choice is "exit"

                        System.out.println("Logging out...");
                        return;
                }
            }
        }

        public static boolean theatreandshows(Scanner scan, User curuser, ArrayList<Movies> moviesinarea, LocalDate userdate){       // Function that deals with theatre,shows and booking

            HashMap<String, HashSet<Show>> theateandShowsHashMap =new HashMap<>();      // Creating a new HashMap to store theatre and its object
            String bookmov;      // To store movie name to book

            l:while(true){
                System.out.println("Enter movie name: ");
                bookmov=scan.nextLine();     // To get movie name to book
                for(var mov:moviesinarea) {        // Loops over the moviesinarea ArrayList
                    if (mov.getMoviename().equals(bookmov)) {      // Checks if movie name matches
                        if (theateandShowsHashMap.containsKey(mov.getTheatre().getTheatreName())) {     // Checks for theatre name in theatreandShows HashMap
                            theateandShowsHashMap.get(mov.getTheatre().getTheatreName()).add(mov.getShow());     // Gets the theatre name and adds show objects
                            break l;
                        }
                        else {
                            HashSet<Show> shows = new HashSet<>();       // Creates a new HashSet
                            shows.add(mov.getShow());        // Adding show object inside
                            theateandShowsHashMap.put(mov.getTheatre().getTheatreName(), shows);   // To pass the theatre name and show objects
                            break l;
                        }
                    }
                }
                System.out.println("Movie Not Found...");
            }

            for(String out: theateandShowsHashMap.keySet()){       // Loops over theatreandShows HashMap
                System.out.println("Theatre: "+out);
                var showobj= theateandShowsHashMap.get(out);       // Gets the show objects and pass into an ArayList
                for(var s:showobj){             // Loops over sobj ArrayList
                    System.out.println("Shows:"+s.toString());
                }
            }

            HashSet<Show> shows=new HashSet<>();    // Declaring shows HashSet to store shows object
            LocalTime showtime=null;       // Declaring a showtime to store show time
            String theatreName;      // Declaring String theatreName to store theatre name
            Show curShow = null;       // Declaring a reference of show to store current show object

            l:while(true){        // Loops until show object is found
                System.out.println("Enter Theatre name: ");
                theatreName =scan.nextLine();     // To get theatre name to book
                if(!theateandShowsHashMap.containsKey(theatreName)){    // Checks for theatre name in Hashmap
                    System.out.println("No Theatre found...");
                    continue;
                }
                shows= theateandShowsHashMap.get(theatreName);     // Getting show objects of theatre name from HashMap
                System.out.println("Enter Show time: ");
                showtime= LocalTime.parse(scan.nextLine(),BookMyShow.getTimeFormatter());     // To get show time
                for(var s:shows){    // Loops over shows HashSet
                    if(!s.getStarttime().equals(showtime)){   // Checks for show time macthes
                        System.out.println("No shows found");
                        continue l;   // Continues while loop
                    }
                    curShow=s;     // Assign the current show object
                }
                break;    // Breaks while loop
            }
            var seatstobook=curShow.getSeatsofshow();    // Getting the seats and gris of current show
            for(var seats:seatstobook.entrySet()){     // Loops over seatstobook and gets both keys and values
                System.out.println(seats.getKey()+" "+seats.getValue());
            }
            System.out.println("Enter seat_no to book: ");
            int numberOfseats =Integer.parseInt(scan.nextLine());        // To get total number of seats to book

            ArrayList<String> bookedSeats =new ArrayList<>();        //  Creating a new ArrayList to store booked seats
            HashMap<Character,ArrayList<String>> dupseatsandGrids=new HashMap<>();        // Creating a new HashMap to store seats and grids
            long totalPrice=0;     // To store totalprice of booked ticket
            int numofbookesSeats = 0;       // To store total number of booked seats

            for (var showseats :curShow.getSeatsofshow().entrySet()){       // Loops over SeatsofShow HashMap
                dupseatsandGrids.put(showseats.getKey(),new ArrayList<>());         // Gets the key and creates a new ArrayList and pass into the HashMap
                dupseatsandGrids.get(showseats.getKey()).addAll(showseats.getValue());        // Gets the key and adds object into the key's ArrayList
            }
            for(int i=1;i<=numberOfseats;i++){        // Loops until i gets equals to number of seats to book
                dupseatsandGrids = UserActions.bookTickets(scan,i,curShow, bookedSeats,dupseatsandGrids);    // Calls bookTickets function
            }
            System.out.println("Confirm your Booking (y/n): ");
            String confirm=scan.nextLine();       // To get user choice
            switch (confirm){

                // Case to confirm booked seats
                case "y":        // If user choice is "y"
                    curShow.setSeatsofshow(dupseatsandGrids);      // Sets dupseatsandGrids to seatsofshow
                    numofbookesSeats = bookedSeats.size();      //  Getting number of booked seats
                    totalPrice= (long) numofbookesSeats *190;      // Calculating total ticket price

                    // Creating a new ticket object and pasing inside ArrayList
                    curuser.getTicketArrayList().add(new Ticket(theatreName,curShow.getDateofshow(),curShow.getScreens().getScreen_no(),bookmov,curShow.getStarttime(),totalPrice, numofbookesSeats, bookedSeats));
                    System.out.println("Booking Successful...");

                    // Printing ticket
                    System.out.println("Your Ticket: \n");
                    System.out.println("*************** "+theatreName+" ***************");

                    System.out.println("Date: "+curShow.getDateofshow()+"          "+"Time: "+curShow.getStarttime());
                    System.out.println("  ------------ "+bookmov+" ------------");
                    System.out.println(" ******* ScreenName: "+curShow.getScreens().getScreen_no()+" *******");
                    System.out.println("No.of Seats: "+ numofbookesSeats);
                    for(String s: bookedSeats){
                        System.out.print(s+",");

                    }
                    System.out.println();
                    System.out.println("                                       Total Amount: "+totalPrice);
                    System.out.println();
                    System.out.println("**************** Booked Ticket *********************");
//                    for(var originalseatsbooked:curShow.getSeatsofshow().entrySet()){    // Loops over seatsofshow
//                        System.out.println(originalseatsbooked.getKey()+" "+originalseatsbooked.getValue());
//                    }
                    break;

                // Case to cancel booked seats
                case "n":      // If user choice is"n"
                    System.out.println("Booking cancelled Successfully...");
                    break;
            }
            return true;
    }

    public static void userOptions(Scanner scan,User curuser){

        System.out.println("Do you want to 1) Continue booking\n2) view Ticket\n3) Exit\n");
        int ch=Integer.parseInt(scan.nextLine());     // To gte user choice
        switch (ch){

            // Case to continue booking
            case 1:   // If user choice is 1

                showMovies(scan,curuser);     // Calls showMovies
                break;

            // Case to view tickets
            case 2:      // If user choice is 2

                viewTicket(curuser);      // Calls viewTicket
                break;

            // Case to exit
            case 3:     // If user choice is 3

                System.out.println("Logging out...");
                break;
        }

    }

    // Function to book seats
    public static HashMap<Character,ArrayList<String>> bookTickets(Scanner scan,int i,Show cshow,ArrayList<String> allseatstobook, HashMap<Character,ArrayList<String>> dupseats){

        while(true){
            System.out.println("Enter seat_no "+i+": ");
            String seat_no=scan.nextLine();             // To get the seat name
            if(allseatstobook.contains(seat_no)){           // Checks for seat name in ArrayList
                System.out.println("Seat Already Booked...");
                continue;         // Continues while loop
            }
            Character rowName=seat_no.charAt(0);        // Gets the first character in seat name
            int seat =Integer.parseInt(seat_no.substring(1));      // Gets number after the from index 1
            var allRows=dupseats.keySet();      // Gets the row names and store it in a ArrayList
            if (allRows.contains(rowName)) {       // Checks for the row name
                if(seat>dupseats.get(rowName).size()-2){            // Checks if seat number exists row size
                    System.out.println("Unavailable seat...");
                    continue;      // Continues while loop
                }
                String seatGird=cshow.getScreens().getGrids();       // Getting seat grids
                var spaceseat=seatGird.split("\\*");        // Splitting grids
                int sumofGrids=0;            // To store sum of grids
                for(String nofSeats:spaceseat){           // Loops over spaceseat ArrayList
                    sumofGrids += Integer.parseInt(nofSeats);          // Adding grid numbers
                }
                String index1 =spaceseat[0];             // To store value in index 0 from ArrayList
                String index2=spaceseat[2];          // To store value in index 2 from ArrayList
                if(seat<=Integer.parseInt(index1)){         // Checks if given seat is smaller than value in index1
                    if(dupseats.get(rowName).get(seat).equals("X")){       // Checks wheather the seat is already booked
                        System.out.println("Seat Already Booked...");
                        continue;     // Continues while loop
                    }
                    dupseats.get(rowName).set(seat-1,"X");     // Gets row name and sets the index value as X
                    System.out.println(dupseats.get(rowName));
                    allseatstobook.add(seat_no);          // Adds the seat name into an ArrayList
                    break;   // Breaks while loop
                }
                else if(seat>=(sumofGrids+1)-Integer.parseInt(index2))     // Checks if seat is greater than that index2 value
                {
                    if(dupseats.get(rowName).get(seat).equals("X")){      // Checks wheather the seat is already booked
                        System.out.println("Seat Already Booked...");
                        continue;      // Continues while loop
                    }
                    dupseats.get(rowName).set(seat+1,"X");        // Gets row name and sets the index value as X
                    System.out.println(dupseats.get(rowName));
                    allseatstobook.add(seat_no);        // Adds the seat name into an ArrayList
                    break;        // Breaks while loop
                }
               else {
                    if(dupseats.get(rowName).get(seat).equals("X")){       // Checks wheather the seat is already booked
                        System.out.println("Seat Already Booked...");
                        continue;        // Continues while loop
                    }
                    dupseats.get(rowName).set(seat,"X");           // Gets row name and sets the index value as X
                    System.out.println(dupseats.get(rowName));
                    allseatstobook.add(seat_no);          // Adds the seat name into an ArrayList
                    break;         // Breaks while loop
                }
            }
            System.out.println("no rows found");
        }
        return dupseats;
    }

    public static void viewTicket(User curuser){   // To view booked tickets

        int i=1;
        for(var information:curuser.getTicketArrayList()) {    // Loops over ticket ArrayList
            if(information==null) {

                System.out.println("No Tickets Found...");
                break;

            }
                System.out.println();
                System.out.println("Ticket " + i + ": \n");
                System.out.println("*************** " + information.getTheatreName() + " ***************");

                System.out.println("Date: " + information.getDate() + "          " + "Time: " + information.getTime());
                System.out.println("  ------------ " + information.getMovieName() + " ------------");
                System.out.println(" ******* ScreenName: " + information.getScreen() + " *******");
                System.out.println("No.of Seats: " + information.getTotalSeats());
                for (String s : information.getTotalSeats()) {
                    System.out.print(s + ",");

                }
                System.out.println();
                System.out.println("                                   Total Amount: " + information.getPriceofTickets());
                System.out.println();
                System.out.println("************ Booked Ticket **************");
                i++;

        }

    }
}



