package org.api.excel.mapping;

import org.api.excel.annotations.ExcelCell;
import org.api.excel.annotations.ExcelSheet;
import org.api.excel.annotations.ExcelSheets;
import org.api.excel.model.CellModel;
import org.api.excel.model.SheetModel;
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
        SheetModel model = instance.to(DefaultClass.class);
        assertThat(model).isNotNull();
        ExcelSheets annotationSheets = model.getAnnotationSheets();
        assertThat(annotationSheets.value()).isNotEmpty().hasSize(1);
        ExcelSheet sheetAnnotation = annotationSheets.value()[0];
        assertThat(sheetAnnotation).isNotNull().isInstanceOf(ExcelSheet.class);
        assertThat(sheetAnnotation.name()).isEmpty();
        assertThat(sheetAnnotation.number()).isZero();
        assertThat(sheetAnnotation.rowNumber()).isOne();

        List<CellModel> cellModels = model.getCellModels();
        assertThat(cellModels).isNotEmpty().hasSize(4);
        cellModels.forEach(element -> {
            Field field = element.getField();
            ExcelCell annotation = element.getAnnotation();

            assertThat(field).isNotNull();
            assertThat(annotation).isNotNull().isInstanceOf(ExcelCell.class);


        });
    }

    @ExcelSheet
    private class DefaultClass {
        @ExcelCell
        private String firstname;
        @ExcelCell
        private String lastname;
        @ExcelCell
        private LocalDate dnaiss;
        @ExcelCell
        private int age;
    }
}

