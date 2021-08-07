package ru.avishnyakov.concurrency.composing_objects;

import ru.avishnyakov.concurrency.GuardedBy;
import ru.avishnyakov.concurrency.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

@ThreadSafe
public class PersonSet {
    @GuardedBy("this")
    private final Set<Person> mySet = new HashSet<>();

    public synchronized void addPerson(Person p) {
        mySet.add(p);
    }

    public synchronized boolean containsPerson(Person p) {
        return mySet.contains(p);
    }
}

class Person {
}
