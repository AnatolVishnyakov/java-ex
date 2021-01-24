package ru.avishnyakov.javaex.functional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

interface Parent {
    void message(String body);

    String getLastMessage();

    default void welcome() {
        message("Parent: Hi!");
    }
}

class ParentImpl implements Parent {
    private String body;

    @Override
    public void message(String body) {
        this.body = body;
    }

    @Override
    public String getLastMessage() {
        return body;
    }
}

interface Child extends Parent {
    @Override
    default void welcome() {
        message("Child: Hello!");
    }

}

class ChildImpl implements Child {
    private String body;

    @Override
    public void message(String body) {
        this.body = body;
    }

    @Override
    public String getLastMessage() {
        return body;
    }
}

class OverridingParent extends ParentImpl {
    // default метод является
    // виртуальным методом, а
    // не статическим
    @Override
    public void welcome() {
        message("Class Parent: Hi!");
    }
}

class OverridingChild extends OverridingParent implements Child {
}

public class DefaultMethodTest {
    @Test
    public void testParentDefaultUsed() {
        final Parent parent = new ParentImpl();
        parent.welcome();

        assertEquals("Parent: Hi!", parent.getLastMessage());
    }

    @Test
    public void testChildDefaultUsed() {
        final Child child = new ChildImpl();
        child.welcome();

        assertEquals("Child: Hello!", child.getLastMessage());
    }

    @Test
    public void testOverrideDefaultMethod() {
        final Parent parent = new OverridingParent();
        parent.welcome();

        assertEquals("Class Parent: Hi!", parent.getLastMessage());
    }

    @Test
    public void testOverrideClassOrInterfaceMethod() {
        final Child child = new OverridingChild();
        child.welcome();

        assertEquals("Class Parent: Hi!", child.getLastMessage());
    }
}