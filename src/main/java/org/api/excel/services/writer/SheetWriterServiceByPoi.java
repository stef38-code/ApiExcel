package org.api.excel.services.writer;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.api.excel.core.annotations.Book;
import org.api.excel.model.SheetWriterModel;
import org.api.excel.model.commun.CellModel;

import java.util.List;

public class SheetWriterServiceByPoi<T> implements SheetWriterService<T> {
    private final RowsWriteService<T> rowsWriteService;

    public SheetWriterServiceByPoi(RowsWriteService<T> rowsWriteService) {
        this.rowsWriteService = rowsWriteService;
    }

    @Override
    public void execute(Workbook workbook, Book annotationSheets, List<CellModel> cellModels, List<T> entities) {
        SheetWriterModel sheetWriter = SheetWriterModel.aNew().
                pages(annotationSheets.value())
                .create();
        Sheet sheet = workbook.createSheet(sheetWriter.getName());
        rowsWriteService.execute(sheetWriter, sheet, cellModels, entities);
        //Definition du header
    }
}
