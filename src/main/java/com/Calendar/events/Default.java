package com.calendar.events;

import com.calendar.User.User;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Default extends Events {

    private final String Type = "Event";


    public Default(String eventName, String eventOwner, int year, int month, int day, int hourOfDay, int minuteOfDay, LocalDate date, String location, String description,User user, String type) {
        super(eventName, eventOwner, year, month, day, hourOfDay, minuteOfDay, date, location, description,user, type);
    }
    public Default() {
        super();
        setType(Type);
    }
}
