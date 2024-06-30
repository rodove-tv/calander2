package com.main;

import com.Calendar.Database.Database;
import com.Calendar.User.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.Calendar.DataManadgement.DataManadgement;
import com.Calendar.Display.Display;
import com.Calendar.Events.Events;

import static com.Calendar.Events.Events.addUserEventsListToAllEventsList;

public class Main {
    static List<User> users;
    static List<Events> events;
    public static Database  database = new Database(users, events);

    public static void main(String[] args) {
        //List<User> users = new ArrayList<>();
        //List<Events> events = new ArrayList<>();
        //Database database = new Database(users,events);
        //DataManadgement.toJson("dataBase/database.json", database);
       // Database loadedDatabase = DataManadgement.fromJson("database.json");
        Display.connectionDisplay();

    }
}