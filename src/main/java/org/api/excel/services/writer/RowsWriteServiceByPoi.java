package org.api.excel.services.writer;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.api.excel.core.utils.Debug;
import org.api.excel.core.utils.Info;
import org.api.excel.model.RowHeaderWriteModel;
import org.api.excel.model.SheetWriterModel;
import org.api.excel.model.commun.CellModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RowsWriteServiceByPoi<T> implements RowsWriteService<T> {
    private final CellWriteService<T> cellWriteService;

    public RowsWriteServiceByPoi(CellWriteService<T> cellWriteService) {
        this.cellWriteService = cellWriteService;
    }


    @Override
    public void execute(SheetWriterModel sheetWriter, Sheet sheet, List<CellModel> cellModels, List<T> entities) {

        RowHeaderWriteModel rowHeaderWriteModel = RowHeaderWriteModel.aNew().cells(cellModels).create();
        int rowNumberHeader = sheetWriter.getRowNumber() - 1;
        Info.print(this, "Sheet name : {0}, header number {1}", sheetWriter.getName(), rowNumberHeader);
        //add header + cell
        addRowHeader(rowHeaderWriteModel, sheet, rowNumberHeader);
        Info.print(this, "row header {0}", sheet.getLastRowNum());
        //add data
        addData(entities, cellModels, rowHeaderWriteModel, rowNumberHeader, sheet);
    }

    private void addRowHeader(RowHeaderWriteModel rowHeaderWriteModel, Sheet sheet, int rowNumberHeader) {
        Row headerRow = sheet.createRow(rowNumberHeader);
        rowHeaderWriteModel.getHeader().forEach((index, v) -> {
            Info.print(this, "column header {0} value {1}", index, v.getColumnName());
            cellWriteService.execute(headerRow, index, v.getColumnName());
        });
    }

    private void addData(List<T> entities, List<CellModel> cellModels, RowHeaderWriteModel rowHeaderWriteModel, int rowNumberHeader, Sheet sheet) {
        int size = entities.size();
        List<Row> rows = new ArrayList<>();
        for (int j = 0; j < entities.size(); j++) {
            rows.add(sheet.createRow(rowNumberHeader + j + 1));
        }
        AtomicInteger rowCount = new AtomicInteger();
        entities.forEach(entitie -> {
            int index = rowCount.getAndIncrement();
            Debug.print(this, "row {0}/{1} value {2}", index, size, entitie);
            cellWriteService.execute(cellModels, rowHeaderWriteModel, rows.get(index), entitie);
        });

    }
}
