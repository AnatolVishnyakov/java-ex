package ru.avishnyakov.javaex.functional;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LambdaExpressionTest {
    @Test
    public void firstLambdaExpression() {
        final Button button = new Button("click");
        // 1
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Click");
            }
        });

        // 2
        button.addActionListener(event -> System.out.println("Click"));

        // 3
        ActionListener actionListener = event -> System.out.println("Click");
        button.addActionListener(actionListener);
    }

    @Test
    public void lambdaExpressionVariants() {
        Runnable noArguments = () -> System.out.println("Hello world!");

        ActionListener oneArgument = event -> System.out.println("Button clicked");

        Runnable multiStatement = () -> {
            System.out.print("Hello");
            System.out.println(" world!");
        };

        BinaryOperator<Long> sub = (x, y) -> x - y;
        assertEquals(5L, sub.apply(8L, 3L));

        BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;
        assertEquals(7L, addExplicit.apply(4L, 3L));
    }

    @Test
    public void lambdaEffectivelyFinalVariables() {
        final String name = getUserName();
        final Button button = new Button();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hi " + name);
            }
        });

        button.addActionListener(event -> System.out.println("Hi " + name));
    }

    private String getUserName() {
        return "John Doe";
    }
}
