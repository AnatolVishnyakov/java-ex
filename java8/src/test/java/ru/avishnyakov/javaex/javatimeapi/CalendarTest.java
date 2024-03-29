package ru.avishnyakov.javaex.javatimeapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalendarTest {
    private static final Calendar calendar;

    static {
        calendar = Calendar.getInstance();
        calendar.setTime(new Date(1618851665548L)); // 2021-04-19T20:01:05.548+03:00
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
        assertEquals(2, calendar.get(Calendar.DAY_OF_WEEK));
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

    @Test
    @DisplayName("Тип календаря")
    public void testCalendarType() {
        assertEquals("gregory", calendar.getCalendarType());
    }

    @Test
    @DisplayName("Типы календарей")
    public void testCalendarTypes() {
        assertEquals(new HashSet<>(Arrays.asList("gregory", "buddhist", "japanese")), Calendar.getAvailableCalendarTypes());
    }
}
