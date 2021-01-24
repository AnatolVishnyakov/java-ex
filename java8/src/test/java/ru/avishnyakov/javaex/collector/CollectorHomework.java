package ru.avishnyakov.javaex.collector;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectorHomework {
    @Test
    public void methodReferenceUpperCase() {
        final Stream<String> stream = Stream.of(
                "one",
                "two two",
                "three three three",
                "four four four four"
        );

        final List<String> actualStrings = stream.map(String::toUpperCase)
                .collect(Collectors.toList());

        final List<String> expectedStrings = Arrays.asList("ONE", "TWO TWO", "THREE THREE THREE", "FOUR FOUR FOUR FOUR");
        assertEquals(expectedStrings, actualStrings);
    }

    @Test
    public void countWithReduce() {
        final Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50, 60, 70, 80, 90);
        final Integer count = stream.reduce(0, (i1, i2) -> i1 += 1);

        assertEquals(9, count);
    }

    @Test
    public void concatList() {
        final Stream<List<Integer>> stream = Stream.of(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5, 6),
                Arrays.asList(7, 8),
                Arrays.asList(9, 10)
        );

        final List<Integer> actual = stream.flatMap(Collection::stream)
                .collect(Collectors.toList());
        final List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertEquals(expected, actual);
    }

    @Test
    public void actorLongerName() {
        final Stream<String> names = Stream.of(
                "John Lennon",
                "Paul McCartney",
                "George Harrison",
                "Ringo Starr",
                "Pete Best",
                "Stuart Sutcliffe"
        );

        final String actualReduce = names.reduce("", (strPrev, strNext) -> {
            if (strPrev.length() < strNext.length()) {
                return strNext;
            }
            return strPrev;
        });
        assertEquals("Stuart Sutcliffe", actualReduce);

//        final String actualCollectReduce = names.collect(Collectors.reducing(
//                "", (strPrev, strNext) -> {
//                    if (strPrev.length() < strNext.length()) {
//                        return strNext;
//                    }
//                    return strPrev;
//                }
//        ));
//        assertEquals("Stuart Sutcliffe", actualCollectReduce);
    }

    @Test
    public void actorNameCount() {
        final Stream<String> stream = Stream.of("John", "Paul", "George", "John", "Paul", "John");
        final Map<String, Long> actual = stream.collect(groupingBy(s -> s, counting()));

        assertEquals("{George=1, John=3, Paul=2}", actual.toString());
    }

    @Test
    public void customCollectorGroupingBy() {
        // TODO implements collector
    }

    @Test
    public void enhancementMapFibonacci() {
        // TODO implements enhancement fibonacci
    }
}
