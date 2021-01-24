package ru.avishnyakov.javaex.functional;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MethodReference {
    class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String newName() {
            return UUID.randomUUID().toString();
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    private final List<Person> persons = Arrays.asList(
            new Person("test")
    );

    @Test
    public void testMethodReference() {
        System.out.println(persons);
        final List<String> users = persons.stream()
                .map(Person::newName)
                .collect(Collectors.toList());
        System.out.println(users);
    }
}
