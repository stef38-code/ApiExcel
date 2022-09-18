package org.api.excel.annotations.business;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnnotationInClass {
    private AnnotationInClass() {
    }


    protected static <CLASS, ANNOTATION extends Annotation> boolean isFieldAnnotationPresent(Class<CLASS> tClass, Class<ANNOTATION> aClass) {
        return Stream.of(tClass.getDeclaredFields())
                .anyMatch(field -> Objects.nonNull(field.getAnnotation(aClass)));
    }
    /**
     * Getter if the annotation is class level
     * @param tClass source class
     * @param aClass annotation
     * @return Optional <ANNOTATION> class
     * @param <CLASS>
     * @param <ANNOTATION>
     */
    protected static <CLASS, ANNOTATION extends Annotation> Optional<ANNOTATION> getClassAnnotation(Class<CLASS> tClass, Class<ANNOTATION> aClass) {
        return Optional.ofNullable(tClass.getAnnotation(aClass));
    }
    protected static <CLASS, ANNOTATION extends Annotation> List<Field> getFieldContainAnnotation(Class<CLASS> tClass, Class<ANNOTATION> aClass) {
        return Stream.of(tClass.getDeclaredFields())
                .filter(field -> Objects.nonNull(field.getAnnotation(aClass)))
                .collect(Collectors.toList());
    }
}
