package BMS;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class UserActions {//USeractions class for the user operations

    public static User userlogin(Scanner sc, String name) {//method for userlogin if user is already exist.
            for (User a : BMS.getUserlist()) {//loop for getting the each user from th user list to a.
                if (a.getName().equals(name)) {//checking if the a object's name is equal to the name .
                    //getting the pass
                    System.out.println("Enter the Password");
                    int pass = Integer.parseInt(sc.nextLine());
                    if (a.getName().equals(name) && a.getPass() == pass) {//if name and the pass are matching.
                        System.out.println("Login successfull");
                        UserActions.availableMovies(a,LocalDate.now());//calls the user available movies by default for printing the available movies in user's area .
                        return a;//return the ligined user object.
                    } else {//if pass is wrong.
                        System.out.println("wroong pass..");
                    }
                }
            }
        return null;//returns null.
    }

    public static void register(Scanner sc,String name) {//metho to register new user
        //getting the username, pass and the location for the user.
        System.out.print("Enter the new Username [Default "+name+"]:");
        String username = sc.nextLine();
        if(username.isEmpty()){//if the user name input is empty it takes the already entered username.
            username=name;
            System.out.println(username);
        }
        System.out.println("Enter the pass :");
        int pass = Integer.parseInt(sc.nextLine());
        System.out.println("Enter your location :");
        String loc = sc.nextLine();
        //creating new instance for the User with the name,pass and location
        BMS.getUserlist().add(new User(username, pass, loc));
        System.out.println("User added !");
    }
//method to print the available movies based on the location.
public static void availableMovies(User user, LocalDate date) {
    Scanner sc = new Scanner(System.in);//scanner object creation .
    String movietobook ="";// movie name variable
    boolean movieisthere ;//for condition purpose
    ArrayList<Movie> movielist = new ArrayList<>();//local movielist to store the list of movies.
    System.out.println("========================");

//    LocalDate today = date;//to store the current date.
    LocalDate selectedDate = date;//for after changing the

        while (true) {
            System.out.println("Now available novies in your Location  :" + user.getLocation());//printing the location
            movieisthere = false;//for condition purpose
            for (String moviename : BMS.getMovieNameMovieObject().keySet()) {//getting the movienmeandobj ,and puts in the moviename .
                boolean movavailable = false;//for changing the another bool var.
                for (Movie movie : BMS.getMovieNameMovieObject().get(moviename)) {//iterate the movielist and stores in the movie.
                    if (movie.getLoc().equalsIgnoreCase(user.getLocation()) && selectedDate.equals(movie.getStartDate())) {//checks the location of the movie with the users location and movie starting date with today.
                        movavailable = true;//resassigning as true .
                        movieisthere = true;//resassigning as true .
                        break;
                    }
                }
                if (movavailable){//if movie is available
                    System.out.println("=>" + moviename);//printing the available movie
                }
            }

            if (!movieisthere) {//if no movie available
                System.out.println("No movie Available in your location on currentdate!");
            }
            // if want to change the location or date
            System.out.println(" Would you like to change the (Location or Date): [yes = 1 | no = 0]");
            int ch = Integer.parseInt(sc.nextLine());

            if (ch == 1) {//if want to change location.
                LocalDate newDate = changeLocationorDate(user, date);//calling chngelocationordate() method
                if (!(newDate == null)) {//if newdate is not null.
                    selectedDate = newDate;//assigning the newdate to date.
                }
            }
            else {//if choice is 0 or other
                break;
            }
        }

    while (movieisthere) {//if any movie is there...

        //getting movie name to book tickets.
        System.out.println("Enter the movie name to book tickets :");
        try {
            movietobook = sc.nextLine();
            movieisthere= false;//reassigning as false after getting the name(because no need to iterate after getting input)
        } catch (Exception e) {
            System.out.println("Not a valid movie name");
        }
    }

    ArrayList<String> movieNameList = new ArrayList<>(BMS.getMovieNameMovieObject().keySet());//movienamelist to hold the name of the movies
    if (movieNameList.contains(movietobook)) {//if name of the movietobook is available in movienamelist.
        for (var movieobject : BMS.getMovieNameMovieObject().get(movietobook)) {//getting the movieobject from the movie hashmap.
            if (movieobject.getLoc().equalsIgnoreCase(user.getLocation()) && movieobject.getStartDate().isEqual(selectedDate)) {//chaecks the location and the time of the movie is equls to the user's.
                movielist.add(movieobject);//add the movie object in the movielist.
            }
        }
        bookticket(user, movielist);//calling booktickets with user object and movielist object.
    }else {
            System.out.println("No movie available as you entered !");
        }

}


    public static void bookticket(User user, ArrayList<Movie> movies) {//method for booking tickets
        Scanner sc = new Scanner(System.in);//scanner object creation
        LocalTime showTime;//var for the time of the show
        String theName;//theatre name var

        HashMap<String, HashSet<Show>> ShowonTheatre = new HashMap<>();//hashmap to store the show on the particular theatre.
        for (var movie : movies) {  //getting the movie arraylist elements from one by one
            if (ShowonTheatre.containsKey(movie.getTheatre().getName())) {//checking Showontheatre contains the key as the name of  movie.
                ShowonTheatre.get(movie.getTheatre().getName()).add(movie.getShow());//adding the show object value to the particular movie key value.
            } else {
                HashSet<Show> shows = new HashSet<>();//creating the new hashset<>
                shows.add(movie.getShow());//adding the shows to the shows set.
                ShowonTheatre.put(movie.getTheatre().getName(), shows);//add the show arraylist object in the theatrename key.
            }
        }

        while (true) {
            System.out.println("Available Theatres");
            for (String theatreName : ShowonTheatre.keySet()) {//getting the key value
                System.out.println("Theatre Name :" + theatreName);//to print the theatre name
                System.out.println("Shows        :" + ShowonTheatre.get(theatreName).toString());//to print the show timings
            }
            ArrayList<String> thlist = new ArrayList<>(ShowonTheatre.keySet());
            while (true) {
                //getting the theatre name
                System.out.println("Enter the name of theatre (if want exit enter 0 ):");
                    theName = sc.nextLine();
                if (!thlist.contains(theName)) {//if theatrename is not available in theatre list
                    if (theName.equals("0")) {//if input i "0"
                        System.out.println("Exiting...");
                        return;
                    }
                    System.out.println("Theatre is not available");
                    continue;
                    }
                break;
            }
            //getting the key value from the theatre show on theatre
            if (thlist.contains(theName)) {//list contains the theatre name
                //getting the local time.
                while (true) {
                    System.out.println("Enter the show time :");
                    try {
                        showTime= LocalTime.parse(sc.nextLine(), BMS.getTimeFormatter());
                        break;
                    }catch (Exception e){
                        System.out.println("PLease enter the valid time");
                    }
                }

                HashSet<Show> show = ShowonTheatre.get(theName);//hashset for show on the particular theatre key .
                if (show == null) {//if null
                    System.out.println("Invalid show !");
                    continue;//if show is not available repeats the process
                }

                Show nowShow = null;//condition purpose
                for (Show shows : show) {//to loop all the show hashset.
                    if (shows.getStartTime().equals(showTime)) {//checking if the show is available in the given time
                        nowShow = shows;//reassigning newshow as shows
                        break;
                    }
                }
                if (nowShow == null) {//not null
                    System.out.println("Enter the valid time of show !");
                    continue;
                } else {
                    System.out.println("Screen Name :" + nowShow.getScreen().getScreenname());//to print screen name
                    System.out.println("No. of Seats:" + nowShow.getScreen().getNofseats());//to print the no. of seats
                    var seatGrids = nowShow.getSeatarr();//getting the seat arrangement
                    System.out.println("Seat Arrangement :\n=========================");
                    for (var seats : seatGrids.entrySet()) {//loop the seating arrangement
                        System.out.println(seats.getKey() + " " + seats.getValue());//printing the seat pattern
                    }
                }
                //getting the no. of seats to book
                System.out.println("Enter the no .of seats:");
                int seatCount = Integer.parseInt(sc.nextLine());
                int price = nowShow.getPrice() * seatCount;//calculating the total price
                int availableSeat = nowShow.getScreen().getAvailableseat();//var. for getting the available seats
                var bookedTicket = seatSelection(seatCount, nowShow, availableSeat,price);//to select the seat(methods call)
                if (!(bookedTicket == null)) {
                    //adding the ticket with all movie details
                    Tickets ticket = new Tickets(theName, movies.getFirst().getMovieName(), nowShow.getScreen().getScreenname(), user.getLocation(), showTime, bookedTicket, price);
                    user.getTicket().add(ticket);//adding the ticket to the user instance.
                }

            } else {
                System.out.println("Invalid Theatre name !");
                break;//if theatre is not available.
            }
        }
    }

    public static void viewTickets(User user) {//method for view the booked tickets
        if (user.getTicket().isEmpty()) {//if tickeet of user is empty
            System.out.println("No Tickets booked !");
            return;
        }
        ArrayList<Tickets> ticket = user.getTicket();//get the ticket object for the current user
        for (Tickets tickets : ticket) // go through the arrayList
        {
            System.out.println("============================");
            System.out.println("Theatre Name     : " + tickets.getTheatreName()); // print  theatre name
            System.out.println("Theatre Location : " + tickets.getLocation()); // print  theatre location
            System.out.println("Movie Name       : " + tickets.getMovieName()); // print movie name
            System.out.println("Screen Name      : " + tickets.getScreenName());// print  screen name
            System.out.println("Show Time        : " + tickets.getStartTime());// print  show time
            System.out.println("Booked Seats     : " + tickets.getBookedTicket()); // print  booked seats
            System.out.println("Price            : " + tickets.getPrice());// print  total price
            System.out.println("============================");
        }
    }

    public static LocalDate changeLocationorDate(User user, LocalDate today) {//method to change the location and date.
        Scanner sc = new Scanner(System.in);//Scanner object to get the object.
        System.out.println("1.Location \n 2.Date \n 3. Exit \n Enter your choice :");
        int choice = Integer.parseInt(sc.nextLine());//choice variable .
        switch (choice) {//swith case for the change location or date.
            case 1:
                //case for changing the location
                System.out.println("Your Location :" + user.getLocation());
                System.out.println("Available Locations:");
                var availableLocations = new HashSet<>();//hashset for the locations
                for (Theatre theatre : BMS.getTheatrenametheatreobj().values()) {//for loop for getting the theatre object from the theatrehashmap.
                    availableLocations.add(theatre.getLocation());//adding the locations on the available locations
                }
                for (var location : availableLocations) {//iterating the availablelocation and storing on the location.
                    System.out.println("=>" + location);//printing the locations
                }
                // getting the locations
                System.out.println("Enter your new Location :");
                String newLocation = sc.nextLine();
                if (availableLocations.contains(newLocation)) {//if newlocation is contains in the locations....
                    user.setLocation(newLocation);//setting the newlocation to the user
                    System.out.println("Location changed Successfully to " + newLocation);//printing the location after change.
                    return LocalDate.now();
                } else {//if location is not avilable !
                    System.out.println("Location not valid !");
                }
                break;
            case 2://case for the changing the date.

                //getting the date with th perfect date.
                while (true) {
                    System.out.println("Enter your new date");
                    try {
                        LocalDate newDate = LocalDate.parse(sc.nextLine(), BMS.getDateFormatter());//getting local date
                        if (newDate.isAfter(today) || newDate.isEqual(today)) { // check the date is not in past
                            System.out.println("Date changed successfully to :" + newDate);
                            return newDate;//returning the newdate
                        }
                    } catch (Exception e) {//catch block
                        System.out.println("please enter the valid date !");
                        continue ;
                    }
                    break;//breaks the while
                }
                break;//break for case 2.
            case 3://case for exiting .
                System.out.println("Exitting...");
                return null;//if not available.return null.
            default://if choice is invalid
                System.out.println("Invalid choice !");
        }
        return null;//return statement for method
    }

        public static ArrayList<String> seatSelection(int seatCount, Show show, int availableseat, int price) {//method for selecting the seat
            Scanner sc = new Scanner(System.in);//Scannner object creation
            var seatGrid = show.getSeatarr();//getting the seat arrangement.
            HashMap<Character, ArrayList<String>> duplicatedGrid = new HashMap<>();//duplicate grid to store the seating arrangement.
            for (var entry : seatGrid.entrySet()) {//storing the seat arrangement in the arraylist format
                char key = entry.getKey();//key of the seat arrangement
                ArrayList<String> value = entry.getValue();//arraylist format(seats)
                ArrayList<String> duplicatelist = new ArrayList<>(value);//creates the duplicate arraylist with seat arrangement value.
                duplicatedGrid.put(key, duplicatelist);//add the duplicated seat pattern on the hashset with key and duplicate pattern
            }
            ArrayList<String> bookTickets = new ArrayList<>();//Arraylist to store the list of booked tickets
            final int finalseatcount = seatCount;//assinging the no of seats in the another var.
            boolean bookconfirm = false;//for conditioon purpose
            while (seatCount > 0) {//loop untill no of seats is > 0
                if (!(availableseat == 0)) {
                    if (availableseat >= seatCount) {
                        //getting the seat number(B1 , S1).
                        System.out.println("Enter the Seat number to book :");
                        String seatnumber = sc.nextLine();
                        char row = seatnumber.charAt(0);//row value( '0' th index of the seat number)
                        int seat = Integer.parseInt(seatnumber.substring(1));//seat value('1' st index of the seat number)
                        String[] grid = show.getScreen().getgrid().split("\\*");//to get the grid values(split hte * s)
                        int sum = 0;//sum of seats for an row
                        for (String grids : grid) {//iterating all the grid value from the gris arrray
                            sum += Integer.parseInt(grids);//adding the grid value to thesum.
                        }
                        if (seat > sum || seat <= 0) {//seat epxtended
                            System.out.println("Enter a valid seat !");
                            continue;//continue from seat name getting
                        }
                        String choosenseats;//var for the choosen seats
                        int index;//index var for marking the correct boooked seats
                        if (seat <= Integer.parseInt(grid[0])) {//from left to 1 st space
                            index = seat - 1;//same index
                        } else if (seat >= (sum + 1) - Integer.parseInt(grid[2])) {//after 2 nd space
                            index = seat + 1;//index is  increased by 1 with seat
                        } else {
                            index = seat;//between 2 spaces.same index
                        }
                        choosenseats = duplicatedGrid.get(row).get(index);//getting the seat using the index and row char.
                        if (choosenseats.equalsIgnoreCase("X")) {//if already boooked
                            System.out.println("This seat is already booked !");
                            continue;//continue from the getting another seat number.
                        } else {
                            duplicatedGrid.get(row).set(index, "X");//setting the selected seat as X
                            bookTickets.add(seatnumber);//add the Booked seat value to booked tickets
                            bookconfirm = true;//reassigning as true
                        }
                    } else {
                        System.out.println("Exceeding max no of seats available");//if no. of seats is exceeding.
                        return null;
                    }
                }else {
                    System.out.println("Show house full !");
                    return null;
                }

                for (var seatEntry : duplicatedGrid.entrySet()) {//iterating through the dup .arr list
                    System.out.println(seatEntry.getKey() + " " + seatEntry.getValue());//printing the row value and seat pattern
                }
                seatCount--;//decrementing the seatCount value
            }

            if (bookconfirm) {// if seats are perfectly selected ,then confirm booking
                System.out.println("Confirm your seat bookking ! [yes = 1 / no =0]");//confirming the ticket booking
                int choice = Integer.parseInt(sc.nextLine());

                if (choice == 1) {//confirms booking
                    show.setSeatarr(duplicatedGrid);
                    System.out.println("Ticket Amount paying Rs.:" + price);//to say paying ticket amount
                    System.out.println("Tickets booked Successfully !");
                    show.getScreen().setAvailableseat(availableseat - finalseatcount);
                    return bookTickets;//returns booked tickets list
                }
                else {//cancell booking
                    //if booking is calceleed
                    System.out.println("Bookking has been cancelled .");
                    System.out.println("Exiting...!");
                    return null;//if ticket was not confirmed to book
                }
            }
            else{
                System.out.println("Enter the valid seat count !");
            }
            return null;//seat selection was not perfectly done returns null
        }
    }