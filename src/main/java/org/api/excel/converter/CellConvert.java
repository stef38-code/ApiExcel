package org.api.excel.converter;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.api.excel.annotations.Box;
import org.api.excel.exception.CellConvertException;
import org.api.excel.utils.Debug;

import java.util.Objects;

public class CellConvert {

    private static CellConvert instance = null;

    private CellConvert() {
    }

    public static CellConvert getInstance() {
        if (Objects.isNull(instance)) {
            instance = new CellConvert();
        }

        return instance;
    }

    public Object value(Box annotation, org.apache.poi.ss.usermodel.Cell cell) {
        Debug.print(this.getClass(), () -> "cell column index {0} ", cell.getColumnIndex());
        if (annotation.stringFormat()) {
            return returnStringValue(cell);
        }
        return returnValue(cell);
    }

    private Object returnValue(org.apache.poi.ss.usermodel.Cell cell) {

        CellType cellType = cell.getCellType();
        switch (cellType) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case STRING:
                return cell.getStringCellValue();
            case ERROR:
                return String.valueOf(cell.getErrorCellValue());
            case BLANK:
                return "";
            case FORMULA:
                return cell.getCellFormula();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                throw new CellConvertException("Decoding value of the cell", cell);
        }
    }

    public String returnStringValue(org.apache.poi.ss.usermodel.Cell cell) {
        Debug.print(this.getClass(), () -> "stringValue -> type de cellule {0} value {1}", cell.getCellType(), cell);
        CellType cellType = cell.getCellType();

        switch (cellType) {
            case NUMERIC:
                double doubleVal = cell.getNumericCellValue();
                return String.valueOf(doubleVal);
            case STRING:
                return cell.getStringCellValue();
            case ERROR:
                return String.valueOf(cell.getErrorCellValue());
            case BLANK:
                return "";
            case FORMULA:
                return cell.getCellFormula();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                throw new CellConvertException("decoding string value of the cell", cell);
        }
    }
}
