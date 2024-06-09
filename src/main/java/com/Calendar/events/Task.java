package com.Calendar.events;

import com.Calendar.User.User;
import com.Calendar.display.Display;
//import org.jetbrains.annotations.NotNull;

import static com.Calendar.display.Display.ANSI_RESET;
import static com.Calendar.display.Display.ANSI_WHITE;

public class Task extends Events{


        private final String Type = "Task";


        public Task() {
            super();
            setType(Type);
        }


        @Override
        public void basicEventInfos(/*@NotNull*/ User user){

            setEventOwner(user.getName());
            System.out.println(ANSI_WHITE +"<------------------"+ ANSI_RESET +"New Event : Task" + ANSI_WHITE +"------------------>"+ANSI_RESET);
            System.out.println("Enter the name of the task: ");
            setEventName(Display.getConsoleInputString());
            System.out.println("(Optional) Enter the description of the Task: ");
            setDescription(Display.getConsoleInputString());
            System.out.println("Enter the year of the Task: ");
            setYear(Display.getConsoleInputInt(99999));
            System.out.println("Enter the month of the Task : ");
            setMonth(Display.getConsoleInputInt(12));
            System.out.println("Enter the day of the Task: ");
            setDay(Display.getConsoleInputInt(31));
            addDetailsToEvent();
            setDate();

        }


        //display the task event
        public static void displayEvent(Task event) {
        System.out.println("Task name: " + event.getEventName());
        System.out.println("Location: " + event.getLocation());
        System.out.println("Description: " + event.getDescription());
        }
}
