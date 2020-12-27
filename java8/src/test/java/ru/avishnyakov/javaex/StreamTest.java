package ru.avishnyakov.javaex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.avishnyakov.javaex.model.Artist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        allArtists.stream()
                .filter(artist -> {
                    System.out.println(artist.getName());
                    return artist.isFrom("London");
                })
        .count();
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
}
