package BookMyShow;

import java.util.Scanner;


public class UserActions {

    public static User userLog(Scanner scan){
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
        System.out.println("Enter Password to create new ID...\n");
        System.out.println("Create Password: ");
        String newpass=scan.nextLine();
        BookMyShow.getUsers().add(new User(username,newpass));
        System.out.println("Account Created Successfully...");
        for(User users:BookMyShow.getUsers()){
            System.out.println(users.getUserId());
        }
        return null;
        }
}
