package BookMyShow;

public class Admin {
    public String adminId;
    public String adminPass;

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
