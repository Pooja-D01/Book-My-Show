package BMS;

import java.time.LocalTime;
import java.util.ArrayList;

public class Tickets {//class for the movie Tickets/
    private String theatreName ;//theatrename for ticket.
    private String movieName;//movie name for ticket.
    private String screenname;//screen naem for ticket.
    private String location;//location of the show for ticket.
    private LocalTime startTime;//moviestarttime for ticket.
    private ArrayList<String> bookedTicket;//bokkedseat numbers for ticket.
    private int price;//price for ticket.

    //gettors for the theatrename, movienmae, screenname,location,starttime,bookedtickets,price.
    public String getTheatreName() {
        return theatreName;
    }
    public int getPrice() {
        return price;
    }
    public ArrayList<String> getBookedTicket() {
        return bookedTicket;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public String getLocation() {
        return location;
    }
    public String getScreenName() {
        return screenname;
    }
    public String getMovieName() {
        return movieName;
    }

    //Constructor for the Tickets class to initialize the theatrename, movienmae, screenname,location,starttime,bookedtickets,price.
    public Tickets(String theatreName, String movieName, String screenname, String location, LocalTime startTime, ArrayList<String> bookedTicket, int price) {
        //super();[implicitly calling the constructor of object class]
        this.theatreName= theatreName ;
        this.movieName=movieName;
        this.screenname= screenname;
        this.location=location;
        this.startTime = startTime;
        this.bookedTicket= bookedTicket;
        this.price= price;
    }
}
