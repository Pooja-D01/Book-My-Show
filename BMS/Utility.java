package BMS;

import java.util.ArrayList;
import java.util.HashMap;

public class Utility implements UtilitiesInterface
{
    //User defined utility class to geenrate the seatarrangement .
    public static HashMap<Character, ArrayList<String>> generateSeatingPatterns(int noofseats, String scrgrid){//method to generate  seat arrangement.
        int count =noofseats;//assigning coofcount to the var (count)
        String[] splitscr= scrgrid.split("\\*");//spliting the gris value and put it iin string array[].
        int sum=0;//int variable decration.(for storing the one row's total seats)

        for(String grid: splitscr){//iterating through the splitedscr string array]
            int temp = Integer.parseInt(grid);//storing that getted value on temp.
            sum=sum+temp;//adding sum with the grid value.
        }
        if(count % sum == 0)//if condition becomes true enters into the body.
        {
            var seatArrange =new HashMap<Character,ArrayList<String>>();//seatrrange for the seating arragement pattern hansmap<>.
            char ch = 'A';//Character key valu to the row identification.initialized with 'A'.
            while(count>0){//untill the no. of seats become zero.
                ArrayList<String> r = new ArrayList<>();//row arraylist for storing the row seat pattern.
                //using nested for loop the seararrangement was added with seats and spaces!
                for(int i = 0 ; i < splitscr.length  ; i++){
                    for(int j = 0 ; j <Integer.parseInt(splitscr[i]) ; j++){
                        r.add("["+"]");//seats
                    }
                    if(i < scrgrid.length() -3){
                        r.add("<===> ");//space
                    }
                }
                seatArrange.put(ch,r);//putting this row value(seatarrangement) using the,chracter key value.
                ch++;//incrementing character var. by one .
                count=count-sum;//reducing the no. of seats by minusing with sum .(1 row seat count).
            }
            return seatArrange;//returns the seat arrangement
        }
        System.out.println("not a valid grid value");
        return null;//if grid value mismatched.
    }
}
