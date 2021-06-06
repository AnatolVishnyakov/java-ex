package ru.avishnyakov.javaex.datetime;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class DateFormatTest {
    private static final Date currentDate = new Date(1618678865548L);

    @Test
    public void test() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        dateFormat.setLenient(false);
        dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Europe/Berlin")));
        System.out.println(dateFormat.format(new Date()));
    }
}
