package ru.avishnyakov.javaex;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
}
