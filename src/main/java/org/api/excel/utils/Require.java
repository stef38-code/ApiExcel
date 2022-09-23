package org.api.excel.utils;

import java.util.Objects;
import java.util.function.Predicate;

public class Require {
    private Require() {
        throw new UnsupportedOperationException("Require is a utility class and cannot be instantiated");
    }
    protected static <T> void execute(T object, Predicate<T> predicate, String msgToCaller) {
        Objects.requireNonNull(predicate);
        if (predicate.test(object)) {
            throw new IllegalArgumentException(msgToCaller);
        }
    }
}
