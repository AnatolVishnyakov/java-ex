package ru.avishnyakov.javaex.testing;

import org.junit.jupiter.api.Test;
import ru.avishnyakov.javaex.model.Album;
import ru.avishnyakov.javaex.model.Track;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.ToLongFunction;

import static ru.avishnyakov.javaex.TestData.*;

public class Testing {
    @Test
    public void testOverrideMethod() {
        ThreadLocal.withInitial(() -> ThreadLocalRandom.current().nextInt());
    }

    @Test
    public void testImperative() {
        System.out.println("Running time: " + countRunningTimeImperative());
        System.out.println("Musicians: " + countMusiciansImperative());
        System.out.println("Tracks: " + countTracksImperative());
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

    @Test
    public void testStream() {
        System.out.println(countRunningTimeStream());
        System.out.println(countMusiciansStream());
        System.out.println(countTracksStream());
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
}