package ru.avishnyakov.javaex.designpattern.solid;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class SingleResponsibilityPrinciple {
    @Test
    public void testCountPrimes1() {
        System.out.println(countPrimes1(10));
    }

    private long countPrimes1(int upTo) {
        long tally = 0;
        for (int i = 1; i < upTo; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                }
            }
            if (isPrime) {
                System.out.print(i + " ");
                tally++;
            }
        }
        return tally;
    }

    @Test
    public void testCountPrimes2() {
        System.out.println(countPrimes2(10));
    }

    private long countPrimes2(int upTo) {
        long tally = 0;
        for (int i = 1; i < upTo; i++) {
            if (isPrime(i)) {
                tally++;
            }
        }
        return tally;
    }

    private boolean isPrime(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testCountPrimes3() {
        System.out.println(countPrimes3(10));
    }

    private long countPrimes3(int upTo) {
        return IntStream.range(1, upTo)
                .filter(this::isPrime2)
                .count();
    }

    private boolean isPrime2(int number) {
        return IntStream.range(2, number)
                .allMatch(i -> (number % i) != 0);
    }
}
