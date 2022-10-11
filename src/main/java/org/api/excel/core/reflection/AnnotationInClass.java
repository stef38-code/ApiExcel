package org.api.excel.core.reflection;

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
     * @param <C>
     * @param <A>
     */
    public static <C, A extends Annotation> Optional<A> getClassAnnotation(Class<C> tClass, Class<A> aClass) {
        return Optional.ofNullable(tClass.getAnnotation(aClass));
    }

    /**
     * Getter if the annotation is present on fields
     * @param tClass source class
     * @param aClass annotation
     * @return Optional List<Field>
     * @param <C>
     * @param <A>
     */
    public static <C, A extends Annotation> Optional<List<Field>> getFieldContainAnnotation(Class<C> tClass, Class<A> aClass) {
        List<Field> fields = Stream.of(tClass.getDeclaredFields())
                .filter(field -> Objects.nonNull(field.getAnnotation(aClass)))
                .collect(Collectors.toList());
        if(CollectionUtils.isEmpty(fields)){
            return Optional.empty();
        }
        return Optional.of(fields);
    }
}
