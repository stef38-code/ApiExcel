package org.api.excel.annotations.business;

import org.api.excel.annotations.ExcelSheet;
import org.api.excel.annotations.ExcelSheets;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class AnnotationExcelSheetsTest {

    @Test
    void sheetAnnotation_then_defaultValue_when_defaultValues() {
        Class<DefaultValue> tClass = DefaultValue.class;
        Optional<ExcelSheets> classAnnotation = AnnotationInClass.getClassAnnotation(tClass, ExcelSheets.class);
        assertThat(classAnnotation).isPresent();
        assertThat(classAnnotation.get()).isNotNull();
        assertThat(classAnnotation.get().value()).isEmpty();
    }

    @Test
    void sheetAnnotation_then_changeValue_when_values() {
        Class<ChangeValues> tClass = ChangeValues.class;
        Optional<ExcelSheets> classAnnotation = AnnotationInClass.getClassAnnotation(tClass, ExcelSheets.class);
        assertThat(classAnnotation).isNotNull();
        assertThat(classAnnotation).isPresent();
        ExcelSheets actual = classAnnotation.get();
        assertThat(actual).isNotNull();
        ExcelSheet[] value = actual.value();
        assertThat(value).isNotEmpty().hasSize(1);
        ExcelSheet excelSheet = value[0];
        assertThat(excelSheet.name()).hasToString("Test");
        assertThat(excelSheet.number()).isEqualTo(99);
        assertThat(excelSheet.rowNumber()).isEqualTo(25);
    }

    @ExcelSheets()
    private class DefaultValue {

    }

    @ExcelSheets(value = {@ExcelSheet(name = "Test", number = 99, rowNumber = 25)})
    private class ChangeValues {

    }

}
