package BookMyShow;

import java.util.Scanner;

public class BookMyShowActions {

    public static void start(Scanner scan){
        BookMyShow.getAdmins().add(new Admin("Ad01","123"));          // Default admin object add when code begins
        BookMyShow.getUsers().add(new User("U1","1","cbe"));

        while(true){             // Loops until user exists
            System.out.println("1.Admin\n2.User\n3.Exit\nYou are: ");
            int choice=Integer.parseInt(scan.nextLine());      // To get users choice

            switch (choice){

                case 1:         // If user choice is 1
                    Admin ad= AdminActions.adminLog(scan);    // Calls admin login
                    if(ad==null){
                        break;
                    }
                    AdminActions.adminOptions(scan);    // Calls if admin login success
                    break;

                case 2:    // If user choice is 2
                    User us=UserActions.userLog(scan);    // Calls user login
                    if(us==null){
                        break;
                    }
                    UserActions.showMovies(scan,us);       // Calls if user login success
                    break;

                case 3:      // If user choice is 3
                    System.out.println("Quiting...");
                    return;

                default:   // If user choice is more than 3
                    System.out.println("Invalid choice...");
                    break;
            }

        }

    }
}
