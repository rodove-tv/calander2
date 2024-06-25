package com.Calendar.Events;

import java.time.LocalDate;

public class Default extends Events {

    private final String Type = "Event";


    public Default(String eventName, String eventOwner, int year, int month, int day, int hourOfDay, int minuteOfDay, LocalDate date, String location, String description, com.Calendar.User.User user, String type) {
        super(eventName, eventOwner, year, month, day, hourOfDay, minuteOfDay, date, location, description, user, type);
    }
    public Default() {
        super();
        setType(Type);
    }
}
