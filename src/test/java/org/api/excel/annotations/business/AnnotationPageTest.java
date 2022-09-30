package org.api.excel.annotations.business;

import org.api.excel.annotations.Page;
import org.api.excel.annotations.Book;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class AnnotationPageTest {

    @Test
    void sheetAnnotation_then_defaultValue_when_defaultValues() {
        Class<DefaultValue> tClass = DefaultValue.class;
        Optional<Page> classAnnotation = AnnotationInClass.getClassAnnotation(tClass, Page.class);
        assertThat(classAnnotation).isPresent()
                .containsInstanceOf(Page.class);
        assertThat(classAnnotation.get().name()).isEmpty();
        assertThat(classAnnotation.get().number()).isZero();
        assertThat(classAnnotation.get().rowNumber()).isOne();
    }

    @Test
    void sheetAnnotation_then_changeValue_when_values() {
        Class<ChangeValues> tClass = ChangeValues.class;
        Optional<Page> classAnnotation = AnnotationInClass.getClassAnnotation(tClass, Page.class);
        assertThat(classAnnotation).isNotNull();
        assertThat(classAnnotation.get().name()).hasToString("Test");
        assertThat(classAnnotation.get().number()).isEqualTo(99);
        assertThat(classAnnotation.get().rowNumber()).isEqualTo(25);
    }

    @Test
    void excelSheetsAnnotation_then_changeValue_when_values() {
        Class<DefaultExcelSheet> tClass = DefaultExcelSheet.class;
        Optional<Book> classAnnotation = AnnotationInClass.getClassAnnotation(tClass, Book.class);
        assertThat(classAnnotation).isPresent()
                .containsInstanceOf(Book.class);
        assertThat(classAnnotation.get().value()).isEmpty();
    }

    @Page
    private class DefaultValue {

    }

    @Page(name = "Test", number = 99, rowNumber = 25)
    private class ChangeValues {

    }

    @Book(value = {})
    private class DefaultExcelSheet {

    }
}
