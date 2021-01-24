package ru.avishnyakov.javaex.functional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

interface Jukebox {
    default String rock() {
        return "... all over the world!";
    }
}

interface Carriage {
    default String rock() {
        return "... from side to side";
    }
}

class MusicalCarriage implements Carriage, Jukebox {
    @Override
    public String rock() {
        // необходимо явно указать
        // на метод унаследованного
        // интерфейса
        return Carriage.super.rock();
    }
}

public class MultipleInheritanceTest {
    @Test
    public void testEqualDefaultMethod() {
        final MusicalCarriage carriage = new MusicalCarriage();
        assertEquals("... from side to side", carriage.rock());
    }
}
