package ru.avishnyakov.javaex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.avishnyakov.javaex.model.Album;
import ru.avishnyakov.javaex.model.Artist;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.avishnyakov.javaex.TestData.albums;

public class CollectorTest {
    private List<Artist> artists;

    @BeforeEach
    public void init() {
        final List<Artist> members1 = asList(
                new Artist("user-1 group-1", "UK"),
                new Artist("user-2 group-1", "UK"),
                new Artist("user-3 group-1", "UK")
        );
        final List<Artist> members2 = asList(new Artist("user-1 group-2", "UK"));
        final List<Artist> members3 = asList(
                new Artist("user-1 group-3", "UK"),
                new Artist("user-2 group-3", "UK")
        );
        final List<Artist> members4 = asList(
                new Artist("user-1 group-4", "UK"),
                new Artist("user-2 group-4", "UK"),
                new Artist("user-3 group-4", "UK"),
                new Artist("user-4 group-4", "UK"),
                new Artist("user-5 group-4", "UK")
        );
        final List<Artist> members5 = asList(
                new Artist("user-1 group-5", "UK"),
                new Artist("user-2 group-5", "UK")
        );

        this.artists = asList(
                new Artist("group-1", members1, "UK"),
                new Artist("group-2", members2, "UK"),
                new Artist("group-3", members3, "UK"),
                new Artist("group-4", members4, "UK"),
                new Artist("group-5", members5, "UK")
        );
    }

    @Test
    public void testBiggestGroup() {
        final Optional<Artist> artist = biggestGroup(artists.stream());
        assertTrue(artist.isPresent());
        assertEquals(artists.get(3), artist.get());
    }

    private Optional<Artist> biggestGroup(Stream<Artist> artists) {
        Function<Artist, Long> getCount = artist -> artist.getMembers().count();
        return artists.collect(maxBy(Comparator.comparing(getCount)));
    }

    @Test
    public void testSmallestGroup() {
        final Optional<Artist> artist = smallestGroup(artists.stream());
        assertTrue(artist.isPresent());
        assertEquals(artists.get(1), artist.get());
    }

    private Optional<Artist> smallestGroup(Stream<Artist> artists) {
        Function<Artist, Long> getCount = artist -> artist.getMembers().count();
        return artists.collect(minBy(Comparator.comparing(getCount)));
    }

    @Test
    public void testAverage() {
        final Double collect = artists.stream()
                .collect(averagingLong(artist -> artist.getMembers().count()));
        System.out.println(collect);
    }

    private double averageNumberOfTracks(List<Album> albums) {
        return albums.stream()
                .collect(averagingInt(album -> album.getTrackList().size()));
    }

    @Test
    public void testPartition() {
        final List<Artist> members = asList(
                new Artist("user-1 group-1", "UK")
        );
        final List<Artist> artists = asList(
                new Artist("solo-1", Collections.emptyList(), "USA"),
                new Artist("solo-2", Collections.emptyList(), "Canada"),
                new Artist("group-1", members, "USA")
                );
        final Map<Boolean, List<Artist>> result = bandsAndSolo(artists.stream());
        assertEquals(1, result.get(false).size());
        assertEquals(2, result.get(true).size());
    }

    private Map<Boolean, List<Artist>> bandsAndSolo(Stream<Artist> artists) {
        return artists.collect(partitioningBy(artist -> artist.isSolo()));
    }

    @Test
    public void testGrouping() {
        final Map<Artist, List<Album>> albumsGrouping = albumsByArtist(albums.stream());
        System.out.println(albumsGrouping);
    }

    private Map<Artist, List<Album>> albumsByArtist(Stream<Album> albums) {
        return albums.collect(groupingBy(album -> album.getMainMusician()));
    }

    @Test
    public void testJoining() {
        final String result = artists.stream()
                .map(Artist::getName)
                .collect(joining(", ", "[", "]"));
        assertEquals("[group-1, group-2, group-3, group-4, group-5]", result);
    }

    @Test
    public void testGroupCounting() {
        final Map<Artist, Long> result = numberOfAlbums(albums.stream());
        System.out.println(result);
    }

    private Map<Artist, Long> numberOfAlbums(Stream<Album> albums) {
        return albums.collect(groupingBy(album -> album.getMainMusician(), counting()));
    }
}