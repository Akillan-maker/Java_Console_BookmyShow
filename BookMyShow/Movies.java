package BookMyShow;

import java.time.LocalDate;

public class Movies {
    String moviename;
    LocalDate date;
    String location;
    long duration;
    Theatre theatre;
    Screens screens;
    Show show;

    public Movies(String moviename,String location,LocalDate date,long duration,Theatre theatre,Screens screens,Show show){

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

    public long getDuration() {

        return duration;

    }

    public LocalDate getDate() {

        return date;

    }

    public Theatre getTheatre() {

        return theatre;

    }

    public Screens getScreens() {

        return screens;

    }

    public Show getShow() {

        return show;

    }
}
