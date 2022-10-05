package org.api.excel.mapping;

import org.api.excel.core.annotations.Book;
import org.api.excel.core.annotations.Box;
import org.api.excel.core.annotations.Page;
import org.api.excel.model.commun.CellModel;
import org.api.excel.model.commun.BookModel;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ModelMapperTest {
    /**
     * Method under test: {@link ModelMapper#getInstance()}
     */
    @Test
    void getInstance() {
        ModelMapper instance = ModelMapper.getInstance();
        assertThat(instance).isNotNull();
    }

    /**
     * Method under test: {@link ModelMapper#to(Class)}
     */
    @Test
    void to() {
        ModelMapper instance = ModelMapper.getInstance();
        BookModel model = instance.to(DefaultClass.class);
        assertThat(model).isNotNull();
        Book annotationSheets = model.getAnnotationBook();
        assertThat(annotationSheets.value()).isNotEmpty().hasSize(1);
        Page pageAnnotation = annotationSheets.value()[0];
        assertThat(pageAnnotation).isNotNull().isInstanceOf(Page.class);
        assertThat(pageAnnotation.name()).isEmpty();
        assertThat(pageAnnotation.number()).isZero();
        assertThat(pageAnnotation.rowNumber()).isOne();

        List<CellModel> cellModels = model.getCellModels();
        assertThat(cellModels).isNotEmpty().hasSize(4);
        cellModels.forEach(element -> {
            Field field = element.getField();
            Box annotation = element.getAnnotation();

            assertThat(field).isNotNull();
            assertThat(annotation).isNotNull().isInstanceOf(Box.class);


        });
    }

    @Page
    private static class DefaultClass {
        @Box
        private String firstname;
        @Box
        private String lastname;
        @Box
        private LocalDate dnaiss;
        @Box
        private int age;
    }
}

