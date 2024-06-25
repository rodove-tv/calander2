package com.main;

import com.calendar.User.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.calendar.DataManadgement.DataManadgement;
import com.calendar.display.Display;
import com.calendar.events.Events;

import static com.calendar.display.Display.getConsoleInputString;
import static com.calendar.events.Events.addUserEventsListToAllEventsList;

public class Main {
    public static void main(String[] args) {
        String jsonFilePath = "dataBase/user.json";
        List<User> users = DataManadgement.readUsersFromFile(jsonFilePath);
        User user = User.createUser( users);
        users.add(user);
        addUserEventsListToAllEventsList(new ArrayList<>(), user);
        Display.receptionDisplay(user);
        DataManadgement.writeUsersToFile(jsonFilePath, users);
    }
}