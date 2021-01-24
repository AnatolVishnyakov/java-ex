package ru.avishnyakov.javaex.collector;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class StringCollector implements Collector<String, StringCombiner, String> {
    private final String delimiter;
    private final String prefix;
    private final String suffix;

    public StringCollector(String delimiter, String prefix, String suffix) {
        this.delimiter = delimiter;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    // Поставщик/фабрика создает контейнеры
    @Override
    public Supplier<StringCombiner> supplier() {
        return new Supplier<StringCombiner>() {
            @Override
            public StringCombiner get() {
                return new StringCombiner(delimiter, prefix, suffix);
            }
        };
    }

    // Добавляет текущий элемент в коллектор
    @Override
    public BiConsumer<StringCombiner, String> accumulator() {
        return new BiConsumer<StringCombiner, String>() {
            @Override
            public void accept(StringCombiner stringCombiner, String element) {
                stringCombiner.add(element);
            }
        };
    }

    // Объединяет результат предыдущей операции
    // с результатом текущей операции
    @Override
    public BinaryOperator<StringCombiner> combiner() {
        return new BinaryOperator<StringCombiner>() {
            @Override
            public StringCombiner apply(StringCombiner that, StringCombiner other) {
                return that.merge(other);
            }
        };
    }

    // Результирующая операция
    @Override
    public Function<StringCombiner, String> finisher() {
        return new Function<StringCombiner, String>() {
            @Override
            public String apply(StringCombiner stringCombiner) {
                return stringCombiner.toString();
            }
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
