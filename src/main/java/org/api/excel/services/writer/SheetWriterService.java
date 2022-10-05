package org.api.excel.services.writer;

import org.apache.poi.ss.usermodel.Workbook;
import org.api.excel.core.annotations.Book;
import org.api.excel.model.commun.CellModel;

import java.util.List;

public interface SheetWriterService<T> {
    void execute(Workbook workbook, Book annotationSheets, List<CellModel> cellModels, List<T> entities);
}
