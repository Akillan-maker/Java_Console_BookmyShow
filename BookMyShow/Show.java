package BookMyShow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Show {
    public LocalTime starttime;    // To store start time of show
    public LocalTime endtime;    // To store end time of show
    public LocalDate dateofshow;      // To store show date
    HashMap<Character, ArrayList<String>> seatsofshow=new HashMap<>();   // To store seats and grids od show
    Screens screens;

    public Show(LocalTime starttime, LocalTime endtime, LocalDate dateofshow, Screens screens,HashMap<Character,ArrayList<String>> seatsofshow){   // Creating a show constructor

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

    // Overriding methods from object class
    // These methods are called only when contains() or equals is used in show

    @Override
    public int hashCode() {     // To Generates a hash key

        return Objects.hash(starttime, endtime, dateofshow);

    }

    @Override
    public boolean equals(Object object){    // To check the show start time and endtime matches or not

        if (object == null || getClass() != object.getClass()) {    // Checks if the object type is  show
            return false;
        }
        Show show = (Show) object;   // Casting the object into show object
        // Checks and return the object
        return Objects.equals(this.starttime, show.starttime) && Objects.equals(this.endtime, show.endtime) && Objects.equals(dateofshow,show.getDateofshow());

    }

    @Override
    public String toString() {   // To display the start time

        return  starttime.toString();

    }
}
