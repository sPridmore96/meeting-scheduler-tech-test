package org.example;
import java.util.HashMap;
public class Main {
    public static void main(String[] args) {
        Employee employeeOne = new Employee("Sam");
        employeeOne.setCurrentMeetings(new HashMap<>(){{
            put(1000,1100);
        }});
        employeeOne.setWorkingHours(new HashMap<>(){{
            put(900, 1700);
        }});
        Employee employeeTwo = new Employee("Andy");
        employeeTwo.setCurrentMeetings(new HashMap<>(){{
            put(1200, 1330);
            put(1500,1600);
        }});
        employeeTwo.setWorkingHours(new HashMap<>(){{
            put(900, 1700);
        }});
        CheckMeetingCommands newCommands = new CheckMeetingCommands();
        newCommands.runCommands(employeeOne, employeeTwo);
    }
}