package ru.avishnyakov.javaex.designpattern.observer;

public class Aliens implements LandingObserver {
    @Override
    public void observeLanding(String name) {
        if (name.contains("Apollo")) {
            System.out.println("Они отвлеклись, вторгаемся на Землю!");
        }
    }
}
