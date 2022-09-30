package org.api.excel.exception;

import org.apache.poi.ss.usermodel.Cell;

public class CellConvertException extends RuntimeException {
    private final Cell cell;
    private final String msg;

    public CellConvertException(String msg, Cell cell) {
        super();
        this.cell = cell;
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg + " cell: " + cell.getRowIndex() +
                " row: " + cell.getRow().getRowNum() +
                " sheet: " + cell.getSheet().getSheetName();
    }
}
