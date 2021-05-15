package ru.avishnyakov.javaex.datetime;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstantTest {
    private static final long EPOCH_SECOND = 1618678865L;   // 2021-04-17T17:01:05Z
    private static final long EPOCH_MILLI = 1618678865548L; // 2021-04-17T17:01:05.548Z
    private static final long NANO_SECOND = 999_999_999;
    private static final Instant instant = Instant.ofEpochMilli(EPOCH_MILLI);
    private static final Instant instantWithNano = instant.with(ChronoField.NANO_OF_SECOND, NANO_SECOND); // 2021-04-17T17:01:05.999999999Z

    @Test
    public void testAtZone() {
        assertEquals(ZoneId.of("Europe/Moscow"), instant.atZone(ZoneId.of("Europe/Moscow")).getZone());
    }

    @Test
    public void testEpochMilli() {
        assertEquals(instant, Instant.ofEpochMilli(EPOCH_MILLI));
    }

    @Test
    public void testEpochSecond() {
        assertEquals(instant.truncatedTo(ChronoUnit.SECONDS), Instant.ofEpochSecond(EPOCH_SECOND));
    }

    @Test
    public void testEpochSecondWithNanoSeconds() {
        assertEquals(instantWithNano, Instant.ofEpochSecond(EPOCH_SECOND, NANO_SECOND));
    }

    @Test
    public void testFrom() {
        assertEquals(instant, Instant.from(ZonedDateTime.ofInstant(instant, ZoneId.systemDefault())));
        assertEquals(instant, Instant.from(OffsetDateTime.ofInstant(instant, ZoneId.systemDefault())));
        assertEquals(instant, Instant.from(instant));
    }

    @Test
    public void testParse() {
        assertEquals(instant, Instant.parse("2021-04-17T17:01:05.548Z"));
    }

    @Test
    public void testConstants() {
        assertEquals(Instant.parse("1970-01-01T00:00:00Z"), Instant.EPOCH);
        assertEquals(Instant.parse("-1000000000-01-01T00:00:00Z"), Instant.MIN);
        assertEquals(Instant.parse("+1000000000-12-31T23:59:59.999999999Z"), Instant.MAX);
    }
}
