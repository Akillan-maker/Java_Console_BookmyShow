package BookMyShow;

import java.time.LocalDate;
import java.time.LocalTime;

public class Show {
    public LocalTime starttime;
    public LocalTime endtime;
    public LocalDate dateofshow;
    Screens screens;

    public Show(LocalTime starttime, LocalTime endtime, LocalDate dateofshow, Screens screens){

        this.starttime=starttime;
        this.endtime=endtime;
        this.dateofshow=dateofshow;
        this.screens=screens;
    }

    public LocalTime getStarttime() {
        return starttime;
    }

    public LocalTime getEndtime() {
        return endtime;
    }

    public LocalDate getDateofshow() {
        return dateofshow;
    }

    public Screens getScreens() {
        return screens;
    }
}
