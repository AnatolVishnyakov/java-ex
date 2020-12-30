package ru.avishnyakov.javaex.model;

import java.util.List;
import java.util.Optional;

public class Artists {
    private List<Artist> artists;

    public Artists(List<Artist> artists) {
        this.artists = artists;
    }

    public Optional<Artist> getArtist(int index) {
        if (index < 0 || index >= artists.size()) {
            indexException(index);
        }

//        return Optional.of(artists.get(index));
        return Optional.of(artists.get(index));
    }

    private void indexException(int index) {
        throw new IllegalArgumentException(index + " doesn't correspond to an Artist");
    }

    public String getArtistName(int index) {
        try {
            final Optional<Artist> artist = getArtist(index);
            return artist.map(Artist::getName)
                    .orElse(null);
        } catch (IllegalArgumentException e) {
            return "unknown";
        }
    }
}
