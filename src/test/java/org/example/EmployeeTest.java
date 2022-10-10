package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EmployeeTest {
    Employee employeeTest;

    @BeforeEach
    public void init() {
        employeeTest = new Employee("Testing");
    }

    @Test
    public void updatesEmployeeMeetings_PutMeeting_ReturnsNewMeetings() {
        employeeTest.setCurrentMeetings(new HashMap<>() {{
            put(1000, 1100);
            put(1300, 1400);
        }});
        assertNotEquals(null, employeeTest.getCurrentMeetings());
    }

    @Test
    public void getEmployeeWorkingHours_hasHashMapNoNullValues_ReturnsWorkingHoursNotNull() {
        employeeTest.setWorkingHours(new HashMap<>() {{
            put(900, 1700);
        }});
        for (Map.Entry<Integer, Integer> entry : employeeTest.getWorkingHours().entrySet()) {
            Integer start = entry.getKey();
            Integer finish = entry.getValue();
            assertNotEquals(null, start);
            assertNotEquals(null, finish);
        }
    }

}