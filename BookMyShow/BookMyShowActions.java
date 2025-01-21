package BookMyShow;

import java.util.Scanner;

public class BookMyShowActions {

    public static void start(Scanner scan){
        BookMyShow.getAdmins().add(new Admin("Ad01","123"));
        BookMyShow.getUsers().add(new User("u1","1"));

        while(true){
            System.out.println("1.Admin\n2.User\n3.Exit\nYou are: ");
            int choice=Integer.parseInt(scan.nextLine());

            switch (choice){
                case 1:
                    Admin ad= AdminActions.adminLog(scan);
                    if(ad==null){
                        break;
                    }
                    AdminActions.adminOptions(scan);
                    break;

                case 2:
                    User us=UserActions.userLog(scan);
                    if(us==null){

                        break;
                    }
                    break;

                case 3:
                    System.out.println("Quiting...");
                    break;

                default:
                    System.out.println("Invalid choice...");
            }

        }

    }
}
