package org.api.excel.core.utils;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * The type Require.
 */
public class Require {
    /**
     * Instantiates a new Require.
     */
    Require() {
        throw new UnsupportedOperationException("Require is a utility class and cannot be instantiated");
    }

    /**
     * Positive test.
     *
     * @param <T>         the type parameter
     * @param object      the object
     * @param predicate   the predicate
     * @param msgToCaller the msg to caller
     */
    protected static <T> void positiveTest(T object, Predicate<T> predicate, String msgToCaller) {
        execute(object, predicate, msgToCaller);
    }

    /**
     * Negative test.
     *
     * @param <T>         the type parameter
     * @param object      the object
     * @param predicate   the predicate
     * @param msgToCaller the msg to caller
     */
    protected static <T> void negativeTest(T object, Predicate<T> predicate, String msgToCaller) {
        execute(object, predicate.negate(), msgToCaller);
    }
    private static <T> void execute(T object, Predicate<T> predicate, String msgToCaller){
        Objects.requireNonNull(predicate);
        if (predicate.test(object)) {
            throw new IllegalArgumentException(msgToCaller);
        }
    }
}
