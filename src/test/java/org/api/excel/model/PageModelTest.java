package org.api.excel.model;

import org.api.excel.annotations.Page;
import org.api.excel.annotations.Book;
import org.api.excel.annotations.business.AnnotationInClass;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

class PageModelTest {

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SheetModel##annotationSheets(Book)#builder()}
     * </ul>
     */
    @Test
    void build_then_notProperties_when_NullPointerException() {
        Assertions.assertThatThrownBy(SheetModel.annotationSheets(null)::build)
                .isInstanceOf(NullPointerException.class)
                .hasMessage("the annotation cannot is null");

    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SheetModel#annotationSheets(Book)#builder()}
     * </ul>
     */
    @Test
    void build_then_sheetAnnotationIsNull_when_NullPointerException() {
        Assertions.assertThatThrownBy(SheetModel.annotationSheets(null).sheetAnnotation(null)::build)
                .isInstanceOf(NullPointerException.class)
                .hasMessage("the annotation cannot is null");

    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SheetModel##annotationSheets(Book)#builder()}
     * </ul>
     */
    @Test
    void build_then_cellModelsIsNull_when_NullPointerException() {
        Optional<Page> classAnnotation = AnnotationInClass.getClassAnnotation(MyClass.class, Page.class);
        Page annotation = classAnnotation.orElse(null);
        Assertions.assertThatThrownBy(SheetModel.annotationSheets(null).sheetAnnotation(annotation)::build)
                .isInstanceOf(NullPointerException.class)
                .hasMessage("the Collection cannot be null");

    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SheetModel#annotationSheets(Book)#builder()}
     * </ul>
     */
    @Test
    void build_then_cellModelsIsEmpty_when_IllegalArgumentException() {
        Optional<Page> classAnnotation = AnnotationInClass.getClassAnnotation(MyClass.class, Page.class);

        Page annotation = classAnnotation.orElse(null);
        Assertions.assertThatThrownBy(SheetModel.annotationSheets(null).sheetAnnotation(annotation).cellModels(Collections.emptyList())::build)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("the Collection cannot be empty");

    }

    @Page
    private static class MyClass {

    }
}

