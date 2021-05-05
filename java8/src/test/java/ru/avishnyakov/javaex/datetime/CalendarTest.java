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
    public void test() {
        System.out.println(Calendar.getAvailableCalendarTypes());
        System.out.println(Calendar.getInstance());
        System.out.println(Calendar.getInstance(TimeZone.getTimeZone("America/New_York")));
        System.out.println(Calendar.getInstance(Locale.ENGLISH));
        System.out.println(Arrays.toString(Calendar.getAvailableLocales()));

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        System.out.println(Calendar.getInstance().getClass());
    }

    @Test
    public void testDayOfWeek() {
        assertEquals("Пн", getDayOfWeek());
    }

    private String getDayOfWeek() {
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, new Locale("ru"));
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
}
