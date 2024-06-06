package com.calendar.users;

public class User {

    private String name;
    private String email;
    private String phone;

    public void setName(String name) {this.name = name;}
    public void setEmail(String email) {this.email = email;}
    public void setPhone(String phone) {this.phone = phone;}

    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getPhone() {return phone;}



    public static User[] getAllUsers() {
        User[] users = new User[2];
        User user1 = new User();
        user1.setName("John Doe");
        user1.setEmail("");
        return users;
    }

    public static User getUser(String name) {
        User user = new User();
        user.setName(name);
        return user;
    }


}
