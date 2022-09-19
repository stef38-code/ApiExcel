package org.api.excel.utils;

import org.apache.poi.ss.formula.functions.T;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

public class CollectionUtils {
    private static <T> void requireNonEmpty(T object, Predicate<T> predicate, String msgToCaller){
        Objects.requireNonNull(object);
        Objects.requireNonNull(predicate);
        if (predicate.test(object)){
            throw new IllegalArgumentException(msgToCaller);
        }
    }
    public static <T> void requireNotEmpty(Collection<T>  object, String msg){
        Objects.requireNonNull(object,"the Collection cannot be null");
        Predicate<Collection<T>> predicate = Collection::isEmpty;
        if (predicate.test(object)){
            throw new IllegalArgumentException(msg);
        }
    }
}
