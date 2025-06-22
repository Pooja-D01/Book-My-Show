package BMS;

import java.util.ArrayList;

public class User  extends Account{//User pojo class
    String location ;//location for the user
    private ArrayList<Tickets> ticket = new ArrayList<>();//ticketlist to store the ticket object.

    //constructor to the User class to instializ ethe username,pass and location.
    public User(String name,int pass,String location){
        super(name,pass);//calling the constructor if the superclass (Account).
        this.location=location;
    }

    //gettors for the location,ticket,name and pass
    public String getLocation(){ return location;}
    public ArrayList<Tickets> getTicket() {return ticket;}


    //settor for the location.
    public void setLocation(String location){
        this.location=location;
    }
}
