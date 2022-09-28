package org.api.excel.converter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.api.excel.annotations.ExcelCell;
import org.api.excel.exception.CellConvertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class CellConvert {
    private static final Logger log = LoggerFactory.getLogger(CellConvert.class);
    private static CellConvert instance = null;

    private CellConvert() {
    }

    public static CellConvert getInstance() {
        if (Objects.isNull(instance)) {
            instance = new CellConvert();
        }

        return instance;
    }

    public Object value(ExcelCell annotation, Cell cell) {
        if (annotation.stringFormat()) {
            return returnStringValue(cell);
        }
        return returnValue(cell);
    }

    private Object returnValue(Cell cell) {
        log.debug("ObjectValue -> type de cellule {} ", cell.getCellType());
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

    public String returnStringValue(Cell cell) {
        log.debug("stringValue -> type de cellule {} value {}", cell.getCellType(), cell);
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
