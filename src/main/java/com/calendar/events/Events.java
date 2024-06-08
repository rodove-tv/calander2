package com.Calendar.events;
import com.Calendar.users.User;
import com.Calendar.display.Display;
import jdk.jfr.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public abstract class Events {

    private String eventName;
    private String eventOwner;
    private int year;
    private int month;
    private int day;
    private int hourOfDay;
    private int minuteOfDay;
    private LocalDateTime date;
    private LocalDate localDate;
    private String location;
    private String description;
    private static List<List<Events>> AllEventsList;
    private com.Calendar.users.User User;
    private String Type;

//constructor
    public Events(String eventName, String eventOwner, int year, int month, int day, int hourOfDay, int minuteOfDay, LocalDateTime date, String location, String description, User user) {
        this.eventName = eventName;
        this.eventOwner = eventOwner;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hourOfDay = hourOfDay;
        this.minuteOfDay = minuteOfDay;
        this.date = date;
        this.location = location;
        this.description = description;
        this.User = user;
    }
    public Events() {
    }





    public static void addUserEventsListToAllEventsList(List<Events> eventsList) {
        AllEventsList.add(eventsList);
    }

    public static void addEventToUserEventsList(Events event, User user) {
        for (List<Events> events : AllEventsList) {
            if (Objects.equals(events.getFirst().getEventOwner(), user.getName())) {
                events.add(event);
            }
        }
    }


    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    public void setMinuteOfDay(int minuteOfDay) {
        this.minuteOfDay = minuteOfDay;
    }

    public void setDate() {
        this.date = LocalDateTime.of(getYear(), getMonth(), getDay(), getHourOfDay(), getMinuteOfDay());
    }

    public void setLocalDate() {
        this.localDate = LocalDate.now();
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEventOwner(String eventOwner) {
        this.eventOwner = eventOwner;
    }

    public void setType(String Type) {
        this.Type = Type;
    }


    public int getYear() {
        return year;
    }

    public String getEventOwner() {
        return eventOwner;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    public int getMinuteOfDay() {
        return minuteOfDay;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getEventName() {
        return eventName;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return Type;
    }

    //Get all events of a user
    public static List<Events> getUserEvents(User user) {
        for (List<Events> events : AllEventsList) {
            if (Objects.equals(events.getFirst().getEventOwner(), user.getName())) {
                return events;
            }
        }
        return null;
    }

    //get the user event by name
    public Events getSpecialEvent(LocalDateTime date, Events[] events) {
        for (Events event : events) {
            if (Objects.equals(event.getDate(), date)) {
                return event;
            }
        }
        return null;

    }



//create an event
public void createEvent(User user) {
    setType(getType());
    basicEventInfos();
    addEventToUserEventsList(this, user);
}




    public void basicEventInfos()  {
        String type = getType();
        setEventOwner(User.getName());
        System.out.println("<------------------New Event : " + type + "------------------>");
        System.out.println("Enter the name of the "+ type +": ");
        setEventName(Display.getConsoleInputString());
        System.out.println("(Optional) Enter the description of the "+ type +": ");
        setDescription(Display.getConsoleInputString());
        System.out.println("Enter the year of the "+ type + ": ");
        setYear(Integer.parseInt(Display.getConsoleInputString()));
        System.out.println("Enter the month of the " + type +": ");
        setMonth(Integer.parseInt(Display.getConsoleInputString()));
        System.out.println("Enter the day of the "+ type+ ": ");
        setDay(Integer.parseInt(Display.getConsoleInputString()));
        addDetailsToEvent();
        setDate();
    }


    public void addDetailsToEvent() {
        System.out.println("Want you to add more details to this event ? 1:yes 2:no");
        int choice = Display.getConsoleInputInt(2);
        if (choice == 1) {
            System.out.println("Select what you want to add: 1:hour 2:minute 3:location");
            choice = Display.getConsoleInputInt(3);
            switch (choice) {
                case 1:
                    System.out.println("Enter the hour of the event: ");
                    setHourOfDay(Integer.parseInt(Display.getConsoleInputString()));
                    break;
                case 2:
                    System.out.println("Enter the minute of the event: ");
                    setMinuteOfDay(Integer.parseInt(Display.getConsoleInputString()));
                    break;
                case 3:
                    System.out.println("Enter the location of the event: ");
                    setLocation(Display.getConsoleInputString());
                    break;
            }
        }
        //return to main menu
    }











}








