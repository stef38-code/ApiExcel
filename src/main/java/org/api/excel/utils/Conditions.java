package org.api.excel.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Collection de préconditions
 */
public class Conditions extends Require {
    private Conditions() {
        super();
    }

    /**
     * Collection not empty.
     *
     * @param <T>        the type parameter
     * @param collection the collection
     * @param msg        the msg
     */
    public static <T> void requireNotEmpty(Collection<T> collection, String msg) {
        Objects.requireNonNull(collection, "the Collection cannot be null");
        positiveTest(collection, Collection::isEmpty, msg);
    }

    /**
     * Array not empty.
     *
     * @param objects the objects
     * @param msg     the msg
     */
    public static void requireNotEmpty(Object[] objects, String msg) {
        Objects.requireNonNull(objects, "the Collection cannot be null");
        List<Object> collect = Arrays.stream(objects).collect(Collectors.toList());
        if (collect.contains(null)) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Optional non null.
     *
     * @param value the class annotation
     * @param msg   the msg
     */
    public static <T> void requireNonNull(Optional<T> value, String msg) {
        value.ifPresentOrElse(v -> Objects.requireNonNull(value.get(), "the value cannot be null")
                , () -> {
                    throw new IllegalArgumentException(msg);
                });
    }

    /**
     * String not blank.
     *
     * @param stringValue the string value
     */
    public static void requireNotEmpty(String stringValue) {
        positiveTest(stringValue, StringUtils::isBlank, "String not may be blank");
    }

    /**
     * Require file and exists.
     *
     * @param stringValue the string value
     * @return file
     */
    public static File requireFileAndExists(String stringValue) {
        requireNotEmpty(stringValue);
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
        Objects.requireNonNull(file, "the file cannot be null");
        negativeTest(file, File::exists, "File or directory not exist");
    }

    /**
     * Require is file.
     *
     * @param file the file
     */
    public static void requireIsFile(File file) {
        Objects.requireNonNull(file, "File cannot be null");
        positiveTest(file, Predicate.not(File::isFile), "Is not file");
    }

    public static void requireSheetIsNotEmpty(Sheet sheet) {
        Predicate<Sheet> lastRowNumIsEmpty = s -> (s.getLastRowNum() == -1);
        Predicate<Sheet> getRowZeroIsNull = s -> Objects.isNull(s.getRow(0));

        positiveTest(sheet, lastRowNumIsEmpty.and(getRowZeroIsNull), "Sheet Is Empty");
    }
}
