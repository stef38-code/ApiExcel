package org.api.excel.annotations.business;

import org.api.excel.annotations.ExcelCell;
import org.api.excel.annotations.ExcelSheet;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class AnnotationExcelCellTest {
    @Test
    void cellAnnotation_then_NotAnnotation_when_isNotPresent() {
        Class<NotAnnotation> tClass = NotAnnotation.class;
        Optional<List<Field>> fieldContainAnnotation = AnnotationInClass.getFieldContainAnnotation(tClass, ExcelCell.class);
        assertThat(fieldContainAnnotation).isNotPresent();
    }
    @Test
    void cellAnnotation_then_defaultValue_when_defaultValues() {
        Class<DefaultValue> tClass = DefaultValue.class;
        Optional<List<Field>> fieldContainAnnotation = AnnotationInClass.getFieldContainAnnotation(tClass, ExcelCell.class);
        assertThat(fieldContainAnnotation).isPresent()
                .containsInstanceOf(List.class);
        List<Field> fields = fieldContainAnnotation.get();
        assertThat(fields).isNotEmpty().hasSize(1);
        Field fieldName = fields.get(0);
        assertThat(fieldName.getName()).isEqualTo("name");
        assertThat(fieldName.getAnnotation(ExcelCell.class).number()).isEqualTo(0);
        assertThat(fieldName.getAnnotation(ExcelCell.class).stringFormat()).isFalse();
    }

    @Test
    void cellAnnotation_then_changeValue_when_values() {
        Class<ChangeValues> tClass = ChangeValues.class;
        Optional<List<Field>> fieldContainAnnotation = AnnotationInClass.getFieldContainAnnotation(tClass, ExcelCell.class);
        assertThat(fieldContainAnnotation).isPresent()
                .containsInstanceOf(List.class);
        List<Field> fields = fieldContainAnnotation.get();
        assertThat(fields).isNotEmpty().hasSize(2);
        Field fieldFirstName = fields.get(0);
        assertThat(fieldFirstName.getName()).isEqualTo("firstname");
        assertThat(fieldFirstName.getAnnotation(ExcelCell.class).number()).isEqualTo(12);
        assertThat(fieldFirstName.getAnnotation(ExcelCell.class).stringFormat()).isTrue();

        Field fieldLastName = fields.get(1);
        assertThat(fieldLastName.getName()).isEqualTo("lastname");
        assertThat(fieldLastName.getAnnotation(ExcelCell.class).number()).isEqualTo(13);
        assertThat(fieldLastName.getAnnotation(ExcelCell.class).stringFormat()).isFalse();
    }
    private class NotAnnotation {
        private String name;
    }
    private class DefaultValue {
        @ExcelCell
        private String name;
    }

    private class ChangeValues{
        @ExcelCell(number=12,stringFormat = true)
        private String firstname;
        @ExcelCell(number=13)
        private String lastname;
    }
}
