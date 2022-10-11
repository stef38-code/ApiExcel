package org.api.excel.services.reader;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.api.excel.core.annotations.Book;
import org.api.excel.core.file.Excel;
import org.api.excel.core.utils.Info;
import org.api.excel.core.utils.Return;
import org.api.excel.model.commun.CellModel;
import org.api.excel.model.commun.BookModel;

import java.util.*;
import java.util.stream.Collectors;

public class SheetReaderService<T> {
    private final RowsReadService<T> rowsReadServiceByPoi;

    public SheetReaderService(RowsReadService<T> rowsReadServiceByPoi) {
        this.rowsReadServiceByPoi = rowsReadServiceByPoi;
    }

    public Optional<List<T>> execute(Workbook workbook, BookModel bookModel, Class<T> aClass) {
        Info.print(this, "------------------------execute-------------------------------");
        Book books = bookModel.getAnnotationBook();
        List<CellModel> cellModels = bookModel.getCellModels();

        List<T> list = Arrays.stream(books.value()).map(
                page -> {
                    Sheet sheet = Excel.getSheetSelected(page, workbook);
                    Optional<List<T>> rowsInSheet = rowsReadServiceByPoi.execute(aClass, page, sheet, cellModels);
                    return rowsInSheet.orElse(Collections.emptyList());
                }
        ).flatMap(Collection::stream).collect(Collectors.toList());
        return Return.byDefaultOptionalEmpty(list);
    }
}
