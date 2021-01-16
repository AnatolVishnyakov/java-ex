package ru.avishnyakov.javaex;

import org.junit.jupiter.api.Test;
import ru.avishnyakov.javaex.model.Album;
import ru.avishnyakov.javaex.model.Artist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.avishnyakov.javaex.TestData.albums;

public class NewMethodCollectionTest {
    private final Map<String, Artist> artistCache = new HashMap<>();

    @Test
    public void testArtistLegacy() {
        final Artist artist = getArtistLegacy("Metallica");

        assertEquals("Metallica", artist.getName());
        assertEquals(1, artistCache.size());
    }

    private Artist getArtistLegacy(String name) {
        Artist artist = artistCache.get(name);
        if (artist == null) {
            artist = readArtistFromDB(name);
            artistCache.put(name, artist);
        }
        return artist;
    }

    private Artist readArtistFromDB(String name) {
        return new Artist(name, "american");
    }

    @Test
    public void testArtistNew() {
        final Artist artist = getArtistEnhancement("Metallica");

        assertEquals("Metallica", artist.getName());
        assertEquals(1, artistCache.size());
    }

    private Artist getArtistEnhancement(String name) {
        return artistCache.computeIfAbsent(name, this::readArtistFromDB);
    }

    @Test
    public void testMapTraversalLegacy() {
        final Map<Artist, List<Album>> albumsByArtist = albums.stream()
                .collect(groupingBy(Album::getMainMusician));

        Map<Artist, Integer> countOfAlbums = new HashMap<>();
        for (Map.Entry<Artist, List<Album>> entry : albumsByArtist.entrySet()) {
            final Artist artist = entry.getKey();
            final List<Album> albums = entry.getValue();
            countOfAlbums.put(artist, albums.size());
        }
        System.out.println(countOfAlbums);
    }

    @Test
    public void testMapTraversalEnhancement() {
        final Map<Artist, List<Album>> albumsByArtist = albums.stream()
                .collect(groupingBy(Album::getMainMusician));

        Map<Artist, Integer> countOfAlbums = new HashMap<>();
        albumsByArtist.forEach((artist, albums) -> {
            countOfAlbums.put(artist, albums.size());
        });
        System.out.println(countOfAlbums);
    }
}
