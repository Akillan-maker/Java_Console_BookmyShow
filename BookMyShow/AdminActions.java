package BookMyShow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AdminActions {


    public static Admin adminLogin(Scanner scan) {             // Checks the login conditions

        System.out.println("Admin ID: ");
        String adminname = scan.nextLine();       // To get admin id
        for (Admin admin : BookMyShow.getAdmins()) {
            if (admin.getAdminId().equals(adminname)) {   // Loops over admin object and gets admin id
                System.out.println("Password: ");
                String adminpass = scan.nextLine();
                if (admin.getAdminPass().equals(adminpass)) {    // Loops over admin object and get admin password
                    System.out.println("Login Successful...");
                    return admin;           // returns admin object
                }
                System.out.println("Incorrect Password...");
                return null;            // returns null if no password foung
            }
        }
        System.out.println("No Admin Found");
        System.out.println("Login Failed...");
        return null;          // Retruns null if no admin id is found
    }


    public static void adminOptions(Scanner scan) {    // Asks for admin options to do

        while (true) {               // Loops until admin choose exit
            System.out.println("1) Add Locations\n2) Add Theatre\n3) Add Movie\n4) View Movies\n5) View Theatres\n6) Exit\nEnter your choice: ");
            int choice = Integer.parseInt(scan.nextLine());         // Gets the choice of admin
            switch (choice) {

                case 1:                                   // If choice is 1
                    AdminActions.addLocations(scan);  // To add locations
                    break;
                case 2:                                 // If choice is 2
                    AdminActions.addTheatre(scan);    // To add theatres
                    break;
                case 3:                                // If choice is 3
                    AdminActions.addMovie(scan);    // To add movies
                    break;
                case 4:                             // If choice is 4
                    AdminActions.viewMovies();    // To view all added movie
                    break;
                case 5:                              // If choice is 5
                    AdminActions.viewTheatres();  // To view all the theatres
                    break;
                case 6:                               // If choice is 6
                    System.out.println("Logging out...");  // To exit
                    return;
                default:                                   // If choice is more than 6
                    System.out.println("Ivalid choice...Retry");
            }
        }
    }

    public static void addLocations(Scanner scan) {     // To add locations

        System.out.println("Enter locations: ");
        String loc = scan.nextLine();               // To get location from admin
        for (String availableLoc : BookMyShow.getLocations()) {  // Loops over ArrayList of locations
            if (availableLoc.equals(loc)) {          // Checks wheather location is already available
                System.out.println("Locations Already Exists...");
                return;
            }
        }
        BookMyShow.getLocations().add(loc);      // Adding location in ArrayList of locations
        System.out.println("Location Added Successfully...");
        for (String l : BookMyShow.getLocations()) {
            System.out.println(l);
        }
    }

    public static void addTheatre(Scanner scan) {   // To add theatres

        System.out.println("Theatre name: ");
        String tname = scan.nextLine();            // To get the theatre name
        for (String theatres : BookMyShow.getTheatreHashMap().keySet()) {   // Loops over theatre HashMap
            if (theatres.equals(tname)) {     // Checks for theatre name

                System.out.println("Theatre Already exists...");
                return;                // If found then return
            }
        }
        System.out.println("*** Available locations ***");
        for (String loc : BookMyShow.getLocations()) {      // Loops over location ArrayList
            System.out.println( "> " + loc);
        }

        System.out.println("Location: ");
        String location = scan.nextLine();        // To get the location
        int screens = 0;     // To store number of screens to add
        boolean locationFound =false;

        for (String l : BookMyShow.getLocations()) {    // Loops over location ArrayList
            if (l.equals(location)) {        // Checks wheather the location is available
                locationFound =true;
                break;
            }
        }
        if(locationFound){
            while (true) {        // Loops until the number of screen is greater than 0
                System.out.println("No.of Screens: ");
                screens = Integer.parseInt(scan.nextLine());   // To get the number of screens and converting it into Integer
                if (screens == 0) {     // Checks if no.of screens is 0
                    System.out.println("Enter screen atleast one or more...");
                    continue;    // Continue the loop
                }
                break;    // Breaks the while loop
            }
            HashMap<String, Screens> screensHashMap = new HashMap<>();   // To get the screen objects
            for (int i = 1; i <= screens; i++) {      // Loops till i becomes false
                AdminActions.addScreen(scan,i,screensHashMap);   // Calls the method
            }
            Theatre theatres = new Theatre(tname, location, screensHashMap);   // Creating a new theatre object and stores information
            BookMyShow.getTheatreHashMap().put(tname, theatres);   // Put the theatre name and object inside theatre HashMap
            System.out.println("Theatre Added Successfully...");
            return;
        }
        System.out.println("Location Not Found...");
    }

    public static void addScreen(Scanner scan,int i, HashMap<String, Screens> screensHashMap) {   // To add screens

        String screen;    // To store screen name
        int seats=0;   // To store total seats in screen
        String grids;    // To divide the seats into grids
        HashMap<Character, ArrayList<String>> nseats;   // To store rowname and seats pattern
        while(true){    // Loops until the screen is added
            System.out.println("Screen "+i+" name: ");
            screen = scan.nextLine();    // To get the screen name
            var screenKeys = screensHashMap.keySet();   // Storing keys og screen HashMap as ArrayList
            if (screenKeys.contains(screen)) {      // Checks if ArrayList contains key
                System.out.println("Screen Already exists...");
                continue;     // If found then continue
            }
            System.out.println("No.of seats: ");
            seats = Integer.parseInt(scan.nextLine());  // To get total no.of seats

            System.out.println("Enter Grids (eg: 5*5*5): ");
            grids = scan.nextLine();          // To get the grids to arrange seats

            nseats = Utilities.grids(seats, grids);   // Calling Utilities class and passing parameter and assigning it to a HashMap

            for (var seatss : nseats.entrySet()) {   // Loops over both keys and values
                System.out.println(seatss.getKey() + " " + seatss.getValue());
            }
            break;    // Breaks the while loop
        }
        Screens scrObj = new Screens(screen, seats, grids, nseats);   // Creating a new screen object
        screensHashMap.put(screen, scrObj);      // Passing the key and object in HashMap
    }

    public static void addMovie(Scanner scan) {    // To add movies

        int i = 1;
        System.out.println("*** Available locations ***");
        for (String loc : BookMyShow.getLocations()) {   // Loops over location ArrayList
            System.out.println(i + "." + " " + loc);
            i++;
        }
        String locmovie;   // To store the location
        while (true) {
            System.out.println("Enter location: ");
            locmovie = scan.nextLine();    // To get the location
            if (!BookMyShow.getLocations().contains(locmovie)) {   // Checks wheather the location ArraList does not contain loaction
                System.out.println("Location Not found...Retry");
                continue;     // Continues while loop
            }
            break;   // Breaks while loop
        }
        System.out.println("Movie name: ");
        String mname = scan.nextLine();     // To get movie name
        System.out.println("Date of movie(dd-mm-yyyy): ");
        LocalDate date = LocalDate.parse(scan.nextLine(), BookMyShow.getDateTimeFormatter());  // To get date as string and parsing it in date format
        System.out.println("Movie duration: ");
        Long duration = Long.parseLong(scan.nextLine());   // To get duration of movie
        i = 1;

        System.out.println("*** Available Theatres ***");
        for (var theatre : BookMyShow.getTheatreHashMap().keySet()) {    // Loops over theatre HashMap and gets key
            if (BookMyShow.getTheatreHashMap().get(theatre).getLocation().equals(locmovie)) {   // Checks wheather theatre location matches

                System.out.println(i + "." + " " + theatre);
                i++;
            }
        }
        String atheatre;     // To store theatre name
        while (true) {   // Loops until theatre is found
            System.out.println("Enter theatre name to add movie: ");
            atheatre = scan.nextLine();    // To get theatre name
            if (!BookMyShow.getTheatreHashMap().containsKey(atheatre)) {  // Checks wheather theatre name does not exists
                System.out.println("No Theatre Found...Retry");
                continue;  // Continues while loop
            }
            break;   // Breaks while loop if theatre found
        }
        Theatre theatre1 = BookMyShow.getTheatreHashMap().get(atheatre);   // Assigning the theatre object
        i = 1;
        System.out.println("*** Available Screens ***");
        for (var screens : theatre1.getScreensHashMap().keySet()) {   // Loops over screen HashMap and gets key

            System.out.println(i + "." + " " + screens);
            i++;

        }
        String sname;     // To store screen name
        while (true) {    // Loops until correct input of screen
            System.out.println("Enter screen to add movie: ");
            sname = scan.nextLine();   // To get screen name
            var screens = theatre1.getScreensHashMap().keySet();  // To store screen name in a ArrayLists
            if (!screens.contains(sname)) {          // Checks wheather sc ArrayList does not contain
                System.out.println("No screens found...");
                continue;       // Continues while loop
            }
            break;
        }
        var screen1 = theatre1.getScreensHashMap().get(sname);     // Assigning the screen object
        LocalTime stime;             // To store start time
        LocalTime etime;          // To store end time
        LocalDate dshow;               // To store date
        HashMap<Character, ArrayList<String>> seatsofshow;   //  To store seats and grids
        Show show1;           // to store current show object

        m:while(true){                  // Loops until the current show is added
            System.out.println("Enter start time: ");
            stime = LocalTime.parse(scan.nextLine(), BookMyShow.getTimeFormatter());     // To get start time of show
            etime = stime.plusMinutes(duration + 30);            // Calculate end time and stores
            System.out.println("Enter show date: ");
            dshow = LocalDate.parse(scan.nextLine(), BookMyShow.getDateTimeFormatter());    // To get show date

            for(var shows:screen1.getShows()){    // Loops over current screen object
                if(shows.getDateofshow().equals(dshow)){          // Checks the date of show
                    if(!shows.getStarttime().isBefore(stime) && !shows.getEndtime().isBefore(stime)   // Checks the start time and end time of current show with already existing show objects
                            ||!shows.getStarttime().isAfter(etime) && !shows.getEndtime().isAfter(etime)){
                        System.out.println("Show Already exists...");
                        continue m;     // Continues the while loop
                    }
                }
            }

            seatsofshow=Utilities.grids(screen1.getNo_seats(),screen1.getGrids());   // To get the seats and grids for the current show
            show1 = new Show(stime, etime, dshow, screen1,seatsofshow);       // Creating a new show object
            if(screen1.getShows().contains(show1)){         // Checks for the already existing shows
                System.out.println("Show Already exists...");
                continue;    // Continues the while loop
            }
            break; // Breaks while loop
        }
        screen1.getShows().add(show1);     // Adding show object in current screen
        Movies movie1 = new Movies(mname, locmovie, date, duration, theatre1, screen1, show1);    // Creating a new movie object
        var movies=BookMyShow.getMoviesHashMap().get(mname);    // To get and store the movie names in ArrayList
        if(movies==null){         // Checks for null value
            movies=new ArrayList<>();     // Creates a new ArrayList
        }
        movies.add(movie1);     // Adding movie into the ArrayList
        BookMyShow.getMoviesHashMap().put(mname,movies);    // Passing the movie name and its object to HashMap
        System.out.println("Movie Added Successfully...");
    }

    public static void viewTheatres() {    // To view all Theatres

        int i = 1;
        System.out.println("*** Available Theatres ***");
        if (BookMyShow.getTheatreHashMap().isEmpty()) {        // Checks null1
            System.out.println("No Theatres Found...");
            return;       // return if null found
        }

        for (String theatre : BookMyShow.getTheatreHashMap().keySet()) {   // Loops over the theatre HashMap and get its keys
            Theatre ctheatre=BookMyShow.getTheatreHashMap().get(theatre);     // To get and store the key's object

            System.out.println(i + ")" + "Theatre name: " + theatre);
            System.out.println("Location >"+ctheatre.getLocation());
            System.out.println("Screens >"+ctheatre.getScreensHashMap().keySet());
            i++;
        }
    }

    public static void viewMovies(){     // To view all movies

        int i=1;
        System.out.println("*** Available Movies ***");

            if(BookMyShow.getMoviesHashMap().isEmpty()){          // Checks for null
                System.out.println("No Movies Found...");
                return;      // Returns if null
            }

        for(String mov:BookMyShow.getMoviesHashMap().keySet()) {        // Loops over movie HashMap and gets keys
            System.out.println(i + ") " + mov);
            System.out.println("Location: ");
            i++;
            var movshows = BookMyShow.getMoviesHashMap().get(mov);      // To get and store movie object
            for (var m : movshows) {            // Loops over the movie object ArrayList
                System.out.println("Theatre: " + m.getTheatre().getTheatreName());
                System.out.println("Location: " + m.getTheatre().getLocation());
                System.out.println("Shows: " + m.getShow().getStarttime());
            }
        }
    }
}







