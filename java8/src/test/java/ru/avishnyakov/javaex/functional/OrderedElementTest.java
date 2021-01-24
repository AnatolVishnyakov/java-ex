package ru.avishnyakov.javaex.functional;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderedElementTest {
    @Test
    public void testOrderArrivalListItems() {
        final List<Integer> numbers = asList(1, 2, 3, 4);
        final List<Integer> sameOrder = numbers.stream()
                .collect(Collectors.toList());
        assertEquals(numbers, sameOrder);
    }

    @Test
    public void testOrderArrivalSetItemsFailed() {
        final HashSet<Integer> numbers = new HashSet<>(asList(4, 3, 2, 1));
        final List<Integer> sameOrder = numbers.stream()
                .collect(Collectors.toList());
        assertThrows(AssertionFailedError.class, () -> assertEquals(asList(4, 3, 2, 1), sameOrder));
    }

    @Test
    public void testOrderArrivalSetItems() {
        final HashSet<Integer> numbers = new HashSet<>(asList(4, 3, 2, 1));
        final List<Integer> sameOrder = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        assertEquals(asList(1, 2, 3, 4), sameOrder);
    }

    @Test
    public void testOrderListAfterMap() {
        final List<Integer> numbers = asList(1, 2, 3, 4);
        final List<Integer> sameOrder = numbers.stream()
                .map(x -> x + 1)
                .collect(Collectors.toList());
        assertEquals(asList(2, 3, 4, 5), sameOrder);
    }

    @Test
    public void testOrderSetAfterMap() {
        final List<Integer> numbers = asList(1, 2, 3, 4);
        final HashSet<Integer> unordered = new HashSet<>(numbers);
        final List<Integer> stillUnordered = unordered.stream()
                .map(x -> x + 1)
                .collect(Collectors.toList());
        assertThat(stillUnordered, hasItem(2));
        assertThat(stillUnordered, hasItem(3));
        assertThat(stillUnordered, hasItem(4));
        assertThat(stillUnordered, hasItem(5));
    }
}
