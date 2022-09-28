package org.api.excel.services;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.api.excel.annotations.ExcelSheet;
import org.api.excel.converter.RowConverter;
import org.api.excel.exception.ExcelException;
import org.api.excel.exception.WorkbookServiceException;
import org.api.excel.file.Excel;
import org.api.excel.model.CellModel;
import org.api.excel.model.SheetModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class WorkbookService<T> {
    private static final Logger log = LoggerFactory.getLogger(WorkbookService.class);
    private final RowsService rowsService;//= new RowsService();
    private final RowConverter rowConverter;//= RowConverter.getInstance();

    public WorkbookService() {
        this.rowsService = new RowsService();
        rowConverter = RowConverter.getInstance();
    }

    public void execute(SheetModel sheetModel, String file, List<T> list, Class<T> aClass) {
        log.info("Traitement: {}", file);
        try (Workbook workbook = Excel.read(file)) {
            log.info("-> Workbook");
            ExcelSheet sheetAnnotation = sheetModel.getSheetAnnotation();
            List<CellModel> cellModels = sheetModel.getCellModels();
            Sheet sheet = Excel.getSheetSelected(sheetAnnotation, workbook);
            log.info("--> Sheet Name: {}", sheet.getSheetName());
            log.info("---> Rows");
            List<Row> rows = rowsService.extractDataRows(sheet.rowIterator(), sheetAnnotation.rowNumber());
            readDataRows(rows, aClass, cellModels, list);
            Excel.close(workbook);
        } catch (ExcelException | IOException e) {
            throw new WorkbookServiceException(e);
        }
    }

    private void readDataRows(List<Row> rows, Class<T> tClass, List<CellModel> cellModels, List<T> list) {
        log.info("---> Read rows and convert to class {} ", tClass.getName());
        rows.forEach(
                row -> list.add(rowConverter.toClass(row, tClass, cellModels))
        );
    }

}
