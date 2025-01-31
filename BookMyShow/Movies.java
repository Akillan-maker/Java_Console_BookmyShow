package BookMyShow;

import java.time.LocalDate;

public class Movies {
    String moviename;    // To store movie name
    LocalDate date;        // To store movie date
    String location;    // To store location
    long duration;         // To store movie duration
    Theatre theatre;         // To get theatre reference
    Screens screens;      // To get screen reference
    Show show;         // To get show reference

    public Movies(String moviename,String location,LocalDate date,long duration,Theatre theatre,Screens screens,Show show){   // Creating a movie constructor and passing parameters

        this.moviename=moviename;
        this.location=location;
        this.date=date;
        this.duration=duration;
        this.theatre=theatre;
        this.screens=screens;
        this.show=show;

    }

    public String getMoviename(){

        return moviename;
    }

    public String getLocation(){

        return location;

    }

    public LocalDate getDate() {

        return date;

    }

    public Theatre getTheatre() {

        return theatre;

    }

    public Show getShow() {

        return show;

    }
}
