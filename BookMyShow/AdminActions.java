package BookMyShow;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdminActions {


    public static Admin adminLog(Scanner scan){

        System.out.println("Admin ID: ");
        String adminname=scan.nextLine();
        for(Admin admin:BookMyShow.getAdmins()){
            if(admin.getAdminId().equals(adminname)){
                System.out.println("Password: ");
                String adminpass=scan.nextLine();
                if (admin.getAdminPass().equals(adminpass)){
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


    public static void adminOptions(Scanner scan){
        while(true){
            System.out.println("1) Add Locations\n2) Add Theatre");
            int choice=Integer.parseInt(scan.nextLine());
            switch (choice) {

                case 1:
                    AdminActions.addLocations(scan);
                    break;

                case 2:
                    AdminActions.addTheatre(scan);
                    break;







            }
        }
    }

    public static void addLocations(Scanner scan){

        System.out.println("Enter locations: ");
        String loc=scan.nextLine();
        for(String availableLoc:BookMyShow.getLocations()){
            if(availableLoc.equals(loc)){
                System.out.println("Locations Already Exists...");
                return;
            }
        }
        BookMyShow.getLocations().add(loc);
        System.out.println("Location Added Successfully...");
        for(String l:BookMyShow.getLocations()){
            System.out.println(l);
        }
    }

    public static void addTheatre(Scanner scan) {

        System.out.println("Theatre name: ");
        String tname= scan.nextLine();
        for(String theatre:BookMyShow.getTheatreHashMap().keySet()){
            if(theatre.equals(tname)){

                System.out.println("Theatre Already exists...");
                return;
            }
        }
        System.out.println("Location: ");
        String loc= scan.nextLine();
        for(String l:BookMyShow.getLocations()){
            if(l.equals(loc)){
                System.out.println("No.of Screens: ");
                int srcs= Integer.parseInt(scan.nextLine());
                HashMap<String,Screens> screensHashMap=new HashMap<>();
                for(int i=0;i<srcs;i++){
                    System.out.println("Screen name: ");
                    String src= scan.nextLine();
                    for(var s:Theatre.getScreensHashMap().keySet()){
                        if(s.equals(src)){
                            System.out.println("Screen Already exists...");
                            return;
                        }
                    }
                    System.out.println("No.of seats: ");
                    int seats=Integer.parseInt(scan.nextLine());
                    System.out.println("Enter Grids: ");
                    String grids= scan.nextLine();
                    var nseats=Utilities.grids(seats,grids);
                    Screens scrObj = new Screens(src,seats,grids,nseats);
                    Theatre.getScreensHashMap().put(src,scrObj);

                    Theatre theatres = new Theatre(tname,loc,screensHashMap);
                    BookMyShow.getTheatreHashMap().put(tname,theatres);

//                    for(String theatre:BookMyShow.getTheatreHashMap().keySet()){
//                        System.out.println(theatre);
//                    }
//                    for(Map.Entry<String,Screens> screens:Theatre.getScreensHashMap().entrySet()){
//                        System.out.println(screens.getKey()+screens.getValue());
//                    }
                    for(var seatss:nseats.entrySet()){
                        System.out.println(seatss.getKey()+" "+seatss.getValue());
                        return;
                    }
                }
            }
        }
        System.out.println("Location Not Found...");
    }
}
