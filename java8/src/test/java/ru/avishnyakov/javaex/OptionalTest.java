package ru.avishnyakov.javaex;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class OptionalTest {
    @Test
    public void testOf() {
        final Optional<String> a = Optional.of("a");

        assertTrue(a.isPresent());
        assertEquals("a", a.get());
    }

    @Test
    public void testOfEmpty() {
        final Optional<Object> empty = Optional.empty();
        assertFalse(empty.isPresent());
    }

    @Test
    public void testOfNullable() {
        final Optional<Object> object = Optional.ofNullable(null);
        assertFalse(object.isPresent());
    }

    @Test
    public void testOrElse() {
        final Optional<String> a = Optional.empty();

        assertEquals("b", a.orElse("b"));
        assertEquals("c", a.orElseGet(() -> "c"));
    }
}
