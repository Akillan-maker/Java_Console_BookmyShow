package BookMyShow;

public class Screens {
    String screen_no;
    String no_seats;

    public Screens(String screen_no,String no_seats){

        this.screen_no=screen_no;
        this.no_seats= no_seats;

    }

    public String getScreen_no() {

        return screen_no;

    }

    public String getNo_seats() {

        return no_seats;

    }
}
