package com.Calendar.events;

import com.Calendar.User.User;
import com.Calendar.display.Display;
//import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

import static com.Calendar.display.Display.ANSI_RESET;
import static com.Calendar.display.Display.ANSI_WHITE;

public class Birthday extends Events {

        private String gift;
        private final String Type = "Birthday";


        public Birthday() {
            super();
            setType(Type);
        }



        public void setGift(String gift) {
            this.gift = gift;
        }

        public String getGift() {
            return gift;
        }



        @Override
        public void basicEventInfos(/*@NotNull*/ User user) {
            setEventOwner(user.getName());
            System.out.println(ANSI_WHITE + "<------------------" + ANSI_RESET + "New Event : Birthday" + ANSI_WHITE + "------------------>" + ANSI_RESET);
            System.out.println("Enter the name of the birthday person: ");
            setEventName(Display.getConsoleInputString());
            System.out.println("(Optional) Enter the description of the Birthday: ");
            setDescription(Display.getConsoleInputString());
            System.out.println("Enter the gift you want to offer: ");
            setGift(Display.getConsoleInputString());
            System.out.println("Enter the year of the Birthday: ");
            setYear(Display.getConsoleInputInt(99999));
            System.out.println("Enter the month of the Birthday : ");
            setMonth(Display.getConsoleInputInt(12));
            System.out.println("Enter the day of the Birthday: ");
            setDay(Display.getConsoleInputInt(31));
            addDetailsToEvent();
            setDate();
        }

        //display the birthday event
        public static void displayEvent(Birthday event) {
            System.out.println("Event owner: " + event.getEventOwner());
            System.out.println("Birthday person: " + event.getEventName());
            System.out.println("Location: " + event.getLocation());
            System.out.println("Description: " + event.getDescription());
            System.out.println("Gift: " + event.getGift());
        }
    }

