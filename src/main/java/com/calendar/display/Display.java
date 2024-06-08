package com.Calendar.display;
import com.Calendar.events.Birthday;
import com.Calendar.events.Default;
import com.Calendar.events.Events;
import com.Calendar.events.Task;
import  com.Calendar.User.User;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Display {



    public static void displayEvent(Events event) {
        System.out.println("Date: " + event.getDate());
        System.out.println("Event: " + event.getEventName());
        System.out.println("Location: " + event.getLocation());
        System.out.println("Description: " + event.getDescription());
    }

    public static void displayUser(User user) {
        System.out.println("Name: " + user.getName());
        System.out.println("UserPseudo: " + user.getPseudo());
        System.out.println("family: " + user.getFamily());
    }

    public static void displayUserEvents(com.Calendar.User.User user) {
        List<Events> userEvents = Events.getUserEvents(user);
        if (userEvents != null) {
            System.out.println("Events for " + user.getName());
            for (Events event : userEvents) {
                displayEvent(event);
            }
        } else {
            System.out.println("No events found for " + user.getName());
        }
        Display.receptionDisplay(user);
    }




    public static void receptionDisplay(User user) {
        //clearConsole();
        System.out.println("-----------------------Calendar App Menu-----------------------");
        System.out.println("Please select an option:");
        System.out.println("1. Display all your events");
        System.out.println("2. Display current week");
        System.out.println("3. Create a new event");
        System.out.println("4. Display user information");
        System.out.println("4. Exit");
        System.out.println("--------------------------------------------------------------");
        int option = getConsoleInputInt(5);
        switch  (option) {
            case 1:
                //clearConsole();
                displayUserEvents(user);
                break;
            case 2:
                //clearConsole();
                displayCurrentWeek(user);
                break;
            case 3:
                //clearConsole();
                System.out.println("What type of event do you want to create? 1:Default, 2:Birthday, 3:Task");
                int eventType = getConsoleInputInt(3);
                Events event;
                switch (eventType) {
                    case 1:
                        event = new Default();
                        event.setType("Default");
                        break;
                    case 2:

                        event = new Birthday();
                        event.setType("Birthday");
                        break;
                    case 3:
                        event = new Task();
                        event.setType("Task");
                        break;
                    default:
                        System.out.println("Invalid event type");
                        return;
                }
                event.createEvent(user);
                break;
            case 4:
                displayUser(user);
                System.out.println("Want to change your pseudo? 1:yes 2:no");
                int choice = getConsoleInputInt(2);
                if (choice == 1) {
                    user.changePseudo();
                }
                receptionDisplay(user);
                break;
            case 5:
                //clearConsole();
                System.out.println("Goodbye " + user.getName());
                return;
                //exit (account connection)
            default:
                System.out.println("Unexpected error occurred");
                receptionDisplay(user);
        }
    }

    public static void displayCurrentWeek(User user) {
        //clearConsole();
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);

        for (int i = 0; i < 7; i++) {
            LocalDateTime currentDate = startOfWeek.plusDays(i).atStartOfDay();
            System.out.println("Date: " + currentDate);
            System.out.println("-----------------------------------");

            List<Events> userEvents = Events.getUserEvents(user);
            if (userEvents != null) {
                for (Events event : userEvents) {
                    if (event.getDate().equals(currentDate)) {
                        displayEvent(event);
                        System.out.println("-----------------------------------");
                    }
                }
            }
        }
        Display.receptionDisplay(user);
    }






    // Utils

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
                System.out.println("Please enter a valid integer between 0 and " + NbOfOptions);
                return getConsoleInputInt(NbOfOptions);
            }
            return value;
        } catch (NumberFormatException e) {
            System.out.println(input + " Please enter a valid integer between 0 and " + NbOfOptions);
            return getConsoleInputInt(NbOfOptions);
        }
    }


    public static void clearConsole() {
        System.out.print('\f');
    }
}
