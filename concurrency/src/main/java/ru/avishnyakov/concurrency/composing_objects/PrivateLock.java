package ru.avishnyakov.concurrency.composing_objects;

import ru.avishnyakov.concurrency.GuardedBy;

public class PrivateLock {
    private final Object myLock = new Object();
    @GuardedBy("myLock")
    Widget widget;

    void someMethod() {
        synchronized (myLock) {
            // изменение состояния виджета
        }
    }
}

class Widget {}
