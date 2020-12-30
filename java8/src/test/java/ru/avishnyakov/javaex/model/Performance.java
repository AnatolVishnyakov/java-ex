package ru.avishnyakov.javaex.model;

import java.util.stream.Stream;

public interface Performance {
    String getName();

    Stream<Artist> getMusicians();

    default Stream<Artist> getAllMusicians() {
        // TODO выдать список исполнителей,
        //  а если исполнителем является группа
        //  и всех входящих в нее исполнителей
        throw new UnsupportedOperationException();
    }

    // throwing exception
//    default boolean equals(Object o) {
//        return false;
//    }

//    default int hashCode() {
//        return Objects.hash(getMusicians());
//    }
}
