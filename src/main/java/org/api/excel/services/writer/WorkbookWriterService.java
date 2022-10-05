package org.api.excel.services.writer;

import org.apache.poi.ss.usermodel.Workbook;
import org.api.excel.model.commun.BookModel;

import java.util.List;

public interface WorkbookWriterService<T> {
       <T> void execute(Workbook workbook, BookModel bookModel, List<T> entities);
}
