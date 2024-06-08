package com.Calendar.events;

import com.Calendar.display.Display;
import com.Calendar.users.User;

import java.time.LocalDateTime;

    public class Birthday extends Events {

        private String birthdayPerson;
        private String gift;

        public Birthday(String eventName, String eventOwner, int year, int month, int day, int hourOfDay, int minuteOfDay, LocalDateTime date, String location, String description, com.Calendar.users.User user, String birthdayPerson, String gift) {
            super(eventName, eventOwner, year, month, day, hourOfDay, minuteOfDay, date, location, description, user);
            this.birthdayPerson = birthdayPerson;
            this.gift = gift;
        }
        public Birthday() {
            super();
        }

        public void createBirthdayEvent() {
            System.out.println("Enter the name of the birthday person: ");
            setBirthdayPerson(Display.getConsoleInputString());
            System.out.println("Enter the gift you want to offer: ");
            setGift(Display.getConsoleInputString());
        }

        public void setBirthdayPerson(String birthdayPerson) {
            this.birthdayPerson = birthdayPerson;
        }

        public void setGift(String gift) {
            this.gift = gift;
        }

        public String getBirthdayPerson() {
            return birthdayPerson;
        }

        public String getGift() {
            return gift;
        }

        public void displayBirthdayEvent() {
            System.out.println("Event name: " + getEventName());
            System.out.println("Event owner: " + getEventOwner());
            System.out.println("Event date: " + getDate());
            System.out.println("Event location: " + getLocation());
            System.out.println("Event description: " + getDescription());
            System.out.println("Birthday person: " + getBirthdayPerson());
            System.out.println("Gift: " + getGift());
        }
    }

