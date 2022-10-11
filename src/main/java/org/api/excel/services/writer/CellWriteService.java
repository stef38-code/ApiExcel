package org.api.excel.services.writer;

import org.apache.poi.ss.usermodel.Row;
import org.api.excel.model.RowHeaderWriteModel;
import org.api.excel.model.commun.CellModel;

import java.util.List;

public interface CellWriteService<T> {
    void execute(Row headerRow, Integer index, Object value);

    void execute(List<CellModel> cellModels, RowHeaderWriteModel rowHeaderWriteModel, Row row, T entitie);
}
