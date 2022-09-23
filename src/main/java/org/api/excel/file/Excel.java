package org.api.excel.file;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.api.excel.annotations.ExcelSheet;
import org.api.excel.utils.Conditions;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
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
    public static Sheet getSheetSelected(ExcelSheet excelSheet, Workbook workbook) {
        Objects.requireNonNull(excelSheet,"ExcelSheet cannot be null");

        if (excelSheet.name().isBlank())
            return workbook.getSheetAt(excelSheet.number());

        return workbook.getSheet(excelSheet.name());
    }

    public static void close(Workbook workbook) throws ExcelException {
        Objects.requireNonNull(workbook,"Workbook cannot be null");
        try {
            workbook.close();
        } catch (IOException e) {
            throw new ExcelException(e);
        }
    }
}
