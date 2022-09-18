package org.api.excel.annotations.business;

import org.api.excel.annotations.ExcelSheet;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class AnnotationExcelSheetTest {

    @Test
    void sheetAnnotation_then_defaultValue_when_defaultValues() {
        Class<DefaultValue> tClass = DefaultValue.class;
        Optional<ExcelSheet> classAnnotation = AnnotationInClass.getClassAnnotation(tClass, ExcelSheet.class);
        assertThat(classAnnotation).isPresent()
                .containsInstanceOf(ExcelSheet.class);
        assertThat(classAnnotation.get().name()).isEmpty();
        assertThat(classAnnotation.get().number()).isEqualTo(0);
        assertThat(classAnnotation.get().rowNumber()).isEqualTo(1);
    }

    @Test
    void sheetAnnotation_then_changeValue_when_values() {
        Class<ChangeValues> tClass = ChangeValues.class;
        Optional<ExcelSheet> classAnnotation = AnnotationInClass.getClassAnnotation(tClass, ExcelSheet.class);
        assertThat(classAnnotation).isNotNull();
        assertThat(classAnnotation.get().name()).hasToString("Test");
        assertThat(classAnnotation.get().number()).isEqualTo(99);
        assertThat(classAnnotation.get().rowNumber()).isEqualTo(25);
    }
    @ExcelSheet
    private class DefaultValue {

    }
    @ExcelSheet(name = "Test",number = 99,rowNumber = 25)
    private class ChangeValues{

    }
}
