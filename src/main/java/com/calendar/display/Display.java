package com.calendar.display;
import com.calendar.events.Events;
import  com.calendar.users.User;

import java.util.Objects;

public class Display {


    public void getEventName(Events event) {
        System.out.println("Event: " + event.getEventName());
    }


    public void displayEvent(Events event) {
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
}
