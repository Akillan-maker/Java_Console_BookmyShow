package BookMyShow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Ticket {
    private String theatreName;      // To store theatre name
    private LocalDate date;       // To store show date
    private String screen;         // To store screen name
    private String movieName;        // To store movie name
    private LocalTime time;       // To store show time
    private int numerofseats;       // To store total number of seats
    private long priceofTickets;       // To store total amount
    private ArrayList<String> totalSeats=new ArrayList<>();     // To store the booked seats

    public Ticket(String theatreName,LocalDate date,String screen,String movieName,LocalTime time,long priceofTickets,int numerofseats,ArrayList<String> totalSeats){    // Creating ticket constructor

        this.theatreName=theatreName;
        this.date=date;
        this.screen=screen;
        this.movieName=movieName;
        this.time=time;
        this.numerofseats=numerofseats;
        this.priceofTickets=priceofTickets;
        this.totalSeats=totalSeats;

    }

    public String getTheatreName() {

        return theatreName;
    }

    public LocalDate getDate() {

        return date;
    }

    public String getScreen() {

        return screen;
    }

    public String getMovieName() {

        return movieName;
    }

    public LocalTime getTime() {

        return time;
    }

    public int getNumerofSeats() {

        return numerofseats;
    }

    public ArrayList<String> getTotalSeats() {

        return totalSeats;

    }

    public long getPriceofTickets() {

        return priceofTickets;

    }
}
