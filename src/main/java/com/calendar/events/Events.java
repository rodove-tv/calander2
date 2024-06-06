package com.calendar.events;
import com.calendar.users.User;

import java.sql.ClientInfoStatus;
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
    private com.calendar.users.User User;

    public static void addToEventsArray(List<Events> eventsList) {
        AllEventsList.add(eventsList);
    }

    public void setYear(int year) {this.year = year;}
    public void setMonth(int month) {this.month = month;}
    public void setDay(int day) {this.day = day;}
    public void setHourOfDay(int hourOfDay) {this.hourOfDay = hourOfDay;}
    public void setMinuteOfDay(int minuteOfDay) {this.minuteOfDay = minuteOfDay;}
    public void setDate() {
        this.date = LocalDateTime.of(getYear(), getMonth(), getDay(), getHourOfDay(), getMinuteOfDay());
    }
    public void setLocalDate() {
        this.localDate = LocalDate.now();
    }
    public void setEventName(String eventName) {this.eventName = eventName;}
    public void setLocation(String location) {this.location = location;}
    public void setDescription(String description) {this.description = description;}
    public void setEventOwner(String eventOwner) {this.eventOwner = eventOwner;}


    public int getYear() {return year;}

    public String getEventOwner() {return eventOwner;}
    public int getMonth() {return month;}
    public int getDay() {return day;}
    public int getHourOfDay() {return hourOfDay;}
    public int getMinuteOfDay() {return minuteOfDay;}
    public LocalDateTime getDate() {return date;}
    public LocalDate getLocalDate() {return localDate;}
    public String getEventName() {return eventName;}
    public String getLocation() {return location;}
    public String getDescription() {return description;}

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



}
