package ru.avishnyakov.javaex.datetime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalendarTest {
    private static final Calendar calendar;

    static {
        calendar = Calendar.getInstance();
        calendar.setTime(new Date(1618851665548L));
    }

    @Test
    @DisplayName("Год")
    public void testYear() {
        assertEquals(2021, calendar.getWeekYear());
        assertEquals(2021, calendar.get(Calendar.YEAR));
    }

    @Test
    @DisplayName("Недель в году")
    public void testWeeksInYear() {
        assertEquals(52, calendar.getWeeksInWeekYear());
    }

    @Test
    @DisplayName("Недель (сначала года) прошло")
    public void testWeekOfYear() {
        assertEquals(17, calendar.get(Calendar.WEEK_OF_YEAR));
    }

    @Test
    @DisplayName("Дней (сначала года) прошло")
    public void testDayOfYear() {
        assertEquals(109, calendar.get(Calendar.DAY_OF_YEAR));
    }

    @Test
    @DisplayName("Месяц (отсчет с 0)")
    public void testMonth() {
        // April
        assertEquals(3, calendar.get(Calendar.MONTH));
    }

    @Test
    @DisplayName("Дней (сначала месяца) прошло")
    public void testDayOfMonth() {
        assertEquals(19, calendar.get(Calendar.DAY_OF_WEEK));
    }

    @Test
    @DisplayName("Недель (сначала месяца) прошло")
    public void testWeekOfMonth() {
        assertEquals(4, calendar.get(Calendar.WEEK_OF_MONTH));;
    }

    @Test
    public void testDayOfWeekInMonth() {
        assertEquals(3, calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
    }
}
