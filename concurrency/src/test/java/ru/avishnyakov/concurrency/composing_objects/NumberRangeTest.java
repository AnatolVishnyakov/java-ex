package ru.avishnyakov.concurrency.composing_objects;

import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class NumberRangeTest {

    @RepeatedTest(1000)
    void isInRange() throws InterruptedException {
        NumberRange numberRange = new NumberRange();

        ExecutorService executors = Executors.newFixedThreadPool(2);
        executors.execute(() -> numberRange.setLower(ThreadLocalRandom.current().nextInt(5,10)));
        executors.execute(() -> numberRange.setUpper(ThreadLocalRandom.current().nextInt(1, 5)));
        executors.shutdown();

        Thread.sleep(10);

        System.out.println(numberRange);
        assertTrue(numberRange.getLower() <= numberRange.getUpper());
    }
}