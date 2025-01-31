package BookMyShow;

public class Admin {
    public String adminId;      // To store adminID
    public String adminPass;        // To store admin password

    public Admin(String adminId,String adminPass){

        this.adminId=adminId;
        this.adminPass=adminPass;

    }

    public String getAdminId() {

        return adminId;

    }

    public String getAdminPass() {

        return adminPass;

    }
}
