package BMS;

public class Admin extends  Account{//Admion pojo class
    //Admin constructor reassing the admin name and pass.
    public Admin(String name, int pass) {
       super(name,pass);//calling the constructor if the superclass (Account).
    }
}

