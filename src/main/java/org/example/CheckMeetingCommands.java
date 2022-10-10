package org.example;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

public class CheckMeetingCommands {
    private final String[] commands = new String[]{"Show Employee One Working day", "Show Employee Two Working day", "Show Available Meeting Times"};
    private final Scanner scanner = new Scanner(System.in);

    public void printCommands() {
        for (int i = 0; i < commands.length; i++) {
            System.out.println((i + 1) + " : " + commands[i]);
        }
    }

    public int getUserInt(int rangeLimit) {
        int input = 0;
        boolean isActive = true;
        while (isActive) {
            try {
                System.out.println("Enter A number between 1 and " + rangeLimit);
                int userInput = scanner.nextInt();
                if (userInput > 0 && userInput <= rangeLimit) {
                    isActive = false;
                    input = userInput;
                } else {
                    System.out.println("unable to understand input please enter a number between 1 and " + rangeLimit);
                }
            } catch (InputMismatchException e) {
                System.out.println("Only Enter Numbers for this set of commands");
                scanner.nextLine();
            }
            scanner.nextLine();
        }
        return input;
    }

    public void printHashMap(HashMap<Integer, Integer> givenHashMap) {
        TreeMap<Integer, Integer> sortedByStartTime = new TreeMap<>(givenHashMap);
        for (int i : sortedByStartTime.keySet()) {
            if (String.valueOf(i).length() == 4) {
                String[] stringStartTime = Integer.toString(i).split("(?<=\\G..)");
                System.out.println("Start Time : " + stringStartTime[0] + ":" + stringStartTime[1]);
            } else {
                String[] stringStartTime = Integer.toString(i).split("");
                System.out.println("Start Time : " + stringStartTime[0] + ":" + stringStartTime[1] + stringStartTime[2]);
            }
            if (String.valueOf(sortedByStartTime.get(i)).length() == 4) {
                String[] stringFinishTime = Integer.toString(sortedByStartTime.get(i)).split("(?<=\\G..)");
                System.out.println("Finish Time : " + stringFinishTime[0] + ":" + stringFinishTime[1] + "\n");
            } else {
                String[] stringFinishTime = Integer.toString(sortedByStartTime.get(i)).split("");
                System.out.println("Finish Time : " + stringFinishTime[0] + ":" + stringFinishTime[1] + stringFinishTime[2] + "\n");
            }
        }
    }

    public void compareMeetingSchedules(Employee employeeOne, Employee employeeTwo) {
        HashMap<Integer, Integer> combinedHashMap = new HashMap<>() {{
            putAll(employeeOne.getWorkingHours());
            putAll(employeeTwo.getWorkingHours());
            putAll(employeeOne.getCurrentMeetings());
            putAll(employeeTwo.getCurrentMeetings());
        }};
        TreeMap<Integer, Integer> combTreeMap = new TreeMap<>(combinedHashMap);
        Calendar calendar = Calendar.getInstance();
        ArrayList<Integer> allTimesArr = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : combTreeMap.entrySet()) {
            allTimesArr.add(entry.getKey());
            allTimesArr.add(entry.getValue());
        }
        Collections.sort(allTimesArr);
        List<Time> intervals = new ArrayList<>();
        for (int i = 0; i < allTimesArr.size(); i++) {
            Integer start = allTimesArr.get(i);
            Integer end = allTimesArr.get(i + 1);
            i++;
            String[] startOfMeetingArr = start.toString().split("");
            String[] endOfMeetingArr = end.toString().split("");
            if (startOfMeetingArr.length == 4) {
                Time meetingStartTime = new Time(Integer.parseInt(startOfMeetingArr[0] + startOfMeetingArr[1]), Integer.parseInt(startOfMeetingArr[2] + startOfMeetingArr[3]) - 15, 0);
                Time meetingFinishTime = new Time(Integer.parseInt(endOfMeetingArr[0] + endOfMeetingArr[1]), Integer.parseInt(endOfMeetingArr[2] + startOfMeetingArr[3]) - 15, 0);
                calendar.setTime(meetingStartTime);
                while (calendar.getTime().before(meetingFinishTime)) {
                    calendar.add(Calendar.MINUTE, 15);
                    intervals.add(new Time(calendar.getTimeInMillis()));
                }
            } else if (startOfMeetingArr.length == 3 && endOfMeetingArr.length == 3) {
                Time meetingStartTime = new Time(Integer.parseInt(startOfMeetingArr[0]), Integer.parseInt(startOfMeetingArr[1]) - 15, 0);
                Time meetingFinishTime = new Time(Integer.parseInt(endOfMeetingArr[0]), Integer.parseInt(endOfMeetingArr[1]) - 15, 0);
                calendar.setTime(meetingStartTime);
                while (calendar.getTime().before(meetingFinishTime)) {
                    calendar.add(Calendar.MINUTE, 15);
                    intervals.add(new Time(calendar.getTimeInMillis()));
                }
            } else {
                Time meetingStartTime = new Time(Integer.parseInt(startOfMeetingArr[0]), Integer.parseInt(startOfMeetingArr[1]) - 15, 0);
                Time meetingFinishTime = new Time(Integer.parseInt(endOfMeetingArr[0] + endOfMeetingArr[1]), Integer.parseInt(endOfMeetingArr[2] + endOfMeetingArr[3]) - 15, 0);
                calendar.setTime(meetingStartTime);
                while (calendar.getTime().before(meetingFinishTime)) {
                    calendar.add(Calendar.MINUTE, 15);
                    intervals.add(new Time(calendar.getTimeInMillis()));
                }
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        for (Time time : intervals) {
            System.out.println(simpleDateFormat.format(time));
        }
    }

    public void runCommands(Employee employeeOne, Employee employeeTwo) {
        printCommands();
        int userIntInput = getUserInt(3);
        switch (userIntInput) {
            case 1:
                System.out.println("\n" + employeeOne.getName() + ":");
                System.out.println("Working Hours :");
                printHashMap(employeeOne.getWorkingHours());
                System.out.println("Today's Meetings :");
                printHashMap(employeeOne.getCurrentMeetings());
                runCommands(employeeOne, employeeTwo);
                break;
            case 2:
                System.out.println("\n" + employeeTwo.getName() + ":");
                System.out.println("Working Hours :");
                printHashMap(employeeTwo.getWorkingHours());
                System.out.println("Today's Meetings :");
                printHashMap(employeeTwo.getCurrentMeetings());
                runCommands(employeeOne, employeeTwo);
                break;
            case 3:
                System.out.println("\nFree meeting slots at 15 Minute intervals :");
                compareMeetingSchedules(employeeOne, employeeTwo);
                runCommands(employeeOne, employeeTwo);
                break;
            default:
                System.out.println("Pick a number with in the range available");
                break;
        }
    }
}
