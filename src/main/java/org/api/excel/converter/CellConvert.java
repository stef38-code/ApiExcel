package org.api.excel.converter;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
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

    public Object value(Class<?> type, Cell cell) {
        return ConvertUtils.convert(returnValue(cell), type);
    }

    private Object returnValue(Cell cell) {

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
                return StringUtils.EMPTY;
            case FORMULA:
                return cell.getCellFormula();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                throw new CellConvertException("Decoding value of the cell", cell);
        }
    }

    public String returnStringValue(Cell cell) {
        Debug.print(this, "stringValue -> type de cellule {0} value {1}", cell.getCellType(), cell);
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
