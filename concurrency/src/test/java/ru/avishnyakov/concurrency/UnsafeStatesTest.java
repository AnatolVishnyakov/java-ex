package ru.avishnyakov.concurrency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnsafeStatesTest {
    @Test
    void testStates() {
        UnsafeStates unsafeStates = new UnsafeStates();
        unsafeStates.getStates()[0] = "BK";
        unsafeStates.getStates()[0] = "BL";
        unsafeStates.getStates()[0] = "BM";

        assertEquals("AK", unsafeStates.getStates()[0]);
        assertEquals("AL", unsafeStates.getStates()[1]);
        assertEquals("AM", unsafeStates.getStates()[2]);
    }
}

class UnsafeStates {
    private String[] states = new String[] {
            "AK", "AL", "AM"
    };

    public String[] getStates() {
        return states;
    }
}
