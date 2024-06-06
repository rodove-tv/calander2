package com.calendar.user;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class User {
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
            System.out.print("confirmed your password: ");
            mdp_confirmed = scanner.nextLine();
        }while (!mdp.equals(mdp_confirmed));{
            System.out.println("Passwords do not match. Please try again.");
        }
        List<String> family = List.of();
        User user = new User(1,name,pseudo,family,mdp);
        return user;
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
    private int id;
    private String name;
    private String UserPseudo;
    private List<String> family;
    private String mdp;


    public User(int id, String name,String UserPseudo,List<String> family, String mdp) {
        this.id = id;
        this.name = name;
        this.UserPseudo = UserPseudo;
        this.family = family;
        this.mdp = mdp;
    }
    public void printUser(){
        System.out.println("Name: " + name);
        System.out.println("UserPseudo: " + UserPseudo);
        if (family.get(0) !=""){
            System.out.println("Family: ");
            for (int i = 0; i < family.size(); i++) {
                System.out.println(family.get(i));
            }
        }else {
            System.out.println("No family");
        }
        System.out.println("MDP: " + mdp);
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<String> getFamily() {
        return family;
    }
    public String getPseudo() {
        return UserPseudo;
    }
    public String getMdp() {
        return mdp;
    }


    public static User getUserByPseudo(List<User> users, String pseudo) {
        for (User user : users) {
            if (user.getPseudo().equals(pseudo)) {
                return user;
            }
        }
        return null; // Si aucun utilisateur n'est trouvé avec ce pseudo
    }

}

