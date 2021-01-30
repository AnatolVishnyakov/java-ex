package ru.avishnyakov.javaex.parallel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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

    @Test
    public void testParallelSetAllInitialize() {
        final double[] values = parallelInitialize(10);
        final double[] values2 = imperativeInitialize(10);

        Assertions.assertArrayEquals(values, values2);
    }

    public static double[] imperativeInitialize(int size) {
        final double[] values = new double[size];
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }
        return values;
    }

    public static double[] parallelInitialize(int size) {
        final double[] values = new double[size];
        Arrays.parallelSetAll(values, i -> i);
        return values;
    }

    @Test
    public void testParallelPrefix() {
        final double[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        final double[] result = simpleMovingAverage(values, 2);
        System.out.println(Arrays.toString(result));
    }

    public static double[] simpleMovingAverage(double[] values, int n) {
        final double[] sums = Arrays.copyOf(values, values.length);
        Arrays.parallelPrefix(sums, Double::sum);
        System.out.println("Sum: " + Arrays.toString(sums));

        int start = n - 1;
        return IntStream.range(start, sums.length)
                .mapToDouble(i -> {
                    double prefix = i == start ? 0 : sums[i - n];
                    return (sums[i] - prefix) / n;
                })
                .toArray();
    }
}
