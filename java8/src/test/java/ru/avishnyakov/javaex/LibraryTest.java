package ru.avishnyakov.javaex;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class LibraryTest {
    private static final Logger logger = LoggerFactory.getLogger(LibraryTest.class);

    @Test
    public void testLoggerDebug() {
        if (logger.isDebugEnabled()) {
            logger.debug("Some message");
        }
    }

    @Test
    public void testLambdaLoggerDebug() {
        debug(() -> "Some message lambda");
    }

    private void debug(Supplier<String> message) {
        if (logger.isDebugEnabled()) {
            logger.debug(message.get());
        }
    }
}
