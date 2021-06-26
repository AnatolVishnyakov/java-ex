package ru.vishnyakov.streamex;

import one.util.streamex.StreamEx;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;
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
        StreamEx<Integer> generator = StreamEx.generate(() -> 1).limit(3);
        assertEquals(Arrays.asList(1, 1, 1), generator.toList());
    }

    @Test
    void iterate() {

    }

    @Test
    void of() {

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
