package ru.avishnyakov.javaex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.avishnyakov.javaex.model.Artist;
import ru.avishnyakov.javaex.model.Artists;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArtistsTest {
    private Artists artists;
    private List<Artist> listArtists;

    @BeforeEach
    public void init() {
        listArtists = Arrays.asList(
                new Artist("test-1", "UK"),
                new Artist("test-2", "UK"),
                new Artist("test-3", "UK"),
                new Artist("test-4", "UK"),
                new Artist("test-5", "UK")
        );

        this.artists = new Artists(listArtists);
    }

    @Test
    public void testGetArtist() {
        int index = 3;
        assertTrue(artists.getArtist(index).isPresent());
        assertEquals(listArtists.get(index), artists.getArtist(index).get());
    }

    @Test
    public void testGetArtistName() {
        int index = 1;
        assertEquals(listArtists.get(index).getName(), artists.getArtistName(index));
    }

    @Test
    public void testIllegalArgument() {
        int index = -1;
        assertThrows(IllegalArgumentException.class, () -> artists.getArtist(index));
    }
}
