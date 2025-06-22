package BMS;
//import sttements for the Arraylist, hashmap,hashset.
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Screen {//Screen pojo class
    private String screenname;//name of the screen
    private  int availableseat  ;//for the avialable no of seats
    private int nofseats;//no of seats
    private HashMap<Character , ArrayList<String>> seatingarrange;//seating arrangement HAshmap
    private HashSet<Show> shows = new HashSet<>();//Hashset of Show type to store the Show object.
    private String grid ;//The seat grid value for the screen

    //constructor to the Screen class to instialize the screenname,noofseats,seatarrangement and grid.
    public Screen(String screenname,int nofseats,HashMap<Character,ArrayList<String>> seatingarrange, String grid) {
        //super();[implicitly calling the constructor of object class]
        this.screenname = screenname;
        this.nofseats = nofseats;
        this.availableseat = nofseats;
        this.seatingarrange = seatingarrange;
        this.grid = grid;
    }


    //gettors for the instialize the screenname,noofseats,seatarrangement ,shows ,  availableseats and grid.
    public String getScreenname() {
        return screenname;
    }

    public int getNofseats() {
        return nofseats;
    }

    public HashMap<Character, ArrayList<String>> getSeatingarrange() {
        return seatingarrange;
    }

    public HashSet<Show> getShows() {
        return shows;
    }

    public String getgrid() {
        return grid;
    }

    public  int getAvailableseat() {
        return availableseat;
    }

    public  void setAvailableseat(int availableseat) {
        this.availableseat = availableseat;
    }
}
