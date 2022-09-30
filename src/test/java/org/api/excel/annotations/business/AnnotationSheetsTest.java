package org.api.excel.annotations.business;

import org.api.excel.annotations.Page;
import org.api.excel.annotations.Book;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class AnnotationSheetsTest {

    @Test
    void sheetAnnotation_then_defaultValue_when_defaultValues() {
        Class<DefaultValue> tClass = DefaultValue.class;
        Optional<Book> classAnnotation = AnnotationInClass.getClassAnnotation(tClass, Book.class);
        assertThat(classAnnotation).isPresent();
        assertThat(classAnnotation.get()).isNotNull();
        assertThat(classAnnotation.get().value()).isEmpty();
    }

    @Test
    void sheetAnnotation_then_changeValue_when_values() {
        Class<ChangeValues> tClass = ChangeValues.class;
        Optional<Book> classAnnotation = AnnotationInClass.getClassAnnotation(tClass, Book.class);
        assertThat(classAnnotation).isNotNull().isPresent();
        Book actual = classAnnotation.get();
        assertThat(actual).isNotNull();
        Page[] value = actual.value();
        assertThat(value).isNotEmpty().hasSize(1);
        Page page = value[0];
        assertThat(page.name()).hasToString("Test");
        assertThat(page.number()).isEqualTo(99);
        assertThat(page.rowNumber()).isEqualTo(25);
    }

    @Book()
    private static class DefaultValue {

    }

    @Book(value = {@Page(name = "Test", number = 99, rowNumber = 25)})
    private static class ChangeValues {

    }

}
