package ru.avishnyakov.javaex.functional;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class TypeInferenceTest {
    private void useHashMap(Map<String, String> map) {
        // code
    }

    @Test
    public void testDiamond() {
        Map<String, String> oldWordCounts = new HashMap<String, String>();
        Map<Object, Object> diamondWordCounts = new HashMap<>();

        // в Java 7 не откомпилируется
        useHashMap(new HashMap<>());

        BinaryOperator add = (o, o2) -> o;

        final Button button = new Button();
        button.addActionListener(event -> System.out.println(event.getActionCommand()));

        // два метода check с одинаковой сигнатурой
//        check(value -> value > 0);
    }

    interface IntPred {
        boolean test(Integer value);
    }

    boolean check(Predicate<Integer> predicate) {
        return true;
    }

    boolean check(IntPred predicate) {
        return true;
    }
}
