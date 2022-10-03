package org.api.excel.services;

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
import org.api.excel.utils.Info;
import org.api.excel.utils.Return;

import java.io.IOException;
import java.util.*;

public class WorkbookService<T> {
    private final RowsService rowsService;
    private final RowConverter rowConverter;
    private final CellService cellService;

    public WorkbookService() {
        this.rowsService = new RowsService();
        this.cellService = new CellService();
        this.rowConverter = RowConverter.getInstance();
    }

    public Optional<List<T>> execute(SheetModel sheetModel, String file, Class<T> aClass) {
        Info.print(this, "Traitement: {0}", file);
        List<T> list2 = new ArrayList<>();
        try (org.apache.poi.ss.usermodel.Workbook workbook = Excel.read(file)) {
            Info.print(this, "-> Workbook");
            Book annotationSheets = sheetModel.getAnnotationSheets();
            List<CellModel> cellModels = sheetModel.getCellModels();
            Arrays.stream(annotationSheets.value()).forEach(annotationSheet -> {
                Optional<List<T>> rowsInSheet = forRowsInSheet(aClass, annotationSheet, cellModels, workbook);
                rowsInSheet.ifPresentOrElse(list2::addAll, () -> Info.print(this, "No Data"));
            });
            Excel.close(workbook);
        } catch (ExcelException | IOException e) {
            throw new WorkbookServiceException(e);
        }

        return Return.byDefaultOptionalEmpty(list2);
    }

    private Optional<List<T>> forRowsInSheet(Class<T> aClass, Page annotationPage, List<CellModel> cellModels, Workbook workbook) {

        Sheet sheet = Excel.getSheetSelected(annotationPage, workbook);
        Conditions.requireSheetIsNotEmpty(sheet);
        Info.print(this, "--> Sheet Name: {0}", sheet.getSheetName());
        Info.print(this, "---> Rows");
        //Extraction de la premiere ligne pour trouver l'index des colonnes en fonction du nom
        Optional<Row> headerRow = rowsService.getRowsHeader(sheet.rowIterator(), annotationPage.rowNumber());
        Conditions.requireNonNull(headerRow, "Row header cannot be null");
        return headerRow.map(
                row -> {
                    List<CellModel> cellModelCorrecte = getCellModelCorrecte(cellModels, row);
                    //Remplace la liste par une nouvelle qui ne contient en plus la position de la colonne en plus de sont nom
                    return readDataRows(sheet.rowIterator(), annotationPage.rowNumber(), aClass, cellModelCorrecte);
                }
        ).orElse(Optional.empty());
    }

    private List<CellModel> getCellModelCorrecte(List<CellModel> cellModels, Row row) {
        List<CellModel> cellModelCorrecte = new ArrayList<>();
        cellModels.forEach(
                cellModel ->
                    //Recherche si une colonne contient le nom définit
                    cellModelCorrecte.add(cellService.findPosition(cellModel, row.cellIterator()))

        );
        return cellModelCorrecte;
    }

    private Optional<List<T>> readDataRows(Iterator<Row> rowIterator, int rowNumber, Class<T> tClass, List<CellModel> cellModels) {
        List<Row> rows = rowsService.extractDataRows(rowIterator,rowNumber );
        Info.print(this, "---> Read rows and convert to class {0} ", tClass.getName());
        List<T> values = new ArrayList<>();
        rows.forEach(
                row ->
                      values.add(rowConverter.toClass(row, tClass, cellModels))

        );
       return Return.byDefaultOptionalEmpty(values);
    }

}
