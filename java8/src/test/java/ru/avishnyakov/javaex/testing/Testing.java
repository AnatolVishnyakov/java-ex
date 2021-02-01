package ru.avishnyakov.javaex.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.avishnyakov.javaex.model.Album;
import ru.avishnyakov.javaex.model.Track;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.avishnyakov.javaex.TestData.*;

public class Testing {
    @Test
    public void testOverrideMethod() {
        ThreadLocal.withInitial(() -> ThreadLocalRandom.current().nextInt());
    }

    @Test
    public void testImperativeStream() {
        assertEquals(countRunningTimeImperative(), countRunningTimeStream());
        assertEquals(countMusiciansImperative(), countMusiciansStream());
        assertEquals(countTracksImperative(), countTracksStream());
    }

    public long countRunningTimeImperative() {
        long count = 0;
        for (Album album : albums) {
            for (Track track : album.getTrackList()) {
                count += track.getLength();
            }
        }
        return count;
    }

    public long countMusiciansImperative() {
        long count = 0;
        for (Album album : albums) {
            count += album.getMusicianList().size();
        }
        return count;
    }

    public long countTracksImperative() {
        long count = 0;
        for (Album album : albums) {
            count += album.getTrackList().size();
        }
        return count;
    }

    private long countFeature(ToLongFunction<Album> function) {
        return albums.stream()
                .mapToLong(function)
                .sum();
    }

    private long countRunningTimeStream() {
        return countFeature(album -> album.getTracks()
                .mapToLong(track -> track.getLength())
                .sum()
        );
    }

    private long countMusiciansStream() {
        return countFeature(album -> album.getMusicians().count());
    }

    private long countTracksStream() {
        return countFeature(album -> album.getTracks().count());
    }

    @Test
    public void testAllToUpperCase() {
        final List<String> strings = asList("test1", "test2", "test3");

        assertEquals(asList("TEST1", "TEST2", "TEST3"), allToUpperCase(strings));
    }

    private List<String> allToUpperCase(List<String> words) {
        return words.stream()
            .map(string -> string.toUpperCase())
            .collect(Collectors.toList());
    }

    @Test
    public void testElementFirstToUpperCaseLambdas() {
        final List<String> strings = asList("test1", "test2", "test3");

        assertEquals(asList("Test1", "Test2", "Test3"), elementFirstToUpperCaseLambdas(strings));
    }

    private List<String> elementFirstToUpperCaseLambdas(List<String> words) {
        return words.stream()
                .map(value -> {
                    final char firstChar = Character.toUpperCase(value.charAt(0));
                    return firstChar + value.substring(1);
                })
                .collect(Collectors.toList());
    }

    @Test
    public void testPeek() {
        final Set<String> nationalities = album.getMusicians()
                .filter(artist -> artist.getName().startsWith("The"))
                .map(artist -> artist.getNationality())
                .peek(nation -> System.out.println("Found nationality: " + nation))
                .collect(Collectors.<String>toSet());
        assertEquals(new HashSet<>(asList("UK")), nationalities);
    }
}