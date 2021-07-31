package ru.avishnyakov.concurrency;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@ThreadSafe
public class VolatileCachedFactorizer {
    private volatile OneValueCache cache = new OneValueCache(null, null);

    public void service(ServletRequest req, ServletResponse resp) {
        // ...
    }
}
