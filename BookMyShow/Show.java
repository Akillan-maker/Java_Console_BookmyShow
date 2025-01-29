package BookMyShow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Show {
    public LocalTime starttime;
    public LocalTime endtime;
    public LocalDate dateofshow;
    HashMap<Character, ArrayList<String>> seatsofshow=new HashMap<>();
    Screens screens;

    public Show(LocalTime starttime, LocalTime endtime, LocalDate dateofshow, Screens screens,HashMap<Character,ArrayList<String>> seatsofshow){

        this.starttime=starttime;
        this.endtime=endtime;
        this.dateofshow=dateofshow;
        this.screens=screens;
        this.seatsofshow=seatsofshow;
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

    public HashMap<Character, ArrayList<String>> getSeatsofshow() {
        return seatsofshow;
    }

    public void setSeatsofshow(HashMap<Character, ArrayList<String>> seatsofshow) {
        this.seatsofshow = seatsofshow;
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
    @Override
    public String toString() {
        return  starttime.toString();
    }
}
