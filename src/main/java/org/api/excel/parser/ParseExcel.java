package org.api.excel.parser;


import org.api.excel.parser.builder.ReaderExcel;

/**
 * Classe permettant de convertir un fichier Xls ou Xlsx en une liste d'une classe
 * *
 *
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
        return new ReaderExcel<T>().clazz(clazz);
    }

}
