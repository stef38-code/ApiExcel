package org.api.excel.services.reader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.api.excel.model.commun.CellModel;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface CellReadService {
    CellModel findPosition(CellModel cellModel, Iterator<Cell> cells);

    List<CellModel> getCellModelCorrecte(List<CellModel> cellModels, Optional<Row> headerRowOptional);
}
