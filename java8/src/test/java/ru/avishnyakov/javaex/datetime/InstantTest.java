package ru.avishnyakov.javaex.datetime;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
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
}
