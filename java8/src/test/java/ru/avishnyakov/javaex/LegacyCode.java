package ru.avishnyakov.javaex;

import ru.avishnyakov.javaex.model.Album;
import ru.avishnyakov.javaex.model.Track;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LegacyCode {
    // legacy
    public Set<String> findLongTracksOld(List<Album> albums) {
        final Set<String> trackNames = new HashSet<>();
        for (Album album : albums) {
            for (Track track : album.getTrackList()) {
                if (track.getLength() > 60) {
                    final String name = track.getName();
                    trackNames.add(name);
                }
            }
        }

        return trackNames;
    }

    // functional style
    public Set<String> findLongTracksNew(List<Album> albums) {
        final Set<String> trackNames = new HashSet<>();

        // 1
/*
        albums.stream()
                .forEach(album -> {
                    album.getTracks().forEach(track -> {
                        if (track.getLength() > 60) {
                            final String name = track.getName();
                            trackNames.add(name);
                        }
                    });
                });

        return trackNames;
*/
        // 2
/*
        albums.stream()
                .forEach(album -> {
                    album.getTracks()
                            .filter(track -> track.getLength() > 60)
                            .map(track -> track.getName())
                            .forEach(name -> trackNames.add(name));
                });

        return trackNames;
*/
        // 3
        return albums.stream()
                .flatMap(album -> album.getTracks())
                .filter(track -> track.getLength() > 60)
                .map(track -> track.getName())
                .collect(Collectors.toSet());
    }
}
