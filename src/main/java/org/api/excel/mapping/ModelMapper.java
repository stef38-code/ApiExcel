package org.api.excel.mapping;

import org.api.excel.annotations.ExcelCell;
import org.api.excel.annotations.ExcelSheet;
import org.api.excel.annotations.business.AnnotationInClass;
import org.api.excel.model.CellModel;
import org.api.excel.model.SheetModel;
import org.api.excel.utils.PreconditionsUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ModelMapper {
    private ModelMapper() {
    }
    private static ModelMapper instance = null;

    public static ModelMapper getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ModelMapper();
        }

        return instance;
    }
    public SheetModel to(Class<?> clazz){
        Optional<ExcelSheet> classAnnotation = AnnotationInClass.getClassAnnotation(clazz, ExcelSheet.class);
        Optional<List<Field>> fieldContainAnnotation = AnnotationInClass.getFieldContainAnnotation(clazz, ExcelCell.class);
        //
        PreconditionsUtils.requireNonNull(classAnnotation,"ExcelSheet annotation not found");
        PreconditionsUtils.requireNonNull(fieldContainAnnotation,"No fields found with ExcelCell annotation");
        //
        return SheetModel.builder()
                .sheetAnnotation(classAnnotation.get())
                .cellModels(
                        to(fieldContainAnnotation.get())).build();
    }
    public CellModel to(Field field){
        return CellModel.builder().field(field).build();
    }
    public List<CellModel> to(List<Field> fields){
        return fields.stream().map( field -> CellModel.builder().field(field).build()).collect(Collectors.toList());
    }
}
