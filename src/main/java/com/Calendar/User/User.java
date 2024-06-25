package com.Calendar.User;
import com.Calendar.Display.Display;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private int id;
    private String name;
    private String UserPseudo;
    private List<String> family;
    private String password;

    public int getId() {return id;}
    public String getName() {return name;}
    public List<String> getFamily() {return family;}
    public String getPseudo() {return UserPseudo;}
    public String getPassword() {return password;}
    private User getUser() {return this;}

    public String setPassword(String Password) {return Password = password;}

    private void printFamily(){
        for (int i = 0; i < family.size(); i++) {
            System.out.println(i + ". " + family.get(i));
        }
    }
    public void printUser(){
        System.out.println("Name: " + name);
        System.out.println("UserPseudo: " + UserPseudo);
        if (family.get(0) !=""){
            System.out.println("Family: ");
            printFamily();
        }else {
            System.out.println("No family");
        }
        System.out.println("MDP: " + password);
    }


    public static User createUser(Scanner scanner,List<User> tab_user) throws IOException {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        String pseudo;
        boolean pseudoExists;
        do {
            System.out.print("Enter your pseudo: ");
            pseudo = scanner.nextLine();
            pseudoExists = false;
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
        do{
            System.out.print("Enter your password: ");
            mdp = scanner.nextLine();
            System.out.print("Confirm your password: ");
            mdp_confirmed = scanner.nextLine();
            if (!mdp.equals(mdp_confirmed)) {
                System.out.println("Passwords do not match. Please try again.");
            }
        } while (!mdp.equals(mdp_confirmed));
        List<String> family = new ArrayList<>();
        return new User(1,name,pseudo,family,mdp);
    }

    public void createFamily(Scanner scanner) throws IOException {
        String nameFamily;
        do {
            System.out.print("Enter a name of family : ");
            nameFamily = scanner.nextLine();
            nameFamily = UserPseudo+nameFamily;
            if (family.contains(nameFamily)) {
                System.out.println("Name family existe déja voilà toute les famille que vous posédait : ");
                printFamily();
            }
        }while(family.contains(nameFamily));
        family.add(nameFamily);
    }
    public void deleteFamily(Scanner scanner) throws IOException {
        String familyDel;
        int familyDelInt;
        do {
            printFamily();
            System.out.print("Enter a number of family to delete: ");
            familyDel = scanner.nextLine();
            familyDelInt = Integer.parseInt(familyDel);
            if (familyDelInt >= family.size() || familyDelInt < 0) {
                System.out.println("Family does not exist. Please try again.");
            }
        } while (familyDelInt >= family.size() || familyDelInt < 0);

        family.remove(familyDelInt);
    }
    public void addFamily(Scanner scanner,List<User> tab_user) throws IOException {
        System.out.print("gives the pseudo of the user to add to the family");
        String pseudo_add = scanner.nextLine();
        User user = getUserByPseudo(tab_user,pseudo_add);

        if (user == null) {
            System.out.println("User with pseudo " + pseudo_add + " not found.");
            return;
        }


        System.out.println("Current family members:");
        for (int i = 0; i < family.size(); i++) {
           System.out.print(i+" : "+ family.get(i));
        }
        System.out.println("Enter the nuber of the family");
        boolean family_out_of_range;
        do {
            family_out_of_range = false;
            String number = scanner.nextLine();
            int numberInt = Integer.parseInt(number);
            if (numberInt >= family.size() || numberInt < 0) {
                family_out_of_range = true;
                System.out.println("Please enter a valid number.");
            } else {
                user.family.add(family.get(numberInt));
            }
        }while (family_out_of_range);


    }
    public static User getUserByPseudo(List<User> users, String pseudo) {
        for (User user : users) {
            if (user.getPseudo().equals(pseudo)) {
                return user;
            }
        }
        return null; // Si aucun utilisateur n'est trouvé avec ce pseudo
    }

    public User(int id, String name,String UserPseudo,List<String> family, String password) {
        this.id = id;
        this.name = name;
        this.UserPseudo = UserPseudo;
        this.family = family;
        this.password = password;
    }

    public void changePassword() {
        System.out.println("Enter your new password: ");
        String Password = Display.getConsoleInputString();
        System.out.println("Your password will be" + Password + "Are you sure? 1:yes 2:no");
        int choice = Display.getConsoleInputInt(2);
        if (choice == 1) {
            setPassword(Password);
            System.out.println("Your password has been changed to " + Password);
            Display.receptionDisplay(this);
        } else {
            changePassword();
        }
    }

}


