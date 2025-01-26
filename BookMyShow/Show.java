package BookMyShow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

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
    @Override
    public int hashCode() {
        return Objects.hash(starttime, endtime, dateofshow);
    }
    @Override
    public boolean equals(Object object){
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Show show = (Show) object;
        return Objects.equals(this.starttime, show.starttime) && Objects.equals(this.endtime, show.endtime) && Objects.equals(dateofshow,show.getDateofshow());

    }
}
