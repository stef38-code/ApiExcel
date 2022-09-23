package org.api.excel.file;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.api.excel.utils.Conditions;

import java.io.File;
import java.io.IOException;

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
}
