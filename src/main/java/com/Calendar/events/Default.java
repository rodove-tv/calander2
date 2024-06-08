package com.Calendar.events;

import java.time.LocalDateTime;

public class Default extends Events {


    public Default(String eventName, String eventOwner, int year, int month, int day, int hourOfDay, int minuteOfDay, LocalDateTime date, String location, String description, com.Calendar.User.User user) {
        super(eventName, eventOwner, year, month, day, hourOfDay, minuteOfDay, date, location, description, user);
    }
    public Default() {
        super();
    }

    public static void createDefaultEvent() {

    }
}
