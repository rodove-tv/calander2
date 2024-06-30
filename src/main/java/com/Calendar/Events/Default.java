package com.Calendar.Events;

import java.time.LocalDate;
import com.Calendar.User.User;
public class Default extends Events {

    private final String Type = "Event";


    public Default(String eventName,
                   String eventOwner,
                   int year,
                   int month,
                   int day,
                   int hourOfDay,
                   int minuteOfDay,
                   LocalDate date,
                   String location,
                   String description,
                   int idUser,
                   String type) {
        super(eventName, eventOwner, year, month, day, hourOfDay, minuteOfDay, date, location, description, idUser,null, type);
    }
    public Default() {
        super();
        setType(Type);
    }
}
