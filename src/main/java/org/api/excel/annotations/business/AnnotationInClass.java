package org.api.excel.annotations.business;

import org.apache.commons.collections4.CollectionUtils;

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
    /**
     * Getter if the annotation is class level
     * @param tClass source class
     * @param aClass annotation
     * @return Optional <ANNOTATION> class
     * @param <CLASS>
     * @param <ANNOTATION>
     */
    public static <CLASS, ANNOTATION extends Annotation> Optional<ANNOTATION> getClassAnnotation(Class<CLASS> tClass, Class<ANNOTATION> aClass) {
        return Optional.ofNullable(tClass.getAnnotation(aClass));
    }

    /**
     * Getter if the annotation is present on fields
     * @param tClass source class
     * @param aClass annotation
     * @return Optional List<Field>
     * @param <CLASS>
     * @param <ANNOTATION>
     */
    public static <CLASS, ANNOTATION extends Annotation> Optional<List<Field>> getFieldContainAnnotation(Class<CLASS> tClass, Class<ANNOTATION> aClass) {
        List<Field> fields = Stream.of(tClass.getDeclaredFields())
                .filter(field -> Objects.nonNull(field.getAnnotation(aClass)))
                .collect(Collectors.toList());
        if(CollectionUtils.isEmpty(fields)){
            return Optional.empty();
        }
        return Optional.of(fields);
    }
}
