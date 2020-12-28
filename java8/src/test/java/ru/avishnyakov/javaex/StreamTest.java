package ru.avishnyakov.javaex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.avishnyakov.javaex.model.Artist;
import ru.avishnyakov.javaex.model.Track;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Character.isDigit;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamTest {
    private final List<Artist> allArtists = new ArrayList<>();

    @BeforeEach
    public void init() {
        allArtists.addAll(asList(
                new Artist("Little Big", "Moscow"),
                new Artist("Rag'n Rod", "New York"),
                new Artist("Bring me the horizone", "London"),
                new Artist("Some artist", "London")
        ));
    }

    @Test
    public void countOfArtist() {
        int count = 0;

        for (Artist artist : allArtists) {
            if (artist.isFrom("London")) {
                count++;
            }
        }

        assertEquals(2, count);
    }

    @Test
    public void countOfArtistIterator() {
        int count = 0;
        Iterator<Artist> iterator = allArtists.iterator();
        while (iterator.hasNext()) {
            Artist artist = iterator.next();
            if (artist.isFrom("London")) {
                count++;
            }
        }

        assertEquals(2, count);
    }

    @Test
    public void countOfArtistStream() {
        final long count = allArtists.stream()
                .filter(artist -> artist.isFrom("London"))
                .count();

        assertEquals(2, count);
    }

    @Test
    public void testFilter() {
        {
            allArtists.stream()
                    .filter(artist -> {
                        System.out.println(artist.getName());
                        return artist.isFrom("London");
                    })
                    .count();
        }
        {
            final List<String> beginningWithNumbers = new ArrayList<>();
            for (String value : asList("a", "1abc", "abc1")) {
                if (isDigit(value.charAt(0))) {
                    beginningWithNumbers.add(value);
                }
            }

            assertEquals(asList("1abc"), beginningWithNumbers);
        }
        {
            final List<String> beginningWithNumbers = Stream.of("a", "1abc", "abc1")
                    .filter(value -> isDigit(value.charAt(0)))
                    .collect(Collectors.toList());

            assertEquals(asList("1abc"), beginningWithNumbers);
        }
    }

    @Test
    public void testCollect() {
        final List<String> collected = Stream.of("a", "b", "c")
                .collect(Collectors.toList());

        assertEquals(asList("a", "b", "c"), collected);
    }

    @Test
    public void testMap() {
        final List<String> values = asList("a", "b", "c");
        {
            final ArrayList<String> collected = new ArrayList<>();
            for (String string : values) {
                String upperCaseString = string.toUpperCase();
                collected.add(upperCaseString);
            }

            assertEquals(asList("A", "B", "C"), collected);
        }
        {
            final List<String> collected = Stream.of("a", "b", "c")
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());

            assertEquals(asList("A", "B", "C"), collected);
        }
    }

    @Test
    public void testFlatMap() {
        // позволяет конкатенировать потоки
        // (на выходе новый stream)
        List<Integer> joinLists = Stream.of(asList(1, 2), asList(3, 4), asList(5, 6))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        assertEquals(asList(1, 2, 3, 4, 5, 6), joinLists);
    }

    @Test
    public void testMinAndMax() {
        final List<Track> tracks = asList(
                new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451)
        );

        final Track shortestTrack = tracks.stream()
                .min(Comparator.comparing(Track::getLength))
                .get();
        assertEquals(tracks.get(1), shortestTrack);

        final Track longestTrack = tracks.stream()
                .max(Comparator.comparing(Track::getLength))
                .get();
        assertEquals(tracks.get(0), longestTrack);

        final IntSummaryStatistics statistics = tracks.stream()
                .collect(Collectors.summarizingInt(Track::getLength));
        assertEquals(longestTrack.getLength(), statistics.getMax());
        assertEquals(shortestTrack.getLength(), statistics.getMin());
    }

    @Test
    public void testReduce() {
        /* Принцип редукции:
            Object accumulator = initialValue;
            for (Object element : collection) {
                accumulator = combine(accumulator, element);
            }
            // Редуктор имеет тип BinaryOperator
        * */
        assertEquals(10, Stream.of(1, 2, 3, 4).reduce(0, (acc, element) -> acc + element));
        assertEquals(10, Stream.of(1, 2, 3, 4).reduce(0, Integer::sum));
        assertEquals(10, Stream.of(1, 2, 3, 4).reduce(Integer::sum).get());
    }
}