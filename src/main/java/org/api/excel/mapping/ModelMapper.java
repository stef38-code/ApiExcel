package org.api.excel.mapping;

import org.api.excel.core.annotations.Book;
import org.api.excel.core.annotations.Box;
import org.api.excel.core.annotations.Page;
import org.api.excel.core.reflection.AnnotationInClass;
import org.api.excel.core.utils.Conditions;
import org.api.excel.model.commun.BookModel;
import org.api.excel.model.commun.CellModel;

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

    public BookModel to(Class<?> clazz) {
        Optional<Page> pageAnnotationSheet = AnnotationInClass.getClassAnnotation(clazz, Page.class);
        Optional<Book> bookAnnotationSheets = AnnotationInClass.getClassAnnotation(clazz, Book.class);
        Optional<List<Field>> fieldContainAnnotation = AnnotationInClass.getFieldContainAnnotation(clazz, Box.class);
        //deux present ou les deux absents
        if (pageAnnotationSheet.isPresent() == bookAnnotationSheets.isPresent()) {
            throw new ModelMapperException("ExcelSheet or ExcelSheets mandatory");
        }
        //
        Conditions.requireNonNull(fieldContainAnnotation, "No fields found with ExcelCell annotation");
        //
        List<CellModel> cellModels = to(fieldContainAnnotation.orElse(null));
        if (bookAnnotationSheets.isPresent()) {
            return BookModel.aNew().book(bookAnnotationSheets.get()).cellModels(cellModels).create();
        }
        return BookModel.aNew()
                .page(pageAnnotationSheet.orElseThrow())
                .cellModels(
                        cellModels).create();
    }

    private CellModel to(Field field) {
        return CellModel.aNew().field(field).create();
    }

    private List<CellModel> to(List<Field> fields) {
        Objects.requireNonNull(fields, "No fields found");
        return fields.stream().map(this::to).collect(Collectors.toList());
    }
}
