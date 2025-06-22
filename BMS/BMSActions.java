package BMS;
//scanner class import statement.
import java.time.LocalDate;
import java.util.Scanner;

public class BMSActions {//Bookmyshoe Action class

    public static void start() {//start method dfinition
        Scanner sc = new Scanner(System.in);//Scanner object creation .
        BMS.getAdminlist().add(new Admin("a", 1));//setting Default admin credentials.
        x:while (true) {//while with label.
            //BookMyShow Actions
            System.out.println("BOOK MY SHOW");
            System.out.println(" 1. Admin \n 2.User \n 3 Exit");
            System.out.print("Enter your choice:");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {//switch case for admin, user login and exit.
                case 1:
                    Admin admin = AdminActions.adminlogin(sc); //creating instnce for th Admin class.
                    if (admin == null) { //to check whether the admin object 'admin' is null or not
                        System.out.println("please enter valid admin details.");
                    } else if (admin.getName() == null) //it checks whether the 'admin' obj containing admin username is == admin users list
                    {
                        System.out.println("Wrong password..Please try again later.");
                    } else {//admin is available
                        adminMenu();// it calls the adminactions method, if admin username and password get matched
                    }
                    break;

                case 2:
                    //getting the username
                    System.out.println("Enter user Name:");
                    String name = sc.nextLine();
                    User user = UserActions.userlogin(sc, name);//calling the userligin method.
                    if (user == null) {//if user object is null.
                        if (!BMS.getUserlist().contains(name)) {//if username is mismatching.
                            System.out.println("No user found");
                            System.out.println("Do you want to sign up ?[yes = 1 (or) no = 0]");//to ask for register
                            int ch = Integer.parseInt(sc.nextLine());// yes or no for register
                            if (ch == 1) {//if yes to register
                                UserActions.register(sc,name);//calling the register method.
                            } else {//if no to register
                                break;
                            }
                        }
                        else {//if user is already exist
                            System.out.println("USer is already exist!");
                        }
                    }
                    else if (user.getName() == null) //it checks whether the 'user' obj containing user's username is == null
                    {
                        System.out.println("Invalid username...");
                    }
                    else {
                        userMenu(sc,user);// it calls the useroperation method, if username and password get matched
                    }
                    break;

                case 3://case for exiting the BookMyShow.
                    System.out.println("Exiting the BookMyShow ");
                    break x;//break with label.

            }
        }
    }


    public static void adminMenu() {//adminmenu method for adminoperations
        Scanner sc = new Scanner(System.in);//Scanner of for getting inputs.
       x: while (true) {//while with label.
           //Admin menu list
           System.out.println("\nAdmin Menu:");
           System.out.println("1. Add Theatre\n2. Add Movies\n3. View Theatres \n4. view Movies \n5. Exit");

           int adminChoice = Integer.parseInt(sc.nextLine());
           // swith case for perform the adminopertions
           switch (adminChoice) {
               case 1:
                   // case for calling the method to adding Theatre
                   AdminActions.addTheatre(sc);
                   break;
               case 2:
                   // case for calling the method to adding Movies
                   AdminActions.addmovie();
                   break;
               case 3:
                   // case for calling the method to view the Theatre list
                   AdminActions.viewtheatre();
                   break;
               case 4:
                   // case for calling the method to view the Movie list
                   AdminActions.viewmovies();
                   break;

               case 5:
                   //case for exiting the admin.
                   System.out.println("Exiting the admin...");
                   break x;
           }
       }
    }

    private static void userMenu(Scanner sc, User user) {//usermenu method for useroperations
        //User menu list
        x:
        while (true) {
            System.out.println(" 1. Display Movie \n 2. Change Location /Date \n 3. View Ticket \n 4. Exit");
            int userChoice = Integer.parseInt(sc.nextLine());
            switch (userChoice) {//swith case for perform the adminopertions
                case 2:
                    // case for calling the method to Change location or Date.
                    LocalDate date = UserActions.changeLocationorDate(user, LocalDate.now());
                    //calling the available movies to display the movies after changing the date and location !
                    UserActions.availableMovies(user,date);
                    break ;
                case 1:
                    // case for calling the method to view AvailableMovies
                    UserActions.availableMovies(user,LocalDate.now());
                    break ;
                case 3:
                    // case for calling the method to view bookedTickets.
                    UserActions.viewTickets(user);
                    break ;
                case 4:
                    // case for exiting the user.
                    System.out.println("exiting user !");
                    break x;
            }
        }
    }
}
