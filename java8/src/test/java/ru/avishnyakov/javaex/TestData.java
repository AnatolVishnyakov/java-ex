package ru.avishnyakov.javaex;

import ru.avishnyakov.javaex.model.Album;
import ru.avishnyakov.javaex.model.Artist;
import ru.avishnyakov.javaex.model.Track;

import java.time.Duration;
import java.util.List;

import static java.util.Arrays.asList;

public class TestData {
    public static final List<Track> tracks = asList(
            new Track("Day in Day Out", Duration.parse("PT3M10S").getSeconds()),
            new Track("Learn to Live", Duration.parse("PT4M01S").getSeconds()),
            new Track("Delete, Rewind", Duration.parse("PT3M08S").getSeconds()),
            new Track("BTN", Duration.parse("PT3M57S").getSeconds()),
            new Track("An Open Letter to Myself", Duration.parse("PT3M16S").getSeconds()),
            new Track("The Blues", Duration.parse("PT3M16S").getSeconds()),
            new Track("Red Eyes", Duration.parse("PT4M17S").getSeconds()),
            new Track("Stay Young Forever", Duration.parse("PT3M02S").getSeconds()),
            new Track("Heartburn", Duration.parse("PT3M37S").getSeconds())
    );

    public static final List<Artist> musicians = asList(
            new Artist("The architects", "UK")
    );

    public static final Album album = new Album("The Here and Now", tracks, musicians);
}
