package codewars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LeapYears {

    @Test
    public void test() {
        assertTrue(LeapYears.isLeapYear(2020));
        assertTrue(LeapYears.isLeapYear(2000));
        assertFalse(LeapYears.isLeapYear(2015));
        assertFalse(LeapYears.isLeapYear(2100));
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }
}
