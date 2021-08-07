package ru.avishnyakov.concurrency.composing_objects;

import java.util.concurrent.atomic.AtomicInteger;

public class NumberRange {
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) {
        if (i > upper.get()) {
            throw new IllegalArgumentException("не могу установить lower: " + i + " > upper");
        }
        lower.set(i);
    }

    public void setUpper(int i) {
        if (i < lower.get()) {
            throw new IllegalArgumentException("не могу установить upper: " + i + " < lower");
        }
        upper.set(i);
    }

    public boolean isInRange(int i) {
        return (i >= lower.get() && i <= upper.get());
    }

    public int getLower() {
        return lower.get();
    }

    public int getUpper() {
        return upper.get();
    }

    @Override
    public String toString() {
        return "NumberRange{" +
                "lower=" + lower.get() +
                ", upper=" + upper.get() +
                '}';
    }
}
