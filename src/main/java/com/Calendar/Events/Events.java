package com.Calendar.Events;
import com.Calendar.User.User;
import com.Calendar.Display.Display;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
//import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.Calendar.Display.Display.ANSI_RESET;
import static com.Calendar.Display.Display.ANSI_WHITE;


public abstract class Events {


    private String eventName;
    private String eventOwner;
    private int year;
    private int month;
    private int day;
    private int hourOfDay;
    private int minuteOfDay;
    private LocalDateTime dateandTime;
    private LocalDate date;
    private final LocalDate localDate = LocalDate.now();
    private final LocalDateTime localDateTime = LocalDateTime.now();
    private String location;
    private String description;
    private static final Map<String, List<Events>> AllEventsList = new HashMap<>();
    private int idUser;
    private String family;
    private String type;


//constructor
    @JsonCreator
    public Events(@JsonProperty("eventName") String eventName,
                  @JsonProperty("eventOwner") String eventOwner,
                  @JsonProperty("year")int year,
                  @JsonProperty("month")int month,
                  @JsonProperty("day")int day,
                  @JsonProperty("hourOfDay") int hourOfDay,
                  @JsonProperty("minuteOfDay")int minuteOfDay,
                  @JsonProperty("date")LocalDate date,
                  @JsonProperty("location")String location,
                  @JsonProperty("description")String description,
                  @JsonProperty("idUser")int idUser,
                  @JsonProperty("family")String family,
                  @JsonProperty("type")String type) {
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
        if (family != null) {
            this.family = family;
        }else{
            this.family = User.getUserPseudoById(idUser);
        }

    }
    public Events(String Type) {
        this.type = Type;
    }
    public Events() {
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

    public void setDateAndTime() {
        this.dateandTime = LocalDateTime.of(year, month, day, hourOfDay, minuteOfDay);
    }

    public void setDate() {
        this.date = LocalDate.of(year, month, day);
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
        this.type = Type;
    }



    public int getYear() {
        return year;
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
    public LocalDate getDate() {
        return date;
    }
    public LocalDateTime getDateAndTime() {
        return dateandTime;
    }
    public LocalDate getLocalDate() {
        return localDate;
    }
    public String getEventOwner() {
        return eventOwner;
    }
    public String getEventName() {
        return eventName;
    }
    public String getLocation() {
        if (location == null) {
            return "No location";
        }
        return location;
    }
    public String getDescription() {
        return description;
    }
    public String getType() {
        return type;
    }
    //Get all events of a user
    public static List<Events> getUserEvents(User user) {
        return AllEventsList.get(user.getName());
    }
    //get the user event by name
    public Events getEventByName(String name, List<Events> events) {
        for (Events event : events) {
            if (Objects.equals(event.eventName, name)) {
                return event;
            }
        }
        return null;

    }

    //add user events list to all events list
    public static void addUserEventsListToAllEventsList(List<Events> eventsList, User user) {
        AllEventsList.put(user.getName(), eventsList);
    }
    //add event to user events list
    public static void addEventToUserEventsList(Events event, User user) {
        List<Events> userEvents = AllEventsList.get(user.getName());
        if (userEvents != null) {
            userEvents.add(event);
        }
    }

    //create an event
    public void createEvent(/*@NotNull*/ User user) {
        basicEventInfos(user);
        addEventToUserEventsList(this, user);
        Display.receptionDisplay(user);
    }

    //display event depending on the type
    public static void displayEvent(Events event) {
        String type = event.getType();
        if (Objects.equals(type, "Birthday")) {
            Birthday birthday = (Birthday) event;
            Birthday.displayEvent(birthday);
        } else if (Objects.equals(type, "Task")) {
            Task task = (Task) event;
            Task.displayEvent(task);
        } else {
            System.out.println("Event: " + event.getEventName());
            System.out.println("Location: " + event.getLocation());
            System.out.println("Description: " + event.getDescription());
        }

    }

    //method to get the type of the event
    public void basicEventInfos(User user)  {
        setEventOwner(user.getName());
        System.out.println(ANSI_WHITE +"<------------------"+ ANSI_RESET +"New Event : " + type + ANSI_WHITE +"------------------>"+ANSI_RESET);
        System.out.println("Enter the name of the "+ type +": ");
        setEventName(Display.getConsoleInputString());
        System.out.println("(Optional) Enter the description of the "+ type +": ");
        setDescription(Display.getConsoleInputString());
        System.out.println("Enter the year of the "+ type + ": ");
        setYear(Display.getConsoleInputInt(99999));
        System.out.println("Enter the month of the " + type +": ");
        setMonth(Display.getConsoleInputInt(12));
        System.out.println("Enter the day of the "+ type+ ": ");
        setDay(Display.getConsoleInputInt(31));
        addDetailsToEvent();
        setDate();
    }

    //method to add more details to the event
    public void addDetailsToEvent() {
        System.out.println("Want you to add more details to this event ? 1:yes 2:no");
        int choice = Display.getConsoleInputInt(2);
        if (choice == 1) {
            System.out.println("Select what you want to add: 1:Time 2:location");
            choice = Display.getConsoleInputInt(3);
            switch (choice) {
                case 1:
                    System.out.println("Enter the hour of the event: ");
                    hourOfDay = Integer.parseInt(Display.getConsoleInputString());
                    System.out.println("Enter the minute of the event: ");
                    minuteOfDay = Integer.parseInt(Display.getConsoleInputString());
                    setDateAndTime();
                    break;
                case 2:
                    System.out.println("Enter the location of the event: ");
                    location = Display.getConsoleInputString();
                    break;
                default:
                    System.out.println("Unexpected error occurred");
            }
            addDetailsToEvent();
        }
    }

}