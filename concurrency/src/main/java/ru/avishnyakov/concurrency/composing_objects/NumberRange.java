package ru.avishnyakov.concurrency.composing_objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class NumberRange {
    private static final Logger logger = LoggerFactory.getLogger(NumberRange.class);
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) {
        if (i > upper.get()) {
            logger.warn("не могу установить lower: {} > upper", i);
            return;
        }
        lower.set(i);
    }

    public void setUpper(int i) {
        if (i < lower.get()) {
            logger.warn("не могу установить upper: {} < lower", i);
            return;
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
