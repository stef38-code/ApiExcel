package org.api.excel.parser;


import org.api.excel.parser.reader.ReaderExcel;
import org.api.excel.parser.reader.ReaderExcelByPoi;
import org.api.excel.parser.writer.WriteExcel;
import org.api.excel.parser.writer.WriteExcelByPoi;

/**
 * Classe permettant de convertir un fichier Xls ou Xlsx en une liste d'une classe
 * *
 */
public class ParseExcel {
    private ParseExcel() {
        throw new UnsupportedOperationException("ParseExcel is a utility class and cannot be instantiated");
    }

    /**
     * Clazz builder.
     *
     * @param <T>   the type parameter
     * @param clazz the clazz
     * @return the builder
     */
    public static <T> ReaderExcel<T> read(Class<T> clazz) {
        return new ReaderExcelByPoi<T>().clazz(clazz);
    }

    public static <T> WriteExcel<T> write(Class<T> clazz) {
        return new WriteExcelByPoi<T>().clazz(clazz);
    }
}
