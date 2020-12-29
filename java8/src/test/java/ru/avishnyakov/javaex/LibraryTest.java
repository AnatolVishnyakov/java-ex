package ru.avishnyakov.javaex;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.avishnyakov.javaex.model.Track;

import java.util.LongSummaryStatistics;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
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

    @Test
    public void testOverloadByType() {
        overloadMethod("abc");
    }

    private void overloadMethod(Object o) {
        System.out.println("Object");
    }

    private void overloadMethod(String s) {
        System.out.println("String");
    }

    @Test
    public void testOverloadByLambda() {
        overloadMethodLambda((x, y) -> x + y);
    }

    private void overloadMethodLambda(BinaryOperator<Integer> lambda) {
        System.out.println("BinaryOperator");
    }

    private void overloadMethodLambda(IntegerBiFunction lambda) {
        System.out.println("IntegerBiFunction");
    }

    // throwing exception
//    private void overloadMethodLambda(StringBiFunction lambda) {
//        System.out.println("StringBiFunction");
//    }

    private interface IntegerBiFunction extends BinaryOperator<Integer> {
    }

    private interface StringBiFunction extends BinaryOperator<String> {
    }

    @Test
    public void testOverloadPredicate() {
        // throwing exception
//        overloadMethodPredicate((x) -> true);
    }

    private interface IntPredicate {
        boolean test(int value);
    }

    private void overloadMethodPredicate(Predicate<Integer> predicate) {
        System.out.println("Predicate");
    }

    private void overloadMethodPredicate(IntPredicate predicate) {
        System.out.println("IntPredicate");
    }
}
