package org.api.excel.model;

import org.api.excel.core.annotations.Book;
import org.api.excel.core.annotations.Page;
import org.api.excel.core.reflection.AnnotationInClass;
import org.api.excel.model.commun.BookModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

class PageModelTest {

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BookModel##annotationSheets(Book)#builder()}
     * </ul>
     */
    @Test
    void build_then_notProperties_when_NullPointerException() {
        Assertions.assertThatThrownBy(BookModel.aNew().pageAnnotationSheet(null).cellModels(Collections.emptyList())::create)
                .isInstanceOf(NullPointerException.class)
                .hasMessage("the annotation cannot is null");

    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BookModel#aNew#bookAnnotationSheet#cellModels#create()}
     * </ul>
     */
    @Test
    void build_then_sheetAnnotationIsNull_when_NullPointerException() {
        Assertions.assertThatThrownBy(BookModel.aNew().bookAnnotationSheet(null).cellModels(Collections.emptyList())::create)
                .isInstanceOf(NullPointerException.class)
                .hasMessage("the annotation cannot is null");

    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BookModel##annotationSheets(Book)#builder()}
     * </ul>
     */
    @Test
    void build_then_cellModelsIsNull_when_NullPointerException() {
        Optional<Page> classAnnotation = AnnotationInClass.getClassAnnotation(MyClass.class, Page.class);
        Page annotation = classAnnotation.orElse(null);
        Assertions.assertThatThrownBy(BookModel.aNew().pageAnnotationSheet(annotation).cellModels(null)::create)
                .isInstanceOf(NullPointerException.class)
                .hasMessage("the Collection cannot be null");

    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BookModel#annotationSheets(Book)#builder()}
     * </ul>
     */
    @Test
    void build_then_cellModelsIsEmpty_when_IllegalArgumentException() {
        Optional<Page> classAnnotation = AnnotationInClass.getClassAnnotation(MyClass.class, Page.class);

        Page annotation = classAnnotation.orElse(null);
        Assertions.assertThatThrownBy(BookModel.aNew().pageAnnotationSheet(annotation).cellModels(Collections.emptyList())::create)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("the Collection cannot be empty");

    }

    @Page
    private static class MyClass {

    }
}

