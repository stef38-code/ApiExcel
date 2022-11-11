package org.api.excel.core.file;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.api.excel.core.annotations.Page;
import org.api.excel.core.utils.Conditions;
import org.api.excel.core.utils.Info;
import org.api.excel.services.writer.CellWriteServiceByPoi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class Excel {
    private Excel() {
        throw new UnsupportedOperationException("Excel is a utility class and cannot be instantiated");
    }

    public static Workbook read(String stringValue) throws ExcelException {
        File file = Conditions.requireFileAndExists(stringValue);
        try {
            return WorkbookFactory.create(file);
        } catch (IOException | EncryptedDocumentException e) {
            throw new ExcelException(e);
        }
    }

    public static Sheet getSheetSelected(Page page, Workbook workbook) {
        Objects.requireNonNull(page, "ExcelSheet cannot be null");

        if (page.name().isBlank())
            return workbook.getSheetAt(page.number());

        return workbook.getSheet(page.name());
    }

    public static void close(Workbook workbook) throws ExcelException {
        Objects.requireNonNull(workbook, "Workbook cannot be null");
        try {
            if (workbook instanceof SXSSFWorkbook) {
                ((SXSSFWorkbook) workbook).dispose();
            } else {
                workbook.close();
            }
        } catch (IOException e) {
            throw new ExcelException(e);
        }
    }

    public static Workbook getWorkbook(String excelFilePath) {


        if (excelFilePath.endsWith("xlsx")) {
            return new SXSSFWorkbook(1000);
        } else if (excelFilePath.endsWith("xls")) {
            return new HSSFWorkbook();
        }
        throw new IllegalArgumentException("The specified file is not Excel file");
    }

    public static void write(String excelFilePath, Workbook workbook) throws ExcelException {
        try (
                FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        } catch (IOException io) {
            throw new ExcelException(io);
        }
    }

    public static void setData(Cell cell, Object object) {
        if (object instanceof String) {
            Info.print(CellWriteServiceByPoi.class, "o{0} type: String", object);
            cell.setCellValue((String) object);
        } else if (object instanceof Integer) {
            Info.print(CellWriteServiceByPoi.class, "o{0} type: Integer", object);
            cell.setCellValue((Integer) object);
        } else if (object instanceof Double) {
            Info.print(CellWriteServiceByPoi.class, "o{0} type: Double", object);
            cell.setCellValue((Double) object);
        } else {
            Info.print(Excel.class, "Nada !!!");
        }


    }

}
