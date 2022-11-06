package org.api.excel.services.writer;

import org.apache.poi.ss.usermodel.Workbook;
import org.api.excel.core.annotations.Book;
import org.api.excel.model.commun.BookModel;
import org.api.excel.model.commun.CellModel;

import java.util.List;

public class WorkbookWriterServiceByPoi<T> implements WorkbookWriterService<T> {

    private final SheetWriterService<T> sheetWriterServiceByPoi;

    public WorkbookWriterServiceByPoi(SheetWriterService<T> sheetWriterServiceByPoi) {
        this.sheetWriterServiceByPoi = sheetWriterServiceByPoi;
    }

    @Override
    public void execute(Workbook workbook, BookModel bookModel, List<T> entities) {
        Book annotationSheets = bookModel.getAnnotationBook();
        List<CellModel> cellModels = bookModel.getCellModels();
        sheetWriterServiceByPoi.execute(workbook, annotationSheets, cellModels, entities);

    }

}
