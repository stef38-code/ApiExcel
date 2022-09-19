package org.api.excel.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.api.excel.annotations.ExcelSheet;
import org.api.excel.annotations.business.AnnotationInClass;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SheetModelTest {

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SheetModel#builder()#builder()}
     * </ul>
     */
    @Test
    void build_then_notProperties_when_NullPointerException() {
        Assertions.assertThatThrownBy(() -> SheetModel.builder().build())
                .isInstanceOf(NullPointerException.class)
                .hasMessage("the annotation cannot is null");

    }
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SheetModel#builder()#builder()}
     * </ul>
     */
    @Test
    void build_then_cellModelsIsNull_when_NullPointerException() {
        Optional<ExcelSheet> classAnnotation = AnnotationInClass.getClassAnnotation(MyClass.class, ExcelSheet.class);
        ExcelSheet annotation   = classAnnotation.get();
        Assertions.assertThatThrownBy(() -> SheetModel.builder().sheetAnnotation(annotation).build())
                .isInstanceOf(NullPointerException.class)
                .hasMessage("the Collection cannot be null");

    }
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SheetModel#builder()#builder()}
     * </ul>
     */
    @Test
    void build_then_cellModelsIsEmpty_when_IllegalArgumentException() {
        Optional<ExcelSheet> classAnnotation = AnnotationInClass.getClassAnnotation(MyClass.class, ExcelSheet.class);
        ExcelSheet annotation   = classAnnotation.get();
        Assertions.assertThatThrownBy(() -> SheetModel.builder().sheetAnnotation(annotation).cellModels(Collections.EMPTY_LIST).build())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("the Collection cannot be empty");

    }

    @ExcelSheet
    private class MyClass{

    }
}

