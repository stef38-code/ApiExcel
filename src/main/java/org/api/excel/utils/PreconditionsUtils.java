package org.api.excel.utils;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class PreconditionsUtils {
    private PreconditionsUtils() {
        throw new UnsupportedOperationException("CollectionUtils is a utility class and cannot be instantiated");
    }

    private static <T> void requireNonEmpty(T object, Predicate<T> predicate, String msgToCaller) {
        Objects.requireNonNull(predicate);
        if (predicate.test(object)) {
            throw new IllegalArgumentException(msgToCaller);
        }
    }

    public static <T> void requireNotEmpty(Collection<T> object, String msg) {
        Objects.requireNonNull(object, "the Collection cannot be null");
        requireNonEmpty(object, Collection::isEmpty, msg);
    }

    public static void requireNonNull(Optional<?> classAnnotation, String msg) {
        if (!classAnnotation.isPresent()) {
            throw new IllegalArgumentException(msg);
        }
    }
}
