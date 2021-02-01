package ru.avishnyakov.javaex.designpattern.observer;

import org.junit.jupiter.api.Test;
import ru.avishnyakov.javaex.designpattern.observer.Aliens;
import ru.avishnyakov.javaex.designpattern.observer.Moon;
import ru.avishnyakov.javaex.designpattern.observer.Nasa;

public class MoonTest {
    @Test
    public void testMoonStereotype() {
        final Moon moon = new Moon();
        moon.startSpying(new Nasa());
        moon.startSpying(new Aliens());

        moon.land("An asteroid");
        moon.land("Apollo 11");
    }

    @Test
    public void testMoonLambda() {
        final Moon moon = new Moon();

        moon.startSpying(name -> {
            if (name.contains("Apollo")) {
                System.out.println("Мы сделали это!");
            }
        });

        moon.startSpying(name -> {
            if (name.contains("Apollo")) {
                System.out.println("Они отвлеклись, вторгаемся на Землю!");
            }
        });

        moon.land("An asteroid");
        moon.land("Apollo 11");
    }
}
