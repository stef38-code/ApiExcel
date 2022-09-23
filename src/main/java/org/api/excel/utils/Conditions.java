package org.api.excel.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Conditions.
 */
public class Conditions extends Require{
    private Conditions() {
        throw new UnsupportedOperationException("PreconditionsUtils is a utility class and cannot be instantiated");
    }


    /**
     * Require not empty.
     *
     * @param <T>        the type parameter
     * @param collection the collection
     * @param msg        the msg
     */
    public static <T> void requireNotBlank(Collection<T> collection, String msg) {
        Objects.requireNonNull(collection, "the Collection cannot be null");
        execute(collection, Collection::isEmpty, msg);
    }

    /**
     * Require non null.
     *
     * @param optional the class annotation
     * @param msg      the msg
     */
    public static void requireNonNull(Optional<?> optional, String msg) {
        if (!optional.isPresent()) {
            throw new IllegalArgumentException(msg);
        }
        Objects.requireNonNull(optional.get(),"the value cannot be null");
    }

    /**
     * Require not blank.
     *
     * @param stringValue the string value
     */
    public static void requireNotBlank(String stringValue) {
        execute(stringValue, StringUtils::isNotBlank, "String not may be blank");
    }

    /**
     * Require file and exists.
     *
     * @param stringValue the string value
     * @return
     */
    public static File requireFileAndExists(String stringValue) {
        requireNotBlank(stringValue);
        File file = new File(stringValue);
        requireIsFile(file);
        requireExists(file);
        return file;
    }

    /**
     * Require exists.
     *
     * @param file the file
     */
    public static void requireExists(File file) {
        Objects.requireNonNull(file,"the file cannot be null");
        execute(file,File::exists,"File or directory not exist");
    }

    /**
     * Require is file.
     *
     * @param file the file
     */
    public static void requireIsFile(File file) {
        Objects.requireNonNull(file,"File cannot be null");
        execute(file,File::isFile,"File not exist");
    }
}
