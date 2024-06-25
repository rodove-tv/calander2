package com.calendar.DataManadgement;
import com.calendar.User.User;
import com.calendar.display.Display;
import com.calendar.events.Events;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.main.Main;
import jdk.jfr.Event;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DataManadgement {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static List<User> readUsersFromFile(String filePath) {
        List<User> users = new ArrayList<>();
        File jsonFile = new File(filePath);

        if (jsonFile.exists()) {
            try {
                users = objectMapper.readValue(jsonFile, new TypeReference<List<User>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public static void writeUsersToFile(String filePath, List<User> users) {
        try {
            objectMapper.writeValue(new File(filePath), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<List<Events>> readEventsFromFile(String filePath) {
        List<List<Events>> events = new ArrayList<>();
        File jsonFile = new File(filePath);

        if (jsonFile.exists()) {
            try {
                events = objectMapper.readValue(jsonFile, new TypeReference<List<List<Events>>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return events;
    }
    public static void writeEventsToFile(String filePath, List<List<Events>> event) {
        try {
            objectMapper.writeValue(new File(filePath), event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public static void hashPassword(String password, User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
    }


    public static boolean checkPassword(String rawPassword, User user) {
        // Later, when the user tries to log in:
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = user.getPassword(); // This should come from the user input
        return passwordEncoder.matches(rawPassword, hashedPassword);

    }

    public static User connectionUser(List<User> tab_user) {
        do {
            System.out.println("Enter your pseudo or EXIT(in upper case): ");
            String Pseudo = Display.getConsoleInputString();
            if (Pseudo == "EXIT"){
                return null;
            }
            for (User user : tab_user) {
                if (Objects.equals(Pseudo, user.getPseudo())) {
                    return user;
                }
            }
            System.out.println("the pseudo is incorrect please try again");
        } while (true);
    }



}