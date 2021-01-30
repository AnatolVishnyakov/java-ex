package ru.avishnyakov.javaex.parallel;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import ru.avishnyakov.javaex.model.Album;
import ru.avishnyakov.javaex.model.Artist;
import ru.avishnyakov.javaex.model.Track;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 3) // кол-во прогреваний
@Measurement(iterations = 5) // кол-во итераций алгоритма
@BenchmarkMode({Mode.SingleShotTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Threads(1)
@Fork(5)
@Timeout(time = 5, timeUnit = TimeUnit.MINUTES)
public class ParallelBenchmark {
    @Param({"10", "100", "10000"})
    private int numberOfAlbums;
    private final List<Album> albums = new ArrayList<>();

    @Setup
    public void setUp() {
        for (int i = 0; i < numberOfAlbums; i++) {
            final String name = "album-" + UUID.randomUUID().toString();
            List<Track> tracks = Arrays.asList(
                    new Track("Track-" + UUID.randomUUID().toString(), new Random().nextInt(10_000)),
                    new Track("Track-" + UUID.randomUUID().toString(), new Random().nextInt(10_000)),
                    new Track("Track-" + UUID.randomUUID().toString(), new Random().nextInt(10_000)),
                    new Track("Track-" + UUID.randomUUID().toString(), new Random().nextInt(10_000)),
                    new Track("Track-" + UUID.randomUUID().toString(), new Random().nextInt(10_000)),
                    new Track("Track-" + UUID.randomUUID().toString(), new Random().nextInt(10_000)),
                    new Track("Track-" + UUID.randomUUID().toString(), new Random().nextInt(10_000)),
                    new Track("Track-" + UUID.randomUUID().toString(), new Random().nextInt(10_000)),
                    new Track("Track-" + UUID.randomUUID().toString(), new Random().nextInt(10_000))
            );

            List<Artist> artists = Arrays.asList(
                    new Artist("Artist-" + UUID.randomUUID().toString(), "USA"),
                    new Artist("Artist-" + UUID.randomUUID().toString(), "USA"),
                    new Artist("Artist-" + UUID.randomUUID().toString(), "USA"),
                    new Artist("Artist-" + UUID.randomUUID().toString(), "USA"),
                    new Artist("Artist-" + UUID.randomUUID().toString(), "USA"),
                    new Artist("Artist-" + UUID.randomUUID().toString(), "USA")
            );
            final Album album = new Album(name, tracks, artists);
            albums.add(album);
        }
    }

    @Test
    public void testSerialArraySum() {
        final int result = serialArraySum();
        System.out.println(result);
    }

    @Benchmark
    public int serialArraySum() {
        return (int) albums.stream()
                .flatMap(Album::getTracks)
                .mapToLong(Track::getLength)
                .sum();
    }

    @Test
    public void testParallelArraySum() {
        final int result = parallelArraySum();
        System.out.println(result);
    }


    @Benchmark
    public int parallelArraySum() {
        return (int) albums.stream()
                .parallel()
                .flatMap(Album::getTracks)
                .mapToLong(Track::getLength)
                .sum();
    }
}
