package com.calendar.User;
import com.calendar.display.Display;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.calendar.display.Display.getConsoleInputString;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class User {
    private int id;
    private String name;
    private String userPseudo;
    private List<String> family;
    private String password;

    // Getters
    public int getId() {return id;}
    public String getName() {return name;}
    public List<String> getFamily() {return family;}
    public String getPseudo() {return userPseudo;}
    public String getPassword() {return password;}
    private User getUser() {return this;}

    // Setters
    public void setPassword(String Password) {
        password = Password;
    }

    // Default constructor (required for Jackson)
    public User() {}

    // Constructor with parameters (can be used by Jackson)
    @JsonCreator
    public User(@JsonProperty("id") int id,
                @JsonProperty("name") String name,
                @JsonProperty("UserPseudo") String UserPseudo,
                @JsonProperty("family") List<String> family,
                @JsonProperty("mdp") String password) {
        this.id = id;
        this.name = name;
        this.userPseudo = UserPseudo;
        this.family = family;
        this.password = password;
    }

    // Methods
    private void printFamily() {
        for (int i = 0; i < family.size(); i++) {
            System.out.println(i + ". " + family.get(i));
        }
    }

    public void printUser() {
        System.out.println("Name: " + name);
        System.out.println("UserPseudo: " + userPseudo);
        if (!family.isEmpty()) {
            System.out.println("Family: ");
            printFamily();
        } else {
            System.out.println("No family");
        }
        System.out.println("MDP: " + password);
    }

    public static User createUser(List<User> tab_user) {
        System.out.print("Enter your name: ");
        String name = getConsoleInputString();
        String pseudo;
        boolean pseudoExists;
        do {
            System.out.print("Enter your pseudo: ");
            pseudo = getConsoleInputString();
            pseudoExists = false;
            if (pseudo == "EXIT"){
                pseudoExists = true;
                System.out.println("this Pseudo is not allowed to be used");
            }
            for (User user : tab_user) {
                if (user.getPseudo().equals(pseudo)) {
                    pseudoExists = true;
                    System.out.println("Pseudo already used. Please enter another pseudo.");
                    break;
                }
            }
        } while (pseudoExists);
        String mdp;
        String mdp_confirmed;
        do {
            System.out.print("Enter your password: ");
            mdp = getConsoleInputString();
            System.out.print("Confirm your password: ");
            mdp_confirmed = getConsoleInputString();
            if (!mdp.equals(mdp_confirmed)) {
                System.out.println("Passwords do not match. Please try again.");
            }
        } while (!mdp.equals(mdp_confirmed));
        List<String> family = new ArrayList<>();
        return new User(1, name, pseudo, family, mdp);
    }

    public void createFamily() {
        String nameFamily;
        do {
            System.out.print("Enter a name of family: ");
            nameFamily = getConsoleInputString();
            nameFamily = userPseudo + nameFamily;
            if (family.contains(nameFamily)) {
                System.out.println("Name family already exists. Here are all the families you have: ");
                printFamily();
            }
        } while (family.contains(nameFamily));
        family.add(nameFamily);
    }

    public void deleteFamily() throws IOException {
        String familyDel;
        int familyDelInt;
        do {
            printFamily();
            System.out.print("Enter the number of the family to delete: ");
            familyDel = getConsoleInputString();
            familyDelInt = Integer.parseInt(familyDel);
            if (familyDelInt >= family.size() || familyDelInt < 0) {
                System.out.println("Family does not exist. Please try again.");
            }
        } while (familyDelInt >= family.size() || familyDelInt < 0);

        family.remove(familyDelInt);
    }

    public void addUserFamily(List<User> tab_user) throws IOException {
        System.out.print("Enter the pseudo of the user to add to the family: ");
        String pseudo_add = getConsoleInputString();
        User user = getUserByPseudo(tab_user, pseudo_add);

        if (user == null) {
            System.out.println("User with pseudo " + pseudo_add + " not found.");
            return;
        }
        System.out.println("Current family members:");
        for (int i = 0; i < family.size(); i++) {
            System.out.println(i + " : " + family.get(i));
        }
        System.out.println("Enter the number of the family");
        boolean family_out_of_range;
        do {
            family_out_of_range = false;
            String number = getConsoleInputString();
            int numberInt = Integer.parseInt(number);
            if (numberInt >= family.size() || numberInt < 0) {
                family_out_of_range = true;
                System.out.println("Please enter a valid number.");
            } else {
                user.family.add(family.get(numberInt));
            }
        } while (family_out_of_range);
    }

    public static User getUserByPseudo(List<User> users, String pseudo) {
        for (User user : users) {
            if (user.getPseudo().equals(pseudo)) {
                return user;
            }
        }
        return null; // Si aucun utilisateur n'est trouvé avec ce pseudo
    }

    public void changePseudo() {
        System.out.println("Enter your new password: ");
        String Password = getConsoleInputString();
        System.out.println("Your password will be " + Password + ". Are you sure? 1:yes 2:no");
        int choice = Display.getConsoleInputInt(2);
        if (choice == 1) {
            setPassword(Password);
            System.out.println("Your password has been changed to " + Password);
            Display.receptionDisplay(this);
        } else {
            changePseudo();
        }
    }




}