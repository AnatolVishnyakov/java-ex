package ru.avishnyakov;

import ru.avishnyakov.concurrency.Immutable;

import java.util.HashSet;
import java.util.Set;

@Immutable
public class ThreeStooges {
    private final Set<String> stooges = new HashSet<>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooges(String name) {
        return stooges.contains(name);
    }
}
