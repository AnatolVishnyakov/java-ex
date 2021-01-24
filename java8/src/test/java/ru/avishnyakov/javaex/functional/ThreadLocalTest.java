package ru.avishnyakov.javaex.functional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ThreadLocalTest {
    public static class ConnectionAccess {
        private static ThreadLocal<Connection> connectionHolder = ThreadLocal.withInitial(() -> {
            try {
                return DriverManager.getConnection("DB_URL");
            } catch (SQLException exc) {
                exc.printStackTrace();
                throw new RuntimeException(exc);
            }
        });

        public static Connection getConnection() {
            return connectionHolder.get();
        }
    }
}

class SomeBuilderDemo {
    public static class SomeBuilder {
        private int counter;

        public void build() {
            System.out.println("Thread " + Thread.currentThread().getName() + " Build some structure...");
            counter++;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public int getCount() {
            return counter;
        }
    }

    static class SomeBuilderCustom extends SomeBuilder {
        private final Map<String, Integer> counters = new HashMap<>();

        public void build() {
            System.out.println("Thread " + Thread.currentThread().getName() + " Build some structure...");

            if (!counters.containsKey(Thread.currentThread().getName())) {
                counters.put(Thread.currentThread().getName(), 0);
            }
            counters.put(Thread.currentThread().getName(), counters.get(Thread.currentThread().getName()) + 1);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public int getCount() {
            return counters.get(Thread.currentThread().getName());
        }
    }

    static class SomeBuilderThreadLocal extends SomeBuilder {
        private final ThreadLocal<Integer> counter = new ThreadLocal<Integer>() {
            // при сложной инициализации
            // необходимо синхронизировать
            @Override
            protected Integer initialValue() {
                return 0;
            }
        };

        public void build() {
            System.out.println("Thread " + Thread.currentThread().getName() + " Build some structure...");

            // Инициализация вынесена в threadLocal
//            if (counter.get() == null) {
//                counter.set(0);
//            }
            counter.set(counter.get() + 1);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public int getCount() {
            return counter.get();
        }
    }

    public static class SomeBuilderThread extends Thread {
        private final SomeBuilder builder;

        public SomeBuilderThread(SomeBuilder builder) {
            this.builder = builder;
        }

        @Override
        public void run() {
            for (int i = 0; i < Math.random() * 10; i++) {
                builder.build();
            }

            System.out.println("My name is " + getName() + " and I built " + builder.getCount() + " things");
        }
    }


    public static void main(String[] args) {
        SomeBuilder builder = new SomeBuilder();
        SomeBuilderCustom builderCustom = new SomeBuilderCustom();
        final SomeBuilderThreadLocal builderThreadLocal = new SomeBuilderThreadLocal();

        Thread thread1 = new SomeBuilderThread(builderThreadLocal);
        Thread thread2 = new SomeBuilderThread(builderThreadLocal);

        try {
            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
