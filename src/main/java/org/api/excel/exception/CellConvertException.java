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
        StringBuilder message = new StringBuilder(msg);
        message.append(" cell: ").append(cell.getRowIndex());
        message.append(" row: ").append(cell.getRow().getRowNum());
        message.append(" sheet: ").append(cell.getSheet().getSheetName());
        return message.toString();
    }
}
