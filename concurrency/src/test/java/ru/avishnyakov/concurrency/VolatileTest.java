package ru.avishnyakov.concurrency;

import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VolatileTest {
    @RepeatedTest(50)
    void testCounter() throws InterruptedException {
        Counter counter = new Counter();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(counter::increment);

        List<Future<Long>> results = new ArrayList<>();
        final int maxN = 500;
        for (int i = 0; i < maxN; i++) {
            results.add(executor.submit(counter::getId));
        }
        executor.shutdown();
        if (!executor.awaitTermination(10_000, TimeUnit.SECONDS)) {
            System.out.println("<Executor not termination!>");
        }

        results.forEach(future -> {
            try {
                assertEquals(1, future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}

class Counter {
    private volatile long id = 0;
//    private AtomicInteger id = new AtomicInteger();

    public long getId() {
//        return id.get();
        return id;
    }

    public long increment() {
//        return id.incrementAndGet();
        return ++id;
    }
}