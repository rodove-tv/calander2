package com.Calendar.DataManadgement;
import com.Calendar.Database.Database;
import com.Calendar.Display.Display;
import com.Calendar.User.User;
import com.Calendar.Events.Events;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.main.Main.database;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DataManadgement {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void readDatabaseFromFile(String filePath) {
        File jsonFile = new File(filePath);
        ;
        System.out.println("Reading file: " + filePath);

        if (jsonFile.exists()) {
            System.out.println("File exists: " + filePath);
            try {
                database = objectMapper.readValue(jsonFile, Database.class);
                System.out.println("Database initialized successfully: " + database);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to deserialize JSON.");
            }
        } else {
            System.out.println("File not found: " + filePath);
        }
        if (database == null) {
            System.out.println("Failed to initialize database.");
            System.err.println(3);
        }
    }

    public static void writeUsersToFile(String filePath, List<User> users) {
        try {
            objectMapper.writeValue(new File(filePath), users);
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

   public static User connectionUser(List<User> users) {
       String pseudo = Display.getConsoleInputString();
       for (User user : users) {
           if (Objects.equals(user.getPseudo(), pseudo)) {
               String password = Display.getConsoleInputString();
               do {
                   return user;
               } while (!(user.getPassword().equals(password)));
           }
       }
       System.out.println("pseudo not found");
       return connectionUser(users);
   }


    public static void toJson(String json,Object object){
        try {
            objectMapper.writeValue(new File(json), object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Convertir JSON en objet Java
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}