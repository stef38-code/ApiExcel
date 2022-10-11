package org.api.excel.services.writer;

import org.apache.poi.ss.usermodel.Sheet;
import org.api.excel.model.SheetWriterModel;
import org.api.excel.model.commun.CellModel;

import java.util.List;

public interface RowsWriteService<T> {

    void execute(SheetWriterModel sheetWriter, Sheet sheet, List<CellModel> cellModels, List<T> entities);
}
