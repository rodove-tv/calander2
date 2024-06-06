package com.main;

import com.calendar.user.User;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import com.calendar.DataManadgement.DataManadgement;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in); // Déplacer l'initialisation ici

        // Chemin du fichier JSON
        String jsonFilePath = "user.json";

        // Lire les utilisateurs existants
        List<User> users = DataManadgement.readUsersFromFile(jsonFilePath);

        // Créer et ajouter les nouveaux utilisateurs
        User user1 = User.createUser(scanner, users);
        users.add(user1);
        User user2 = User.createUser(scanner, users);
        users.add(user2);

        // Enregistrer les utilisateurs dans le fichier JSON
        DataManadgement.writeUsersToFile(jsonFilePath, users);
    }
}