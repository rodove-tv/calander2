package com.calendar.DataManadgement;
import com.calendar.user.User;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.main.Main;
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
}