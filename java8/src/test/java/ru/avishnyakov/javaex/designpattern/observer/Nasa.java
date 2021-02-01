package ru.avishnyakov.javaex.designpattern.observer;

public class Nasa implements LandingObserver {
    @Override
    public void observeLanding(String name) {
        if (name.contains("Apollo")) {
            System.out.println("Мы сделали это!");
        }
    }
}
