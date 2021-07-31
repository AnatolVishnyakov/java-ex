package ru.avishnyakov;

import org.junit.jupiter.api.RepeatedTest;
import ru.avishnyakov.concurrency.NotThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HolderTest {
    @RepeatedTest(100)
    void test() {
        ExecutorService executors = Executors.newFixedThreadPool(20);
        HolderWrapper holderWrapper = new HolderWrapper();
        for (int i = 0; i < 10; i++) {
            executors.execute(() -> {
                holderWrapper.initialize();
            });
            executors.execute(() -> {
                holderWrapper.holder.assertSanity();
            });
        }
    }
}

@NotThreadSafe
class HolderWrapper {
    public Holder holder;

    public void initialize() {
        holder = new Holder(42);
    }
}

class Holder {
    private int n;

    public Holder(int n) {
        this.n = n;
    }

    public void assertSanity() {
        if (n != n) {
            throw new AssertionError("Эта инструкция является ложной.");
        }
    }
}
