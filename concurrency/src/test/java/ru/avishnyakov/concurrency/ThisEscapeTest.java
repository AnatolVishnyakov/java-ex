package ru.avishnyakov.concurrency;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.EventListener;
import java.util.UUID;

public class ThisEscapeTest {
    @Test
    void testThisEscape() {
        ThisEscape thisEscape = new ThisEscape(new EventSource());
        System.out.println(thisEscape.getValue());
    }
}

class ThisEscape {
    private UUID value = UUID.randomUUID();

    public ThisEscape(EventSource source) {
        source.registerListener(
                new EventListener() {
                    public void onEvent(Event e) {
                        doSomething(e);
                    }

                    private void doSomething(Event e) {
                        try {
                            Thread.sleep(20_000);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        System.out.println("doSomething is worked!");
                    }
                }
        );
    }

    public UUID getValue() {
        return value;
    }
}

class EventSource {
    public void registerListener(EventListener eventListener) {

    }
}