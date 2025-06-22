package BMS;
//import statements for the Localdate,LocalTime,ArrayList<>,Hashmap,Objects.
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Show {//Show pojo class

     private LocalTime startTime ;//tarttime of the show in the type of LocalTime
     private LocalTime endTime;//endTime  of the show in the type of LocalTime
     private LocalDate date;//date  of the show in the type of LocalDate
     private Screen screen;//screen object in the Screen type.
     private int price;//ticket price for the show.
     private HashMap<Character, ArrayList<String>> seatarr ;//seatarr var in the type of Hashmap<>
    private int duration ;

    //Show Constructor to set the startTiime,endTime,date,screen,price,seatarr.
     public Show(LocalTime startTime, LocalTime endTime, LocalDate date,Screen screen, int price,HashMap<Character,ArrayList<String>> seatarr,int duration)
     {
         //super();[implicitly calling the constructor of object class]
         this.startTime=startTime;
         this.endTime=endTime;
         this.date=date;
         this.screen=screen;
         this.price=price;
         this.seatarr=seatarr;
         this.duration = duration;
     }

    public Show() {
        //super();[implicitly calling the constructor of object class]
    }

    //gettors for the startTiime,endTime,date,screen,price,seatarr
    public LocalTime getEndTime() {
        return endTime;
    }

    public int getDuration() {return duration;}

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalDate getDate() {return date;}

    public Screen getScreen() {
        return screen;
    }

    public int getPrice() {
        return price;
    }

    public HashMap<Character, ArrayList<String>> getSeatarr() {
        return seatarr;
    }

    //settor for seatarr
    public void setSeatarr(HashMap<Character, ArrayList<String>> seatarr) {
        this.seatarr = seatarr;
    }


    // overriding the equals method to check the object's particular value.
    @Override
    public boolean equals(Object object){
         if(object == null || getClass() != object.getClass()){//if object is null or class is not equal to the Show type.
             return false;
         }
         Show show = (Show) object;//type caasting the object to Show type.
         return Objects.equals(this.startTime, show.startTime) && Objects.equals(this.endTime, show.endTime);//returns true if the both are ture
    }

    @Override
    public String toString(){
         return String.valueOf(getStartTime());
    }// to print the show timing from the show object


}
