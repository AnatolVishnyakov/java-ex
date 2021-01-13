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

    public static final List<Track> tracks2 = asList(
            new Track("Nihilist", Duration.parse("PT2M51S").getSeconds()),
            new Track("Deathwish", Duration.parse("PT3M52S").getSeconds()),
            new Track("Phantom Fear", Duration.parse("PT4M06S").getSeconds()),
            new Track("Downfall", Duration.parse("PT4M04S").getSeconds()),
            new Track("Gone with the Wind", Duration.parse("PT3M45S").getSeconds()),
            new Track("The Empty Hourglass", Duration.parse("PT4M11S").getSeconds()),
            new Track("A Match Made in Heaven", Duration.parse("PT3M48S").getSeconds()),
            new Track("Gravity", Duration.parse("PT3M18S").getSeconds()),
            new Track("All Love Is Lost", Duration.parse("PT4M20S").getSeconds()),
            new Track("From the Wilderness", Duration.parse("PT3M44S").getSeconds())
    );

    public static final List<Artist> musicians2 = asList(
            new Artist("Enter Shikari", "USA")
    );
    public static final Album album2 = new Album("All Our Gods Have Abandoned Us", tracks2, musicians2);

    public static final List<Album> albums = asList(
            album, album2
    );
}
