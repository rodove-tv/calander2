package com.Calendar.Display;
import com.Calendar.DataManadgement.DataManadgement;
import com.Calendar.Database.Database;
import com.Calendar.Events.Birthday;
import com.Calendar.Events.Default;
import com.Calendar.Events.Events;
import com.Calendar.Events.Task;
import  com.Calendar.User.User;
import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static com.Calendar.Events.Events.displayEvent;
import static com.main.Main.database;


public abstract class Display {


    //Display colors for the console
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";






    //Display user information
    public static void displayUser(@NotNull User user) {
        System.out.println("Name: " + user.getName());
        System.out.println("UserPseudo: " + user.getPseudo());
        System.out.println("family: " + user.getFamily());
    }


    // Display all users events in array order
    public static void displayUserEvents(/*@NotNull*/ User user,List<Events> userEvents) {


        if (userEvents != null  && !userEvents.isEmpty()) {
            System.out.println("Events for " + user.getName());
            for (Events event : userEvents) {
                displayEvent(event);
            }
        } else {
            System.out.println("No events found for " + user.getName() + "feel free to add more events");
        }
        Display.receptionDisplay(user);
    }


    public static void connectionDisplay() {
        DataManadgement.toJson("dataBase/database.json", database);
        DataManadgement.readDatabaseFromFile("dataBase/database.json");
        List<User> users = List.of();
        if (database != null) {
            System.out.println("Displaying data:");
            System.out.println("Users: " + database.getDataUser());
            System.out.println("Events: " + database.getDataEnvents());
        } else {
            System.out.println("Database is null.");
        }
        System.out.println(ANSI_WHITE+"-----------------------"+ANSI_RESET + ANSI_CYAN + "Calendar App Menu"+ ANSI_RESET + ANSI_WHITE + "-----------------------"+ANSI_RESET);
        System.out.println("Please select an option:");
        System.out.println("1. créate a new user");
        System.out.println("2.  connection");
        System.out.println("3. Exit");
        System.out.println(ANSI_WHITE+"--------------------------------------------------------------"+ANSI_RESET);
        int option = getConsoleInputInt(3);

        User user;
        switch (option) {
            case 1:
                user = User.createUser(database.getDataUser());
                database.addUser(user);
                receptionDisplay(user);
                System.out.println("test");
                break;
            case 2:
                user = DataManadgement.connectionUser(users);
                if (user != null) {
                    connectionDisplay();
                    return;
                }
                receptionDisplay(user);

                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid event type");
                break;
        }
    }




    // Display the main menu
    public static void receptionDisplay(@NotNull User user) {
        System.out.println(ANSI_WHITE+"-----------------------"+ANSI_RESET + ANSI_CYAN + "Calendar App Menu"+ ANSI_RESET + ANSI_WHITE + "-----------------------"+ANSI_RESET);
        System.out.println("Please select an option:");
        System.out.println("1. Display all your events");
        System.out.println("2. Display current week");
        System.out.println("3. Create a new event");
        System.out.println("4. Display user information");
        System.out.println("5. Exit");
        System.out.println(ANSI_WHITE+"--------------------------------------------------------------"+ANSI_RESET);
        int option = getConsoleInputInt(5);
        switch  (option) {
            case 1:
                //clearConsole();
                displayUserEvents(user, database.getDataEnvents());
                break;
            case 2:
                //clearConsole();
                displayCurrentWeek(user);
                break;
            case 3:
                //clearConsole();
                System.out.println("What type of event do you want to create? 1:Event, 2:Birthday, 3:Task");
                int eventType = getConsoleInputInt(3);
                Events event;
                switch (eventType) {
                    case 1:
                        event = new Default();
                        break;
                    case 2:

                        event = new Birthday();
                        break;
                    case 3:
                        event = new Task();
                        break;
                    default:
                        System.out.println("Invalid event type");
                        return;
                }
                event.createEvent(user);
                break;
            case 4:
                displayUser(user);
                System.out.println("Want to change your password? 1:yes 2:no");
                int choice = getConsoleInputInt(2);
                if (choice == 1) {
                    System.out.println("change");
                    //user.changePassword();
                }
                receptionDisplay(user);
                break;
            case 5:
                //clearConsole();
                System.out.println("Goodbye " + user.getName());
                connectionDisplay();
                return;
                //exit (account connection)
            default:
                System.out.println("Unexpected error occurred");
                receptionDisplay(user);
        }
    }


    // Display the current week + events matching days
    public static void displayCurrentWeek(@NotNull User user) {
        //clearConsole();
        LocalDate today = LocalDate.now();
        List<Events> userEvents = Events.getUserEvents(user);
        for (int i = 0; i < 7; i++) {
            System.out.println(ANSI_WHITE +"-----------------------------------"+ANSI_RESET);
            LocalDate currentDate = today.with(DayOfWeek.MONDAY).plusDays(i);
            boolean eventFound = false;

            if (userEvents != null && !userEvents.isEmpty()) {
                for (Events event : userEvents) {
                    if (event.getDate().equals(currentDate)) {
                        if (event.getHourOfDay() == 0 && event.getMinuteOfDay() == 0) {
                            displayEventType(event.getType());
                            System.out.println("Date: " + event.getDate());
                        } else {
                            displayEventType(event.getType());
                            System.out.println("Date: " + event.getDateAndTime());
                        }
                        displayEvent(event);
                        eventFound = true;
                    }
                }
            }

            if (!eventFound) {
                System.out.println("Date: " + currentDate);
            }
        }
        Display.receptionDisplay(user);
    }


    // Display the event type in the console with different colors depending on the type
    public static void displayEventType(String type) {
        switch (type) {
            case "Event" ->
                    System.out.println(ANSI_BLUE + "          <--" + type.toUpperCase() + "-->          " + ANSI_RESET);
            case "Birthday" ->
                    System.out.println(ANSI_YELLOW + "          <--" + type.toUpperCase() + "-->          " + ANSI_RESET);
            case "Task" ->
                    System.out.println(ANSI_GREEN + "          <--" + type.toUpperCase() + "-->          " + ANSI_RESET);
        }
    }





    // Input Utils

    public static String getConsoleInputString() {
        String input = "";
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        return input;
    }



    public static Integer getConsoleInputInt(Integer NbOfOptions) {

        Scanner scanner = new Scanner(System.in);
        String input = "";
        input = scanner.nextLine();
        try {
            int value = Integer.parseInt(input);
            if (value <= 0 || value > NbOfOptions) {
                System.out.println("Please enter a valid integer between 1 and " + NbOfOptions);
                return getConsoleInputInt(NbOfOptions);
            }
            return value;
        } catch (NumberFormatException e) {
            System.out.println(input + "Please enter a valid integer between 1 and " + NbOfOptions);
            return getConsoleInputInt(NbOfOptions);
        }
    }


    public static void clearConsole() {
        System.out.print('\f');
    }


}
