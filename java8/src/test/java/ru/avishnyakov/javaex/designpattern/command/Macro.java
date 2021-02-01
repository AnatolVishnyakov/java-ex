package ru.avishnyakov.javaex.designpattern.command;

import java.util.ArrayList;
import java.util.List;

public class Macro {
    private final List<Action> actions = new ArrayList<>();

    public void record(Action action) {
        actions.add(action);
    }

    public void run() {
        actions.forEach(Action::perform);
    }

    public static void main(String[] args) {
        Editor editor = new Editor() {
            @Override
            public void save() {
                System.out.println("save");
            }

            @Override
            public void open() {
                System.out.println("open");
            }

            @Override
            public void close() {
                System.out.println("close");
            }
        };

        // 1
        Macro macro = new Macro();
        macro.record(new Open(editor));
        macro.record(new Save(editor));
        macro.record(new Close(editor));
        macro.run();

        // 2
        macro = new Macro();
        macro.record(editor::open);
        macro.record(editor::save);
        macro.record(editor::close);
        macro.run();
    }
}
