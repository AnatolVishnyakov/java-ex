package ru.avishnyakov.javaex.util;

public class StringCombiner {
    private final StringBuilder builder = new StringBuilder();
    private final String delimiter;
    private final String prefix;
    private final String suffix;

    public StringCombiner(String delimiter, String prefix, String suffix) {
        this.delimiter = delimiter;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public StringCombiner add(String element) {
        if (areAtStart()) {
            builder.append(prefix);
        } else {
            builder.append(delimiter);
        }
        builder.append(element);
        return this;
    }

    public StringCombiner merge(StringCombiner other) {
        builder.append(other.builder);
        return this;
    }

    private boolean areAtStart() {
        return builder.length() == 0;
    }

    @Override
    public String toString() {
        builder.append(suffix);
        return builder.toString();
    }
}
