package ru.avishnyakov.concurrency.atomic;

import ru.avishnyakov.concurrency.NotThreadSafe;

@NotThreadSafe
public class LazyInitRace {
    private static int counter = 0;
    private int id = counter++;

    private static LazyInitRace instance = null;

    public static LazyInitRace getInstance() {
        if (instance == null) {
            instance = new LazyInitRace();
        }
        return instance;
    }

    public int getId() {
        return id;
    }
}
