package org.api.excel.annotations.business;

import java.lang.annotation.Annotation;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.CanIgnoreReturnValue;
import org.assertj.core.util.CheckReturnValue;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnnotationInClassTest {
    /**
     * Method under test: {@link AnnotationInClass#isAnnotationPresent(Class, Class)}
     */
    @Test
    void isAnnotationPresent_then_Object_when_false() {
        Class<Object> tClass = Object.class;
        assertThat(AnnotationInClass.isAnnotationPresent(tClass, Annotation.class)).isFalse();
    }
    /**
     * Method under test: {@link AnnotationInClass#isAnnotationPresent(Class, Class)}
     */
    @Test
    void isAnnotationPresent_then_AssertionsObject_when_true() {
        Class<AnnotationInClassTest> tClass = AnnotationInClassTest.class;
        assertThat(AnnotationInClass.isAnnotationPresent(tClass, Test.class)).isTrue();
    }
    /**
     * Method under test: {@link AnnotationInClass#isAnnotationPresent(Class, Class)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void isAnnotationPresent2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Class.isAnnotationPresent(java.lang.Class)" because "tClass" is null
        //       at org.api.excel.annotations.business.AnnotationInClass.isAnnotationPresent(AnnotationInClass.java:15)
        //   In order to prevent isAnnotationPresent(Class, Class)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   isAnnotationPresent(Class, Class).
        //   See https://diff.blue/R013 to resolve this issue.

        AnnotationInClass.isAnnotationPresent(null, Annotation.class);
    }

    /**
     * Method under test: {@link AnnotationInClass#isFieldAnnotationPresent(Class, Class)}
     */
    @Test
    void isFieldAnnotationPresent() {
        Class<Object> tClass = Object.class;
        assertThat(AnnotationInClass.isFieldAnnotationPresent(tClass, Annotation.class)).isFalse();
    }

    /**
     * Method under test: {@link AnnotationInClass#isFieldAnnotationPresent(Class, Class)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void isFieldAnnotationPresent2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Class.getDeclaredFields()" because "tClass" is null
        //       at org.api.excel.annotations.business.AnnotationInClass.isFieldAnnotationPresent(AnnotationInClass.java:19)
        //   In order to prevent isFieldAnnotationPresent(Class, Class)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   isFieldAnnotationPresent(Class, Class).
        //   See https://diff.blue/R013 to resolve this issue.

        AnnotationInClass.isFieldAnnotationPresent(null, Annotation.class);
    }

    /**
     * Method under test: {@link AnnotationInClass#getClassAnnotation(Class, Class)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void getClassAnnotation() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "obj" is null
        //   In order to prevent getClassAnnotation(Class, Class)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getClassAnnotation(Class, Class).
        //   See https://diff.blue/R013 to resolve this issue.

        Class<Object> tClass = Object.class;
        AnnotationInClass.getClassAnnotation(tClass, Annotation.class).annotationType();
    }

    /**
     * Method under test: {@link AnnotationInClass#getFieldContainAnnotation(Class, Class)}
     */
    @Test
    void getFieldContainAnnotation() {
        Class<Object> tClass = Object.class;
        assertThat(AnnotationInClass.getFieldContainAnnotation(tClass, Annotation.class).isEmpty()).isTrue();
    }

    /**
     * Method under test: {@link AnnotationInClass#getFieldContainAnnotation(Class, Class)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void getFieldContainAnnotation2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Class.getDeclaredFields()" because "tClass" is null
        //       at org.api.excel.annotations.business.AnnotationInClass.getFieldContainAnnotation(AnnotationInClass.java:28)
        //   In order to prevent getFieldContainAnnotation(Class, Class)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getFieldContainAnnotation(Class, Class).
        //   See https://diff.blue/R013 to resolve this issue.

        AnnotationInClass.getFieldContainAnnotation(null, Annotation.class);
    }

}
