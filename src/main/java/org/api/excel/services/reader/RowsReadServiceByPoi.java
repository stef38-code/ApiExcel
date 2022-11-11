package org.api.excel.services.reader;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.api.excel.core.annotations.Page;
import org.api.excel.core.converter.RowConverter;
import org.api.excel.core.utils.Conditions;
import org.api.excel.core.utils.Info;
import org.api.excel.core.utils.Return;
import org.api.excel.model.commun.CellModel;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class RowsReadServiceByPoi<T> implements RowsReadService<T> {
    private final RowConverter rowConverter;
    private final CellReadService cellReadService;

    public RowsReadServiceByPoi(CellReadService cellReadService) {
        this.cellReadService = cellReadService;
        rowConverter = RowConverter.getInstance();
    }

    private static Stream<Row> getStream(Iterator<Row> rows) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(rows, 0),
                false);
    }

    private List<Row> extractDataRows(Iterator<Row> rows, int rowNumber) {
        Info.print(this, "Extract rows from row number {0}", rowNumber);
        return getStream(rows).filter(row -> row.getRowNum() > (rowNumber - 1)).collect(Collectors.toList());
    }

    private Optional<Row> getRowsHeader(Iterator<Row> rows, int rowNumber) {
        Info.print(this, "Extract rows header number {0}", rowNumber);
        return getStream(rows).filter(row -> row.getRowNum() == (rowNumber - 1)).findFirst();

    }

    public Optional<List<T>> execute(Class<T> aClass, Page annotationPage, Sheet sheet, List<CellModel> cellModels) {
        Info.print(this, "------------------------execute-------------------------------");
        //Entete
        Optional<Row> headerRow = getRowsHeader(sheet.rowIterator(), annotationPage.rowNumber());
        Conditions.requireNonNull(headerRow, "Sheet Is Empty");
        //Correction du CellModel
        List<CellModel> cellModelCorrecte = cellReadService.getCellModelCorrecte(cellModels, headerRow);
        //Traietement des datas
        return readDataRows(sheet, annotationPage, aClass, cellModelCorrecte);
    }

    private Optional<List<T>> readDataRows(Sheet sheet, Page annotationPage, Class<T> tClass, List<CellModel> cellModels) {
        List<Row> rows = extractDataRows(sheet.rowIterator(), annotationPage.rowNumber());
        List<T> values = rows.parallelStream().map(row -> rowConverter.toClass(row, tClass, cellModels)).collect(Collectors.toList());
        return Return.byDefaultOptionalEmpty(values);
    }
}
