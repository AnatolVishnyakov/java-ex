package ru.avishnyakov.javaex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.avishnyakov.javaex.model.Album;
import ru.avishnyakov.javaex.model.Artist;
import ru.avishnyakov.javaex.util.StringCollector;
import ru.avishnyakov.javaex.util.StringCombiner;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
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

    @Test
    public void testNameOfAlbumsDumb() {
        final Map<Artist, List<String>> artistAlbums = nameOfAlbumsDumb(albums.stream());
        System.out.println(artistAlbums);
    }

    private Map<Artist, List<String>> nameOfAlbumsDumb(Stream<Album> albums) {
        final Map<Artist, List<Album>> albumsByArtist = albums.collect(groupingBy(album -> album.getMainMusician()));

        Map<Artist, List<String>> nameOfAlbums = new HashMap<>();
        for (Map.Entry<Artist, List<Album>> entry : albumsByArtist.entrySet()) {
            nameOfAlbums.put(entry.getKey(), entry.getValue().stream().map(album -> album.getName()).collect(toList()));
        }
        return nameOfAlbums;
    }

    @Test
    public void testNameOfAlbums() {
        final Map<Artist, List<String>> albumsByArtist = nameOfAlbums(albums.stream());
        System.out.println(albumsByArtist);
    }

    private Map<Artist, List<String>> nameOfAlbums(Stream<Album> albums) {
        return albums.collect(groupingBy(Album::getMainMusician, mapping(Album::getName, toList())));
    }

    @Test
    public void testStringJoinCollector() {
        {
            System.out.println("Императивный стиль");
            final StringBuilder builder = new StringBuilder("[");
            for (Artist artist : artists) {
                if (builder.length() > 1) {
                    builder.append(", ");
                }

                final String name = artist.getName();
                builder.append(name);
            }
            builder.append("]");
            final String result = builder.toString();
            System.out.println(result);
        }
        {
            System.out.println("Частично функциональный стиль 1");
            final StringBuilder builder = new StringBuilder("[");
            artists.stream()
                    .map(Artist::getName)
                    .forEach(name -> {
                        if (builder.length() > 1) {
                            builder.append(", ");
                        }

                        builder.append(name);
                    });
            builder.append("]");
            final String result = builder.toString();
            System.out.println(result);
        }
        {
            System.out.println("Частично функциональный стиль 2");
            final StringBuilder reduced = artists.stream()
                    .map(Artist::getName)
                    .reduce(new StringBuilder(), (builder, name) -> {
                        if (builder.length() > 0) {
                            builder.append(", ");
                        }

                        builder.append(name);
                        return builder;
                    }, (left, right) -> {
                        System.out.println("Left: " + left + " Right: " + right);
                        return left.append(right);
                    });
            reduced.insert(0, "[");
            reduced.append("]");
            final String result = reduced.toString();
            System.out.println(result);
        }
        {
            System.out.println("Частично функциональный стиль 3");
            final String result = artists.stream()
                    .map(Artist::getName)
                    .reduce(new StringCombiner(", ", "[", "]"), StringCombiner::add, StringCombiner::merge)
                    .toString();
            System.out.println(result);
        }
        {
            System.out.println("Функциональный стиль");
            final String result = artists.stream()
                    .map(Artist::getName)
                    .collect(new StringCollector(", ", "[", "]"));
            System.out.println(result);
        }
        {
            System.out.println("Функциональный стиль");
            final String result = artists.stream()
                    .map(Artist::getName)
                    .collect(joining(", ", "[", "]"));
            System.out.println(result);
        }
        {
            // Не эффективный из-за создания
            // на каждый элемент контейнера
            // StringCombiner
            System.out.println("Функциональный стиль (через редукцию)");
            final String result = artists.stream()
                    .map(Artist::getName)
                    .collect(Collectors.reducing(
                            new StringCombiner(",", "[", "]"),
                            name -> new StringCombiner(", ", "[", "]").add(name),
                            StringCombiner::merge
                    ))
                    .toString();
            System.out.println(result);
        }
    }
}