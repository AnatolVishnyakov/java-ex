package ru.avishnyakov.javaex.datetime;

import org.junit.jupiter.api.Test;

import java.time.Instant;

public class InstantTest {
    @Test
    public void test() {
        Instant now = Instant.now();
        System.out.println(now);
    }
}
