package ru.avishnyakov.javaex.javatimeapi;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class DateTest {
    private static final Date pastDate = new Date(1518678377185L);
    private static final Date futureDate = new Date(4718678377185L);
    private static final Date currentDate = new Date(1618678865548L);

    @Test
    public void testAfterDate() {
        assertTrue(currentDate.after(pastDate));
        assertFalse(currentDate.after(futureDate));
    }

    @Test
    public void testBeforeDate() {
        assertTrue(currentDate.before(futureDate));
        assertFalse(currentDate.before(pastDate));
    }

    @Test
    public void testEqualsDate() {
        assertEquals(currentDate, new Date(currentDate.getTime()));
    }

    @Test
    public void testCompareToDate() {
        assertEquals(1, currentDate.compareTo(pastDate));
        assertEquals(-1, currentDate.compareTo(futureDate));
        assertEquals(0, currentDate.compareTo(new Date(currentDate.getTime())));
    }

    @Test
    public void testInstantDate() {
        assertNotNull(currentDate.toInstant());
        assertEquals(Instant.class, currentDate.toInstant().getClass());
    }

    @Test
    public void testFromInstant() {
        assertEquals(currentDate, Date.from(currentDate.toInstant()));
    }

    @Test
    public void testMutable() {
        Date mutableDate = new Date(currentDate.getTime());
        mutableDate.setTime(pastDate.getTime());
        assertEquals(pastDate, mutableDate);
        assertNotEquals(currentDate, mutableDate);
    }
}
