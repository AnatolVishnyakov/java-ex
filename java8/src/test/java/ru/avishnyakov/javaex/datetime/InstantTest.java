package ru.avishnyakov.javaex.datetime;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstantTest {
    private static final Instant instant = Instant.ofEpochMilli(1618678865548L);

    @Test
    public void testAtZone() {
        assertEquals(ZoneId.of("Europe/Moscow"), instant.atZone(ZoneId.of("Europe/Moscow")).getZone());
    }
}
