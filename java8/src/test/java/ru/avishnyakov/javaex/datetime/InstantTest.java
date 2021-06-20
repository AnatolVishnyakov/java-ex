package ru.avishnyakov.javaex.datetime;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InstantTest {
    private static final long EPOCH_SECOND = 1618678865L;   // 2021-04-17T17:01:05Z
    private static final long EPOCH_MILLI = 1618678865548L; // 2021-04-17T17:01:05.548Z
    private static final long NANO_SECOND = 999_999_999;
    private static final Instant instant = Instant.ofEpochMilli(EPOCH_MILLI);
    private static final Instant instantWithNano = instant.with(ChronoField.NANO_OF_SECOND, NANO_SECOND); // 2021-04-17T17:01:05.999999999Z

    @Test
    public void testAtZone() {
        assertEquals(ZoneId.of("Europe/Moscow"), instant.atZone(ZoneId.of("Europe/Moscow")).getZone());
        List<ZonedDateTime> list = new ArrayList<>();
        for (String zone : ZoneId.SHORT_IDS.values()) {
            list.add(Instant.now().atZone(ZoneId.of(zone)));
        }
        list.sort(Comparator.comparing(ZonedDateTime::toLocalDateTime));
        list.forEach(System.out::println);
    }

    @Test
    public void testEpochMilli() {
        assertEquals("2021-04-17T17:01:05.548Z", Instant.ofEpochMilli(EPOCH_MILLI).toString());
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

    @Test
    public void testNow() {
        assertEquals(instant, Instant.now(Clock.fixed(instant, ZoneId.systemDefault())));
    }

    @Test
    public void testIsSupportedChronoField() {
        assertTrue(instant.isSupported(ChronoField.NANO_OF_SECOND));
        assertTrue(instant.isSupported(ChronoField.MICRO_OF_SECOND));
        assertTrue(instant.isSupported(ChronoField.MILLI_OF_SECOND));
        assertTrue(instant.isSupported(ChronoField.INSTANT_SECONDS));

        assertFalse(instant.isSupported(ChronoField.NANO_OF_DAY));
        assertFalse(instant.isSupported(ChronoField.MICRO_OF_DAY));
        assertFalse(instant.isSupported(ChronoField.MILLI_OF_DAY));
        assertFalse(instant.isSupported(ChronoField.SECOND_OF_MINUTE));
        assertFalse(instant.isSupported(ChronoField.SECOND_OF_DAY));
        assertFalse(instant.isSupported(ChronoField.MINUTE_OF_HOUR));
        assertFalse(instant.isSupported(ChronoField.MINUTE_OF_DAY));
        assertFalse(instant.isSupported(ChronoField.HOUR_OF_AMPM));
        assertFalse(instant.isSupported(ChronoField.CLOCK_HOUR_OF_AMPM));
        assertFalse(instant.isSupported(ChronoField.HOUR_OF_DAY));
        assertFalse(instant.isSupported(ChronoField.CLOCK_HOUR_OF_DAY));
        assertFalse(instant.isSupported(ChronoField.AMPM_OF_DAY));
        assertFalse(instant.isSupported(ChronoField.DAY_OF_WEEK));
        assertFalse(instant.isSupported(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
        assertFalse(instant.isSupported(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR));
        assertFalse(instant.isSupported(ChronoField.DAY_OF_MONTH));
        assertFalse(instant.isSupported(ChronoField.DAY_OF_YEAR));
        assertFalse(instant.isSupported(ChronoField.EPOCH_DAY));
        assertFalse(instant.isSupported(ChronoField.ALIGNED_WEEK_OF_MONTH));
        assertFalse(instant.isSupported(ChronoField.ALIGNED_WEEK_OF_YEAR));
        assertFalse(instant.isSupported(ChronoField.MONTH_OF_YEAR));
        assertFalse(instant.isSupported(ChronoField.PROLEPTIC_MONTH));
        assertFalse(instant.isSupported(ChronoField.YEAR_OF_ERA));
        assertFalse(instant.isSupported(ChronoField.YEAR));
        assertFalse(instant.isSupported(ChronoField.ERA));
        assertFalse(instant.isSupported(ChronoField.OFFSET_SECONDS));
    }

    @Test
    public void testIsSupportedChronoUnit() {
        assertTrue(instant.isSupported(ChronoUnit.NANOS));
        assertTrue(instant.isSupported(ChronoUnit.MICROS));
        assertTrue(instant.isSupported(ChronoUnit.MILLIS));
        assertTrue(instant.isSupported(ChronoUnit.SECONDS));
        assertTrue(instant.isSupported(ChronoUnit.MINUTES));
        assertTrue(instant.isSupported(ChronoUnit.HOURS));
        assertTrue(instant.isSupported(ChronoUnit.HALF_DAYS));
        assertTrue(instant.isSupported(ChronoUnit.DAYS));

        assertFalse(instant.isSupported(ChronoUnit.WEEKS));
        assertFalse(instant.isSupported(ChronoUnit.MONTHS));
        assertFalse(instant.isSupported(ChronoUnit.YEARS));
        assertFalse(instant.isSupported(ChronoUnit.DECADES));
        assertFalse(instant.isSupported(ChronoUnit.CENTURIES));
        assertFalse(instant.isSupported(ChronoUnit.MILLENNIA));
        assertFalse(instant.isSupported(ChronoUnit.ERAS));
        assertFalse(instant.isSupported(ChronoUnit.FOREVER));
    }

    @Test
    public void testRange() {
        assertEquals(ValueRange.of(0, 999_999_999), instant.range(ChronoField.NANO_OF_SECOND));
        assertEquals(ValueRange.of(0, 999_999), instant.range(ChronoField.MICRO_OF_SECOND));
        assertEquals(ValueRange.of(0, 999), instant.range(ChronoField.MILLI_OF_SECOND));
        assertEquals(ValueRange.of(Long.MIN_VALUE, Long.MAX_VALUE), instant.range(ChronoField.INSTANT_SECONDS));
    }
}
