package BMS;

import java.util.HashMap;

public class Theatre {//Theatre pojo class
    private String  name;//Theatre name
    private String location;//Theare location
    private HashMap<String, Screen> screenmap;//ScreenMap

    //Theatre constructor to initialize the theatre name,location,sceenmap(Screen hashmap) .
    public Theatre (String name,HashMap<String,Screen> screenmap,String location){
        //super();[implicitly calling the constructor of object class]
        this.name=name;
        this.location=location;
        this.screenmap=screenmap;
    }

    //gettor for the theatre's name,location and  screenmap.
    public String getLocation() {
        return location;
    }
    public String getName() {
        return name;
    }
    public HashMap<String, Screen> getScreenmap() {
        return screenmap;
    }

}
