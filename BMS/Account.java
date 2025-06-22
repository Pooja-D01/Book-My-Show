package BMS;

public abstract class Account {
    String name ;//usernmae of the user
    int pass;//password of the user

    //constructor for Account class
    public Account(String name, int pass){
        //super();[implicitly calling the constructor of object class]
        this.name=name;//intializing the name
        this.pass=pass;//intializing the pass
    }

    //gettor for both name and pass.
    public String getName() {
        return name;
    }
    public int getPass() {
        return pass;
    }
}
