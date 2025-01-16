package BookMyShow;

import java.util.ArrayList;
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
        return;
    }

    public static void addTheatre(Scanner scan){

        System.out.println("Theatre name: ");
        String tname=scan.nextLine();
        for(Theatre theatre:BookMyShow.getTheatres()){
            if(theatre.equals(tname)){
                System.out.println("Theatre Already exists...");
                return;
            }
        }
        System.out.println("Location: ");
        String loc=scan.nextLine();
        for(String l:BookMyShow.getLocations()){
            if(l.equals(loc)){
                System.out.println("Screen: ");
                String scr_no= scan.nextLine();
                System.out.println("No.of Seats: ");
                String seats= scan.nextLine();
                Theatre.getScreens().add(new Screens(scr_no,seats));
                BookMyShow.getTheatres().add(new Theatre(tname,loc));
                System.out.println("Theatre Added Successfully...");
                return;
            }
        }
        System.out.println("Location Not Found...");
        return;
    }
}
