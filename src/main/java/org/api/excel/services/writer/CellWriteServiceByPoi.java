package org.api.excel.services.writer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.api.excel.core.file.Excel;
import org.api.excel.core.reflection.Reflective;
import org.api.excel.core.utils.Debug;
import org.api.excel.core.utils.Info;
import org.api.excel.model.RowHeaderWriteModel;
import org.api.excel.model.commun.CellModel;

import java.util.ArrayList;
import java.util.List;

public class CellWriteServiceByPoi<T> implements CellWriteService<T> {

    @Override
    public void execute(Row row, Integer index, Object value) {
        Cell cell = row.createCell(index);
        Excel.setData(cell, value);
    }

    @Override
    public void execute(List<CellModel> cellModels, RowHeaderWriteModel rowHeaderWriteModel, Row row, T entitie) {
        int size = cellModels.size();
        List<Cell> cells = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            cells.add(row.createCell(j));
        }
        cellModels.forEach(fields -> rowHeaderWriteModel.getHeader().forEach((position, v) -> {
            try {
                Info.print(CellWriteServiceByPoi.class, "Field type: {0}", v.getNameField().getClass().getSimpleName());
                Object o = Reflective.getterField(entitie, v.getNameField());
                Debug.print(CellWriteServiceByPoi.class, "cells.get(position){0}, o{1}", position, o);

                execute(row, position, o);
            } catch (ReflectiveOperationException e) {
                throw new RowsWriteServiceException(e);
            }
        }));
    }
}
