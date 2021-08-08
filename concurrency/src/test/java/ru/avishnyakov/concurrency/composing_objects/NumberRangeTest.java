package ru.avishnyakov.concurrency.composing_objects;

import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberRangeTest {
    ExecutorService executors = Executors.newWorkStealingPool(2);

    @RepeatedTest(10_000)
    void isInRange() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        NumberRange numberRange = new NumberRange();

        executors.execute(() -> {
            System.out.println(Thread.currentThread().getName());
            numberRange.setUpper(new Random().nextInt(10));
            latch.countDown();
        });
        executors.execute(() -> {
            System.out.println(Thread.currentThread().getName());
            numberRange.setLower(new Random().nextInt(10));
            latch.countDown();
        });

        latch.await();

        if (numberRange.getLower() != 0 &&
                numberRange.getUpper() != 0 &&
                numberRange.getLower() > numberRange.getUpper()
        ) {
            System.out.println(numberRange);
        }
        assertTrue(numberRange.getLower() <= numberRange.getUpper());
    }
}