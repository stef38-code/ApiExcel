package org.api.excel.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.api.excel.annotations.Book;
import org.api.excel.annotations.Page;
import org.api.excel.converter.RowConverter;
import org.api.excel.exception.ExcelException;
import org.api.excel.exception.WorkbookServiceException;
import org.api.excel.file.Excel;
import org.api.excel.model.CellModel;
import org.api.excel.model.SheetModel;
import org.api.excel.utils.Conditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class WorkbookService<T> {
    private static final Logger log = LoggerFactory.getLogger(WorkbookService.class);
    private final RowsService rowsService;
    private final RowConverter rowConverter;
    private final CellService cellService;

    public WorkbookService() {
        this.rowsService = new RowsService();
        this.cellService = new CellService();
        this.rowConverter = RowConverter.getInstance();
    }

    public void execute(SheetModel sheetModel, String file, List<T> list, Class<T> aClass) {
        log.info("Traitement: {}", file);
        try (org.apache.poi.ss.usermodel.Workbook workbook = Excel.read(file)) {
            log.info("-> Workbook");
            Book annotationSheets = sheetModel.getAnnotationSheets();
            List<CellModel> cellModels = sheetModel.getCellModels();
            Arrays.stream(annotationSheets.value()).forEach(annotationSheet -> forRowsInSheet(list, aClass, annotationSheet, cellModels, workbook));
            Excel.close(workbook);
        } catch (ExcelException | IOException e) {
            throw new WorkbookServiceException(e);
        }
    }

    private void forRowsInSheet(List<T> list, Class<T> aClass, Page annotationPage, List<CellModel> cellModels, Workbook workbook) {

        Sheet sheet = Excel.getSheetSelected(annotationPage, workbook);
        log.info("--> Sheet Name: {}", sheet.getSheetName());
        log.info("---> Rows");
        //Extraction de la premiere ligne pour trouver l'index des colonnes en fonction du nom
        Optional<Row> headerRow = rowsService.getRowsHeader(sheet.rowIterator(), annotationPage.rowNumber());
        Conditions.requireNonNull(headerRow, "Row header cannot be null");
        Row row = headerRow.get();
        cellModels.forEach(
                cellModel -> {
                    //Recherche si une colonne contient le nom définit
                    Iterator<Cell> cellIterator = row.cellIterator();
                    cellService.findPosition(cellModel, cellIterator);
                }
        );
        //Remplace la liste par une nouvelle qui ne contient en plus la position de la colonne en plus de sont nom

        List<Row> rows = rowsService.extractDataRows(sheet.rowIterator(), annotationPage.rowNumber());
        readDataRows(rows, aClass, cellModels, list);
    }

    private void readDataRows(List<Row> rows, Class<T> tClass, List<CellModel> cellModels, List<T> list) {
        log.info("---> Read rows and convert to class {} ", tClass.getName());
        rows.forEach(
                row -> list.add(rowConverter.toClass(row, tClass, cellModels))
        );
    }

}
