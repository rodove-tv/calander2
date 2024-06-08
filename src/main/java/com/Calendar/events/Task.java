package com.Calendar.events;

import com.Calendar.display.Display;

public class Task extends Events{

        private String taskName;
        private String taskDescription;

        public Task() {
            super();
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public void setTaskDescription(String taskDescription) {
            this.taskDescription = taskDescription;
        }

        public String getTaskName() {
            return taskName;
        }

        public String getTaskDescription() {
            return taskDescription;
        }

        public void displayTaskEvent() {
            System.out.println("Event name: " + getEventName());
            System.out.println("Event owner: " + getEventOwner());
            System.out.println("Event date: " + getDate());
            System.out.println("Event location: " + getLocation());
            System.out.println("Event description: " + getDescription());
            System.out.println("Task name: " + getTaskName());
            System.out.println("Task description: " + getTaskDescription());
        }

    public void createTaskEvent() {
        System.out.println("Enter the name of the task: ");
        setTaskName(Display.getConsoleInputString());
        System.out.println("Enter the description of the task: ");
        setTaskDescription(Display.getConsoleInputString());
    }
}
