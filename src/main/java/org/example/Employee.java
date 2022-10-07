package org.example;
import java.util.HashMap;
public class Employee {
    private String name;
    private HashMap<Integer, Integer> currentMeetings;
    private HashMap<Integer, Integer> workingHours;
    public Employee(String name) {
        this.name = name;
    }
    public HashMap<Integer, Integer> getCurrentMeetings() {
        return currentMeetings;
    }
    public void setCurrentMeetings(HashMap<Integer, Integer> currentMeetings) {
        this.currentMeetings = currentMeetings;
    }
    public HashMap<Integer, Integer> getWorkingHours() {
        return workingHours;
    }
    public void setWorkingHours(HashMap<Integer, Integer> workingHours) {
        this.workingHours = workingHours;
    }
    public String getName() {
        return name;
    }
}
