package ru.avishnyakov.javaex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.avishnyakov.javaex.model.Artist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamTest {
    private final List<Artist> allArtists = new ArrayList<>();

    @BeforeEach
    public void init() {
        allArtists.addAll(Arrays.asList(
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
}
