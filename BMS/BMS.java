package BMS;
//impoer statement for the DateTimeFormatter ,Arraylist,hashmap.
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class BMS {//BMS pojo class

    private static ArrayList<User> userlist = new ArrayList<>();//ArrayList for users
    private static ArrayList<Admin> adminlist = new ArrayList<>();//Arraylist for admin
    private static HashMap<String, Theatre> theatrenametheatreobj=new HashMap<>();//Hashmap for theatre.
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");//DateTimeFormtter type var for starttime and endtime.
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");//DateTimeFormtter type var for date of show.
    private static HashMap<String, ArrayList<Movie>> movieNameMovieObject = new HashMap<>();//Hashmap for movie.

    //gettors for the userlist, adminlist, theatrenametheatreobj, timeformatter, dateformatter, movienamemovieobj.
    public static HashMap<String, Theatre> getTheatrenametheatreobj() {return theatrenametheatreobj;}
    public static ArrayList<Admin> getAdminlist() {return adminlist;}
    public static ArrayList<User> getUserlist() {return userlist;}
    public static DateTimeFormatter getTimeFormatter() {return timeFormatter;}
    public static DateTimeFormatter getDateFormatter() {return dateFormatter;}
    public static HashMap<String,ArrayList<Movie>> getMovieNameMovieObject() {return movieNameMovieObject;}
}
