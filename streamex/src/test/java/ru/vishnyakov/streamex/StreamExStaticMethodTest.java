package ru.vishnyakov.streamex;

import one.util.streamex.StreamEx;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamExStaticMethodTest {

    @Test
    @DisplayName("Сочетания")
    void cartesianPower() {
        assertEquals("[[1, 1], [1, 2], [2, 1], [2, 2]]", StreamEx.cartesianPower(2, Arrays.asList(1, 2)).toList().toString());
//        StreamEx.cartesianPower(2, Arrays.asList(1, 3), Integer.class, Integer::sum)
    }

    @Test
    void cartesianProduct() {

    }

    @Test
    void constant() {
        assertEquals(Arrays.asList(1, 1, 1, 1), StreamEx.constant(1, 4).toList());
    }

    @Test
    void empty() {
        assertEquals(Collections.emptyList(), StreamEx.empty().toList());
    }

    @Test
    void generate() {
        List<Integer> values = StreamEx.generate(() -> 1).limit(3).toList();
        assertEquals(3, values.size());
        assertEquals(Arrays.asList(1, 1, 1), values);
    }

    @Test
    void iterateUnaryOperator() {
        UnaryOperator<Integer> increment = i -> i + 1;

        List<Integer> values = StreamEx.iterate(1, increment).limit(10).toList();

        assertEquals(10, values.size());
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), values);
    }

    @Test
    void iteratePredicateAndUnaryOperator() {
        Predicate<Integer> filter = i -> i <= 5;
        UnaryOperator<Integer> increment = i -> i + 1;

        List<Integer> values = StreamEx.iterate(1, filter, increment).toList();

        assertEquals(5, values.size());
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), values);
    }

    @Test
    void of() {
        System.out.println(StreamEx.of(123).toList());
        System.out.println(StreamEx.of(1, 2, 3).toList());
        System.out.println(StreamEx.of(StreamEx.of(1, 2, 3, 4, 5)).toList());
        System.out.println(StreamEx.of(Arrays.asList(1, 2, 3, 4, 5, 6).iterator()).toList());
        System.out.println(StreamEx.of(Optional.of(Arrays.asList(1, 2, 5))).toList());
        System.out.println(StreamEx.of(new StringTokenizer("a b c d e f", " ")).toList());
        System.out.println(StreamEx.of(new Integer[]{1, 2, 5, 6, 9}, 2, 5).toList());
    }

    @Test
    void ofCombinations() {

    }

    @Test
    void ofKeys() {

    }

    @Test
    void ofLines() {

    }

    @Test
    void ofNullable() {

    }

    @Test
    void ofPairs() {

    }

    @Test
    void ofPermutations() {

    }

    @Test
    void ofReversed() {

    }

    @Test
    void ofSubLists() {

    }

    @Test
    void ofTree() {

    }

    @Test
    void ofValues() {

    }

    @Test
    void produce() {

    }

    @Test
    void split() {

    }

    @Test
    void zip() {

    }

    @Test
    void f() {
        Arrays.stream(StreamEx.class.getMethods())
                .filter(method -> (method.getModifiers() & Modifier.STATIC) == 0)
                .map(Method::getName)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toCollection(() -> new TreeSet<String>(Comparator.naturalOrder())))
                .forEach(System.out::println);
    }
}
