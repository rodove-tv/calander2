package com.main;

import com.Calendar.User.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.Calendar.DataManadgement.DataManadgement;
import com.Calendar.Display.Display;

import static com.Calendar.Events.Events.addUserEventsListToAllEventsList;

public class Main {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            String jsonFilePath = "user.json";
            List<User> users = DataManadgement.readUsersFromFile(jsonFilePath);

            User user1 = User.createUser(scanner, users);
            if (user1 != null) {
                users.add(user1);
                addUserEventsListToAllEventsList(new ArrayList<>(), user1);
                Display.receptionDisplay(user1);
            }
            DataManadgement.writeUsersToFile(jsonFilePath, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }








}