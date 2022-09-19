package org.api.excel.annotations.business;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnnotationInClassTest {


    /**
     * Method under test: {@link AnnotationInClass#getClassAnnotation(Class, Class)}
     */
    @Test
    void getClassAnnotation_then_NotAnnotationOnClass_when_Null() {
        Class<Object> tClass = Object.class;
        Optional<Annotation> classAnnotation = AnnotationInClass.getClassAnnotation(tClass, Annotation.class);
        assertThat(classAnnotation).isNotPresent();
    }
    @Test
    void getClassAnnotation_then_DeprecatedOnClass_when_Deprecated() {
        Class<MyTest> tClass = MyTest.class;
        Optional<Deprecated> classAnnotation = AnnotationInClass.getClassAnnotation(tClass, Deprecated.class);
        assertThat(classAnnotation).isPresent().containsInstanceOf(Deprecated.class);
    }
    /**
     * Method under test: {@link AnnotationInClass#getFieldContainAnnotation(Class, Class)}
     */
    @Test
    void getFieldContainAnnotation() {
        Class<Object> tClass = Object.class;
        assertThat(AnnotationInClass.getFieldContainAnnotation(tClass, Annotation.class)).isNotPresent();
    }

    /**
     * Method under test: {@link AnnotationInClass#getFieldContainAnnotation(Class, Class)}
     */
    @Test
    void getFieldContainAnnotation_then_TwoFieldWithAnAnnotation_when_sizeTwo() {
        Class<MyTest2> tClass = MyTest2.class;

        Optional<List<Field>> fields = AnnotationInClass.getFieldContainAnnotation(tClass, NotNull.class);
        assertThat(fields).isPresent().containsInstanceOf(List.class);
        assertThat(fields.get()).isNotNull().hasSize(2);
    }
    @Deprecated
    private class MyTest{

    }
    private class MyTest2{
        @NotNull(message = "Name cannot be null")
        private String name;
        @NotNull
        private String value2;
    }
}
