package ru.avishnyakov.concurrency.atomic;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LazyInitRaceTest {

    @RepeatedTest(10)
    public void getInstance() throws Exception {
        ExecutorService executors = Executors.newFixedThreadPool(2);
        List<Future<LazyInitRace>> futures = executors.invokeAll(Arrays.asList(
                LazyInitRace::getInstance, LazyInitRace::getInstance));
        List<Integer> result = futures.stream()
                .map(future -> {
                    try {
                        return future.get().getId();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return 0;
                }).collect(Collectors.toList());

        result.forEach(id -> {
            assertEquals(0, id);
        });
    }
}