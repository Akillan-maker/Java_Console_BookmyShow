package BookMyShow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AdminActions {


    public static Admin adminLog(Scanner scan) {

        System.out.println("Admin ID: ");
        String adminname = scan.nextLine();
        for (Admin admin : BookMyShow.getAdmins()) {
            if (admin.getAdminId().equals(adminname)) {
                System.out.println("Password: ");
                String adminpass = scan.nextLine();
                if (admin.getAdminPass().equals(adminpass)) {
                    System.out.println("Login Successful...");
                    return admin;
                }
                System.out.println("Incorrect Password...");
                return null;
            }
        }
        System.out.println("No Admin Found");
        System.out.println("Login Failed...");
        return null;
    }


    public static void adminOptions(Scanner scan) {
        while (true) {
            System.out.println("1) Add Locations\n2) Add Theatre\n3) Add Movie\n4) View Movies\n5) View Theatres\n6) Exit\nEnter your choice: ");
            int choice = Integer.parseInt(scan.nextLine());
            switch (choice) {

                case 1:
                    AdminActions.addLocations(scan);
                    break;

                case 2:
                    AdminActions.addTheatre(scan);
                    break;
                case 3:
                    AdminActions.addMovie(scan);
                    break;
                case 4:
                    AdminActions.viewMovies();
                    break;
                case 5:
                    AdminActions.viewTheatres();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Ivalid choice...Retry");
            }
        }
    }

    public static void addLocations(Scanner scan) {

        System.out.println("Enter locations: ");
        String loc = scan.nextLine();
        for (String availableLoc : BookMyShow.getLocations()) {
            if (availableLoc.equals(loc)) {
                System.out.println("Locations Already Exists...");
                return;
            }
        }
        BookMyShow.getLocations().add(loc);
        System.out.println("Location Added Successfully...");
        for (String l : BookMyShow.getLocations()) {
            System.out.println(l);
        }
    }

    public static void addTheatre(Scanner scan) {

        System.out.println("Theatre name: ");
        String tname = scan.nextLine();
        for (String theatres : BookMyShow.getTheatreHashMap().keySet()) {
            if (theatres.equals(tname)) {

                System.out.println("Theatre Already exists...");
                return;
            }
        }
        System.out.println("Location: ");
        String loc = scan.nextLine();
        int srcs = 0;
        for (String l : BookMyShow.getLocations()) {
            if (l.equals(loc)) {
                ;
                while (true) {
                    System.out.println("No.of Screens: ");
                    srcs = Integer.parseInt(scan.nextLine());
                    if (srcs == 0) {
                        System.out.println("");
                        continue;
                    }
                    break;
                }
                HashMap<String, Screens> screensHashMap = new HashMap<>();
                for (int i = 0; i < srcs; i++) {
                    AdminActions.addScreen(scan, screensHashMap);
                }
                Theatre theatres = new Theatre(tname, loc, screensHashMap);
                BookMyShow.getTheatreHashMap().put(tname, theatres);
                System.out.println("Theatre Added Successfully...");
                return;
            }
        }
        System.out.println("Location Not Found...");
    }

    public static void addScreen(Scanner scan, HashMap<String, Screens> screensHashMap) {

        System.out.println("Screen name: ");
        String src = scan.nextLine();
        var screenKeys = screensHashMap.keySet();
        if (screenKeys.contains(src)) {
            System.out.println("Screen Already exists...");
            return;
        }
        System.out.println("No.of seats: ");
        int seats = Integer.parseInt(scan.nextLine());
        System.out.println("Enter Grids: ");
        String grids = scan.nextLine();
        var nseats = Utilities.grids(seats, grids);
        for (var seatss : nseats.entrySet()) {
            System.out.println(seatss.getKey() + " " + seatss.getValue());
        }
        Screens scrObj = new Screens(src, seats, grids, nseats);
        screensHashMap.put(src, scrObj);
    }

    public static void addMovie(Scanner scan) {

        int i = 1;
        System.out.println("*** Available locations ***");
        for (String loc : BookMyShow.getLocations()) {
            System.out.println(i + "." + " " + loc);
            i++;
        }
        String locmovie = null;
        while (true) {
            System.out.println("Enter location: ");
            locmovie = scan.nextLine();
            if (!BookMyShow.getLocations().contains(locmovie)) {
                System.out.println("Location Not found...Retry");
                continue;
            }
            break;
        }
        System.out.println("Movie name: ");
        String mname = scan.nextLine();
        System.out.println("Date of show(dd-mm-yyyy): ");
        LocalDate date = LocalDate.parse(scan.nextLine(), BookMyShow.getDateTimeFormatter());
        System.out.println("Movie duration: ");
        Long duration = Long.parseLong(scan.nextLine());
        i = 1;
        HashMap<String,ArrayList<Movies>> moviesHashMap=new HashMap<>();
        System.out.println("*** Available Theatres ***");
        for (var theatre : BookMyShow.getTheatreHashMap().keySet()) {
            if (BookMyShow.getTheatreHashMap().get(theatre).getLocation().equals(locmovie)) {

                System.out.println(i + "." + " " + theatre);
                i++;
            }
        }
        String atheatre = null;
        while (true) {
            System.out.println("Enter theatre name to add movie: ");
            atheatre = scan.nextLine();
            if (!BookMyShow.getTheatreHashMap().containsKey(atheatre)) {
                System.out.println("No Theatre Found...Retry");
                continue;
            }
            break;
        }
        Theatre theatre1 = BookMyShow.getTheatreHashMap().get(atheatre);
        i = 1;
        System.out.println("*** Available Screens ***");
        for (var screens : theatre1.getScreensHashMap().keySet()) {

            System.out.println(i + "." + " " + screens);
            i++;

        }
        String sname = null;
        while (true) {
            System.out.println("Enter screen to add movie: ");
            sname = scan.nextLine();
            var sc = theatre1.getScreensHashMap().keySet();
            if (!sc.contains(sname)) {
                System.out.println("No screens found...");
                continue;
            }
            break;
        }
        var screen1 = theatre1.getScreensHashMap().get(sname);

        System.out.println("Enter start time: ");
        LocalTime stime = LocalTime.parse(scan.nextLine(), BookMyShow.getTimeFormatter());
        System.out.println("End time -> ");
        LocalTime etime = stime.plusMinutes(duration + 30);
        System.out.println("Enter show date: ");
        LocalDate dshow = LocalDate.parse(scan.nextLine(), BookMyShow.getDateTimeFormatter());

        Show show1 = new Show(stime, etime, dshow, screen1);
//        if(screen1.getShows().equals(show1)){
//            System.out.println("Show Already exists...");
//            continue;
//        }
        screen1.getShows().add(show1);

        Movies movie1 = new Movies(mname, locmovie, date, duration, theatre1, screen1, show1);

        var movies=BookMyShow.getMoviesHashMap().get(mname);
        if(movies==null){
            movies=new ArrayList<>();
        }
        movies.add(movie1);
        BookMyShow.getMoviesHashMap().put(mname,movies);
    }

    public static void viewTheatres() {

        int i = 1;
        System.out.println("*** Available Theatres ***");
        for (String theatre : BookMyShow.getTheatreHashMap().keySet()) {
            if (theatre == null) {
                System.out.println("No Theatres Found...");
                return;
            }
            System.out.println(i + "." + " " + theatre);
            i++;
        }
    }

    public static void viewMovies(){
        int i=1;
        System.out.println("*** Available Movies ***");
        for(String mov:BookMyShow.getMoviesHashMap().keySet()){
            if(mov == null){
                System.out.println("No Movies Found...");
                return;
            }
            System.out.println(i+"."+" "+mov);
            i++;
        }
    }
}







