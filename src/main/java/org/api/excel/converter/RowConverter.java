package org.api.excel.converter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.api.excel.annotations.ExcelCell;
import org.api.excel.file.CellTools;
import org.api.excel.model.CellModel;
import org.api.excel.reflection.Reflective;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

public class RowConverter {
    private static RowConverter instance = null;

    private RowConverter() {
    }

    public static RowConverter getInstance() {
        if (Objects.isNull(instance)) {
            instance = new RowConverter();
        }

        return instance;
    }

    private <T> void setField(T entity, Field field, Cell cell) {
        try {
            Reflective.setterField(entity, field.getName(), CellTools.getValue(field.getAnnotation(ExcelCell.class), cell));
        } catch (ReflectiveOperationException e) {
            throw new RowConverterException(e);
        }
    }

    public <T> T toClass(Class<T> tClass, List<CellModel> cellModels, Row row) {
        try {
            T entity = Reflective.createInstance(tClass);
            cellModels.forEach(cellModel -> this.cellToField(row, entity, cellModel));
            return entity;
        } catch (ReflectiveOperationException e) {
            throw new RowConverterException(e);
        }
    }

    private <T> void cellToField(Row row, T entity, CellModel cellModel) {
        Field field = cellModel.getField();
        Cell cell = row.getCell(field.getAnnotation(ExcelCell.class).number());
        setField(entity, cellModel.getField(), cell);
    }
}
