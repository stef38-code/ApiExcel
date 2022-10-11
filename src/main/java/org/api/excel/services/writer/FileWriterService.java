package org.api.excel.services.writer;

import org.apache.poi.ss.usermodel.Workbook;
import org.api.excel.core.file.Excel;
import org.api.excel.mapping.ModelMapper;
import org.api.excel.model.commun.BookModel;
import org.api.excel.core.utils.Conditions;

import java.util.List;

public class FileWriterService<T> {
    private final WorkbookWriterService<T> workbookWriterService;
    public FileWriterService() {
        CellWriteService<T> cellWriteService = new CellWriteServiceByPoi<>();
        RowsWriteService<T> rowsWriterService = new RowsWriteServiceByPoi<>(cellWriteService);
        SheetWriterService<T> sheetWriterService = new SheetWriterServiceByPoi<>(rowsWriterService);
        workbookWriterService = new WorkbookWriterServiceByPoi<>(sheetWriterService);
    }


    public void execute(String excelFilePath, Class<T> tClass, List<T> entities) {
        Conditions.requireNotEmpty(excelFilePath);
        Conditions.requireNotEmpty(entities);

        Workbook workbook = Excel.getWorkbook(excelFilePath);

        //

        //extraction des informations de la classe
        ModelMapper mapper = ModelMapper.getInstance();
        BookModel bookModel = mapper.to(tClass);

        workbookWriterService.execute(workbook,bookModel,entities);


        //Nom Sheet

        Excel.write(excelFilePath, workbook);
    }

}
