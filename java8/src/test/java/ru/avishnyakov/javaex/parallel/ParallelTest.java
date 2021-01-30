package ru.avishnyakov.javaex.parallel;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Random;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

public class ParallelTest {
    @Test
    public void testParallelDiceRolls() {
        final Map<Integer, Double> result = parallelDiceRolls();
        System.out.println(result);
    }

    public Map<Integer, Double> parallelDiceRolls() {
        final int N = 100;
        double fraction = 1.0 / N;
        return IntStream.range(0, N)
                .parallel()
                .mapToObj(twoDiceThrows())
                .collect(groupingBy(side -> side, summingDouble(n -> fraction)));
    }

    private IntFunction<Integer> twoDiceThrows() {
        return value -> new Random().nextInt(6) + new Random().nextInt(6);
    }
}
