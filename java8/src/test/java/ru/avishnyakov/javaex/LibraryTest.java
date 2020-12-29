package ru.avishnyakov.javaex;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.avishnyakov.javaex.model.Track;

import java.util.LongSummaryStatistics;
import java.util.function.Supplier;

import static ru.avishnyakov.javaex.TestData.album;

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

    @Test
    public void testStatistics() {
        final LongSummaryStatistics statistics = album.getTracks()
                .mapToLong(Track::getLength)
                .summaryStatistics();

        System.out.println(statistics);
    }
}
