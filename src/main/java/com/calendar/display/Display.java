package com.Calendar.display;
import com.Calendar.events.Birthday;
import com.Calendar.events.Default;
import com.Calendar.events.Events;
import com.Calendar.events.Task;
import  com.Calendar.users.User;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Display {


    public void getEventName(Events event) {
        System.out.println("Event: " + event.getEventName());
    }


    public void displayEvent(Events event) {
        System.out.println("-----------------------------------");
        System.out.println("Event: " + event.getEventName());
        System.out.println("Date: " + event.getDate());
        System.out.println("Location: " + event.getLocation());
        System.out.println("Description: " + event.getDescription());
    }

    public void displayUser(User user) {
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Phone: " + user.getPhone());
    }

    public void displayUserEvents(User user) {
        System.out.println("Events for " + user.getName());
        for (Events event : Objects.requireNonNull(Events.getUserEvents(user))) {
            displayEvent(event);
        }
    }

    public void displayAllEvents(User[] users) {
        for (User user : users) {
            displayUserEvents(user);
        }
    }

    public void displayAllUsers(User[] users) {
        for (User user : users) {
            displayUser(user);
        }
    }

    public void displayAll(User[] users) {
        displayAllUsers(users);
        displayAllEvents(users);
    }

    public void displayAllEvents(Events[] events) {
        for (Events event : events) {
            displayEvent(event);
        }
    }

    public void receptionDisplay(User user) {
        System.out.println("Welcome to the Calendar App");
        System.out.println("Please select an option:");
        System.out.println("1. Display all your events");
        System.out.println("2. Display current week");
        System.out.println("3. Create a new event");
        System.out.println("4. Exit");
        int option = getConsoleInputInt(4);
        switch  (option) {
            case 1:
                displayUserEvents(User.getUser(user.getName()));
                break;
            case 2:
                displayCurrentWeek(User.getUser(user.getName()));
                break;
            case 3:

                System.out.println("What type of event do you want to create? 1:Birthday 2:Meeting 3:Appointment 4:Other");
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
                event.createEvent(User.getUser(user.getName()));
                break;
            case 4:
                //exit (account connection)
                break;
            default:
                System.out.println("Unexpected error occurred");
                receptionDisplay(user);
        }
    }

    public void displayCurrentWeek(User user) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);

        for (int i = 0; i < 7; i++) {
            LocalDate currentDate = startOfWeek.plusDays(i);
            System.out.println("Date: " + currentDate);

            List<Events> userEvents = Events.getUserEvents(user);
            if (userEvents != null) {
                for (Events event : userEvents) {
                    if (event.getLocalDate().equals(currentDate)) {
                        displayEvent(event);
                    }
                }
            }
        }
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
            System.out.println(input + " is not a valid integer");
            return getConsoleInputInt(NbOfOptions);
        }
    }
}
