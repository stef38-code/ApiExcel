package org.api.excel.services.reader;

import org.apache.poi.ss.usermodel.Workbook;
import org.api.excel.core.file.Excel;
import org.api.excel.core.file.ExcelException;
import org.api.excel.core.utils.Info;
import org.api.excel.core.utils.Return;
import org.api.excel.model.commun.BookModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WorkbookReaderServiceByPoi<T> implements WorkbookReaderService<T> {


    private final SheetReaderService<T> sheetReaderService;


    public WorkbookReaderServiceByPoi(SheetReaderService<T> sheetReaderService) {
        this.sheetReaderService = sheetReaderService;
    }

    @Override
    public Optional<List<T>> execute(BookModel bookModel, String file, Class<T> aClass) {
        Info.print(this, "------------------------execute-------------------------------");
        Info.print(this, "file: {0}", file);
        List<T> list2 = new ArrayList<>();
        try (Workbook workbook = Excel.read(file)) {
            Optional<List<T>> optional = sheetReaderService.execute(workbook, bookModel, aClass);
            optional.ifPresentOrElse(list2::addAll, () -> Info.print(this, "No Data"));
            Excel.close(workbook);
        } catch (ExcelException | IOException e) {
            throw new WorkbookServiceException(e);
        }

        return Return.byDefaultOptionalEmpty(list2);
    }


}
