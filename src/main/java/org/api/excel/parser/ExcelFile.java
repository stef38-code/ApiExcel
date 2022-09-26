package org.api.excel.parser;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.api.excel.annotations.ExcelSheet;
import org.api.excel.converter.RowConverter;
import org.api.excel.file.Excel;
import org.api.excel.file.ExcelException;
import org.api.excel.model.CellModel;
import org.api.excel.model.SheetModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ExcelFile<T> {
    private static final Logger log = LoggerFactory.getLogger(ExcelFile.class);
    private final ParseExcel.Builder<T> builder;

    public ExcelFile(ParseExcel.Builder<T> builder) {
        this.builder = builder;
    }

    public void execute(SheetModel sheetModel, String file, List<T> list, Class<T> aClass) {
        log.info("Traitement: {}", file);
        try (Workbook workbook = Excel.read(file)) {
            log.info("Workbook");
            ExcelSheet sheetAnnotation = sheetModel.getSheetAnnotation();
            List<CellModel> cellModels = sheetModel.getCellModels();
            Sheet sheet = Excel.getSheetSelected(sheetAnnotation, workbook);
            log.info("sheet");
            log.info("row");
            List<Row> rows = extractRows(sheet.rowIterator(), sheetAnnotation.rowNumber());
            readRows(rows, aClass, cellModels, list);
            Excel.close(workbook);
        } catch (ExcelException | IOException e) {
            throw new ParseExcelException(e);
        }
    }

    private List<Row> extractRows(Iterator<Row> rows, int rowNumber) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(rows, 0),
                false).filter(row -> row.getRowNum() > (rowNumber - 1)).collect(Collectors.toList());
    }

    private void readRows(List<Row> rows, Class<T> tClass, List<CellModel> cellModels, List<T> list) {
        rows.forEach(
                row -> list.add(rowToEntity(row, tClass, cellModels))
        );
    }

    private T rowToEntity(Row row, Class<T> tClass, List<CellModel> cellModels) {
        log.debug("add new Entity with number row {}", row.getRowNum());
        RowConverter rowConverter = RowConverter.getInstance();
        return rowConverter.toClass(tClass, cellModels, row);
    }
}
