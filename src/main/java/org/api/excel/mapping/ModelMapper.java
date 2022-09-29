package org.api.excel.mapping;

import org.api.excel.annotations.ExcelCell;
import org.api.excel.annotations.ExcelSheet;
import org.api.excel.annotations.ExcelSheets;
import org.api.excel.annotations.business.AnnotationInClass;
import org.api.excel.model.CellModel;
import org.api.excel.model.SheetModel;
import org.api.excel.utils.Conditions;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ModelMapper {
    private static ModelMapper instance = null;

    private ModelMapper() {
    }

    public static ModelMapper getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ModelMapper();
        }

        return instance;
    }

    public SheetModel to(Class<?> clazz) {
        Optional<ExcelSheet> annotationSheet = AnnotationInClass.getClassAnnotation(clazz, ExcelSheet.class);
        Optional<ExcelSheets> annotationSheets = AnnotationInClass.getClassAnnotation(clazz, ExcelSheets.class);
        Optional<List<Field>> fieldContainAnnotation = AnnotationInClass.getFieldContainAnnotation(clazz, ExcelCell.class);
        //deux present ou les deux absents
        if (annotationSheet.isPresent() == annotationSheets.isPresent()) {
            throw new RuntimeException("ExcelSheet or ExcelSheets mandatory");
        }
        //Conditions.requireNonNull(annotationSheet, "ExcelSheet annotation not found");
        Conditions.requireNonNull(fieldContainAnnotation, "No fields found with ExcelCell annotation");
        //
        return SheetModel.annotationSheets(annotationSheets.orElse(null))
                .sheetAnnotation(annotationSheet.orElse(null))
                .cellModels(
                        to(fieldContainAnnotation.orElse(null))).build();
    }

    private CellModel to(Field field) {
        return CellModel.builder().field(field).build();
    }

    private List<CellModel> to(List<Field> fields) {
        Objects.requireNonNull(fields, "No fields found");
        return fields.stream().map(this::to).collect(Collectors.toList());
    }
}
