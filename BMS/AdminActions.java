package BMS;
//import statements for the Localdate,LocalTime,Arraylist,hashmap,Scanner.
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AdminActions {//AdminActions class for admin's actions.

    public static Admin adminlogin(Scanner sc) {//method for the login
        //getting the admin name
        System.out.println("Enter Admin Name:");
        String name = sc.nextLine();
            for (Admin a : BMS.getAdminlist()) {//getting the admin object from adminlist by iterating.
                if (a.getName().equals(name)) {//if admin's name is equals entered name !
                    //getting the pass.
                    System.out.println("Enter the Password");
                    int pass = Integer.parseInt(sc.nextLine());

                    if (a.getName().equals(name) && a.getPass() == pass) {//checks admin name and pass is equals to the respective admin.
                        System.out.println("Login successfull");
                        return a;//returns the logined admin object.
                    } else {//if pass is wrong
                        return new Admin(null, 0);
                    }
                }
            }
        return null;//if no admin available
    }

    public static void addTheatre(Scanner sc) {//method for definition for ddTheatre.
        //getting the name and location
        System.out.print("Enter the theatre name :");
        String name = sc.nextLine();
        System.out.print("Enter the Location :");
        String loc = sc.nextLine();
        for (String temp : BMS.getTheatrenametheatreobj().keySet()) {//getting the key from the Theatrenameandobject iteratively.
            var currenttheatre = BMS.getTheatrenametheatreobj().get(temp);//getting the theatreobj using the theatre key and get().
            if (temp.equals(name) && currenttheatre.getLocation().equals(loc)) {//checking if the name adn location of the theatre is equal.
                System.out.println("Theatre is already exist!");
                return;//if theatre is already exist
            }
        }
        //getting the no. of screens
        System.out.print("Enter the no.of screens:");
        int numofscr = Integer.parseInt(sc.nextLine());
        //Hashmap for the Screen wit screenname key, screeen object value.
        HashMap<String, Screen> screenHashMap = new HashMap<>();
        x:
        while (numofscr >= 0) {//untill no.of screens become 0
            //getting the name, no of seats and scrgrid
            while (true) {
                System.out.print("Enter the name of the screen:");
                String scrname = sc.nextLine();
                //if the name of the screen is contains in the screen map(already exist)
                if (!screenHashMap.containsKey(scrname)) {
                    System.out.print("Enter the no.of seats  :");
                    int noofseats = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter the grid (eg: 2*4*2):");
                    String scrgrid = sc.nextLine();

                    //grid varinable(object)
                    var grid = Utility.generateSeatingPatterns(noofseats, scrgrid);//claiing the generateseatingPatterns method to get the seatpattern
                    if (grid == null) {//if grid is null
                        continue;//continue from getting screen name
                    }
                    Screen screen = (new Screen(scrname, noofseats, grid, scrgrid));//creating new instance for the Screen .
                    screenHashMap.put(scrname, screen);//adding that new instance in the ScreenHashmap with the screenname key.
                    numofscr--;//decrementing the no of screens
                    if (0 == numofscr) {//if all the screens sre created
                        break x;//breaks the loop
                    }
                } else {//if the screen is already exist
                    System.out.println("Screen is already exist !");
                }
            }
        }
        //after getting the theatre credentials creating the theatre instance
        Theatre theatre = new Theatre(name, screenHashMap, loc);
        BMS.getTheatrenametheatreobj().put(name, theatre);//addig that new theatre object to the Theatre hashmap.
        System.out.println("Theatre added !");
    }

    public static void viewtheatre() {//method to view theatres
        if (BMS.getTheatrenametheatreobj().isEmpty()) {//if theatrehashmap is empty
            System.out.println("No Theatres available !");
            return;
        }
        for (var theatres : BMS.getTheatrenametheatreobj().keySet()) {//getting the key from the Theatrenameandobject iteratively.
            var theatre = BMS.getTheatrenametheatreobj().get(theatres);//getting the theatreobj using the theatre key and get().
            System.out.println("Name :" + theatre.getName());//printing the name of the theatre
            System.out.println("location:" + theatre.getLocation());//printing the name of the theatre
            System.out.println("Screens are...");
            System.out.println("--------------");
            for (var screen : theatre.getScreenmap().keySet()) {//getting the screenelements from the Sreenmap iteratively and stores in the new set.
                System.out.println("\nScreen name     :" + screen);//printing the screen name
                System.out.println("Number of seats :" + theatre.getScreenmap().get(screen).getNofseats());//printing the no of seats
                System.out.println("Seat Arrangement:");
                System.out.print(theatre.getScreenmap().get(screen).getSeatingarrange());//printing the set arrangement
            }
        }
    }

    public static void addmovie() {//method for the adding movie
        Scanner sc = new Scanner(System.in);//Scannner class
        LocalDate date = null;//date var
        int duration =0;//duration var initialization
        int price;//var for the ticket price
        Screen screen ;//var in Scrren  type
        Theatre theatre;//var in Theatre type
        x:while (true) {//loop for itertiing.

            //getting the movie name and location.
            System.out.println("---------------------\nEnter the movie name :");
            String moviename = sc.nextLine();
            System.out.println("Enter location for the movie :");
            String loc = sc.nextLine();


            boolean movieavailable = false;//boolean var for condition purpose
            for (Theatre theatr : BMS.getTheatrenametheatreobj().values()) {//getting the value() of the Theatrenameandobj hashmap iteratively.
                if (theatr.getLocation().equalsIgnoreCase(loc)) {//checking the location is equal to the theatre's location.
                    movieavailable = true;
                    break;
                }
            }
            if (!movieavailable) {//if movie is not available in user's location
                System.out.println("No theatre found in your location !");
                return;
            }

            //getting the date of the movie.
            while (true) {//loop
                System.out.println("Enter the date of the Movie(dd:mm:yy)");
                try {//getting the date for the movie
                    date = LocalDate.parse(sc.nextLine(), BMS.getDateFormatter());
                    break;
                } catch (Exception e) {//if date format is not available
                    System.out.println("Invalid date format !");
                }
            }

            //getting the ticket price of the movie.
            while (true) {
                System.out.println("Enter the price of the single ticket :");
                try {//gettingt he price for the ticket
                    price = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {//catch block for the wrong input formation
                    System.out.println("Invalid ticket price!");
                }
            }

            System.out.println("Available Theatres :");
            for (Theatre theatres : BMS.getTheatrenametheatreobj().values()) {//iterating the theatre hashmap to get the theatres list
                if (theatres.getLocation().equalsIgnoreCase(loc)) {//if the theatre is loc of movie
                    System.out.println(theatres.getName());//printing the theatre name
                }
            }


            //getting the Theatrename of the movie.
            while (true) {
                System.out.println("Enter the Theatre name :");
                String theatrename = sc.nextLine();
                //getting the theatre object of theatre name key
                theatre = BMS.getTheatrenametheatreobj().get(theatrename);
                if (theatre == null || !(theatre.getLocation().equalsIgnoreCase(loc))) {//checking if the theatre is null or not in the movie's loc
                    System.out.println("Invalid theatre name ");
                    continue;//loops this part .
                }
                break ;
            }


            System.out.println("Available Screens");
            for (String screenName : theatre.getScreenmap().keySet()) {//iterating the screeen hashmap to get the Screen list
                System.out.println(screenName);
            }
            while (true) {
                //getting the AvailableScreen of the movie.
                System.out.println("Enter the name of the Screen :");
                String scrname = sc.nextLine();
                //getting the screen object from the screen map usnig screen name
                screen = theatre.getScreenmap().get(scrname);
                if (screen == null) {//if screen is full
                    System.out.println("Invalid Screen name !");
                    continue ;//loops this part
                }
                break ;
            }

            //getting the duration of the movie.
            while (true) {//loop for 3 times
                System.out.println("Enter the Duration of the movie(minutes):");
                try {
                    //getting the duration
                    duration = Integer.parseInt(sc.nextLine());
                    break ;
                } catch (Exception e) {
                    System.out.println("Invalid duration");
                }
            }
            Show show = new Show();
            if(duration != 0) {
                show = AdminActions.anothershow(date, sc, screen, price, show, duration);//calling the movie adding methos
                if (show != null) {//if shoe is not null...add it to the screen
                    screen.getShows().add(show);//adding these show instances to the Show.

                    //after getting the Movie credentials & creating the Movie instance
                    Movie currentmovie = new Movie(moviename, loc, date, theatre, screen, show);
                    if (!BMS.getMovieNameMovieObject().containsKey(moviename)) {//if the moie is not  existing
                        BMS.getMovieNameMovieObject().put(moviename, new ArrayList<>());//add the movie to the movie hashmap with the moviename key , new arraylist(empty).
                    }
                    BMS.getMovieNameMovieObject().get(moviename).add(currentmovie);//adding the movies details on the respective movie key .
                    System.out.println("Movie added successfully !");
                }
                boolean repeat = true;
                while (repeat) {//id want to add same movie as another show .
                System.out.println("Do you wnt to add the same movie as another show ? [yes: 1 / no: 0]");
                int choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) {
                    show = AdminActions.anothershow(date, sc, screen, price, show, duration);//calling the movie adding methos
                    if (show != null) {//if shoe is not null...add it to the screen
                        screen.getShows().add(show);//adding these show instances to the Show.
                        //after getting the Movie credentials & creating the Movie instance
                        Movie currentmovies = new Movie(moviename, loc, date, theatre, screen, show);
                        if (!BMS.getMovieNameMovieObject().containsKey(moviename)) {//if the moie is not  existing
                            BMS.getMovieNameMovieObject().put(moviename, new ArrayList<>());//add the movie to the movie hashmap with the moviename key , new arraylist(empty).
                        }
                        BMS.getMovieNameMovieObject().get(moviename).add(currentmovies);//adding the movies details on the respective movie key .
                        System.out.println("Movie was added to another show !");
                    }
                }
                else {
                    repeat = false;//reassigning the condition var
                }
                }
            }else{
                System.out.println("Please enter the valid duration !");
            }
            break;//breaking the while
        }
    }

    public static void viewmovies() {//view movies method
        if (BMS.getMovieNameMovieObject().isEmpty()) {//if moviemap is empty
            System.out.println("No movies available !");
            return;
        }
        //getting the Movie keys from the Movielist.
        for (var moviekey : BMS.getMovieNameMovieObject().keySet()) {
            var availablemovie = BMS.getMovieNameMovieObject().get(moviekey);//getting the movie object value using the movie name key and get().
            for (Movie movies : availablemovie) {//getting all the movies from the available movie hashmap.
                System.out.println("===========================");
                System.out.println("-> Movie    :" + movies.getMovieName());//printing the movie name
                System.out.println("-> Theatre  :" + movies.getTheatre().getName());//printing the name
                System.out.println("-> Screen   :" + movies.getScreen().getScreenname());//printing the screen name
                System.out.println("-> Location :" + movies.getLoc());//printing the location
                System.out.println("-> Date     :" + movies.getStartDate().format((BMS.getDateFormatter())));//printing the Date using dateformatter
                System.out.println("-> Duration :" + movies.getShow().getDuration());
                System.out.println("-> Starttime:" + movies.getShow().getStartTime().format((BMS.getTimeFormatter())));//printing the starttime using timeformatter
                System.out.println("-> End Time :" + movies.getShow().getEndTime().format((BMS.getTimeFormatter())));//printing the endtime using timeformatter
                System.out.println("===========================");
            }
        }
    }

    public static Show anothershow(LocalDate date, Scanner sc, Screen screen, int price,Show show,int duration) {//method for adding the show

        LocalTime startTime = null;//lcal var for the starttime of the movie
        while (true) {//loop for 3 times
            System.out.println("Enter the start time of the show (HH:mm)");
            try {//getting the start time
                startTime = LocalTime.parse(sc.nextLine(), BMS.getTimeFormatter());
                break ;
            } catch (Exception e) {
                System.out.println("Invalid time format");
            }
        }

        LocalTime endTime = null;
        //calculating the endtime by adding the break time.
            endTime= startTime.plusMinutes(duration + 30);
            boolean hitting = false;
            //getting the show instances from the Show.
            for (Show shows : screen.getShows()) {
                /* if the date is equals to the show's date and
                current endtime is before existing starttime (1 : 9)false
                current starttime is after existing endtime (10 : 12)true
                enters into body*/
                if (date.isEqual(shows.getDate()) && !(endTime.isBefore(shows.getStartTime()) || startTime.isAfter(shows.getEndTime()) || startTime.equals(shows.getStartTime()))) {
                    hitting = true;
                    break;
                }
            }
            //this block for checking all the show timings are matching or not.
            for (String movie : BMS.getMovieNameMovieObject().keySet()) {
                for (Movie movieobj : BMS.getMovieNameMovieObject().get(movie)) {
                    if (movieobj.getShow().getStartTime().equals(startTime)) {
                        hitting = true;
                        break;
                    }
                }
            }
            if (hitting) {//if both movies are hitting
                System.out.println("Show overlaps with an existing one.");
                return null;//return null (no show added .
            }
        HashMap<Character, ArrayList<String>> duplicateseatarrangement = new HashMap<>();//Hashmap duplicateseatarrangement for storing the cody of the seatrrangement.
        for (var entry : screen.getSeatingarrange().entrySet()) {//gets the seatrrangeme value and stores as entry set.
            char key = entry.getKey();//to store the key.
            ArrayList<String> value = entry.getValue();//arraylist for the key
            ArrayList<String> duplicatelist = new ArrayList<>(value);//arraylist for the seatpattern.
            duplicateseatarrangement.put(key, duplicatelist);
        }

        //after getting the Screen credentials creating the show instance
        show = new Show(startTime, endTime, date, screen, price, duplicateseatarrangement,duration);
        return show;//returns show.`1

    }
}
