package ru.avishnyakov.javaex.functional;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionalInterfaceTest {
    @Test
    public void testPredicate() {
        Predicate<Integer> isZero = value -> value == 0;
        assertTrue(isZero.test(0));
        assertFalse(isZero.test(1));
    }

    @Test
    public void testConsumer() {
        final List<String> integerList = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .map(Object::toString)
                .collect(Collectors.toList());

        Consumer<List<String>> consumer = values -> System.out.println(String.join(" ", integerList));
        consumer.accept(integerList);
    }

    @Test
    public void testFunction() {
        Function<Integer, String> converter = String::valueOf;
        assertEquals("1234", converter.apply(1234));
    }

    @Test
    public void testSupplier() {
        Supplier<Button> supplier = Button::new;
        final Button button = supplier.get();

        assertNotNull(button);
        assertEquals(Button.class, button.getClass());
    }

    @Test
    public void testUnaryOperator() {
        UnaryOperator<Integer> abs = value -> {
            if (value < 0) {
                return value * -1;
            }
            return value;
        };

        assertEquals(7, abs.apply(7));
        assertEquals(7, abs.apply(-7));
    }

    @Test
    public void testBinaryOperator() {
        BinaryOperator<Integer> multiply = (a, b) -> a * b;
        assertEquals(4, multiply.apply(2, 2));
    }
}
