package org.api.excel.parser;


import org.api.excel.services.FileService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public static <T> Builder<T> clazz(Class<T> clazz) {
        return new Builder<T>().clazz(clazz);
    }

    /**
     * The type Builder.
     *
     * @param <T> the type parameter
     */
    public static final class Builder<T> {
        private final FileService<T> fileService = new FileService<>();
        private final List<String> files;
        private Class<T> tClass;

        private Builder() {
            this.files = new ArrayList<>();
        }

        /**
         * Clazz builder.
         *
         * @param obj the obj
         * @return the builder
         */
        public Builder<T> clazz(Class<T> obj) {
            this.tClass = obj;
            return this;
        }

        /**
         * Build optional.
         *
         * @return the optional
         */
        public Optional<List<T>> build() {
            return fileService.execute(tClass, files);
        }

        /**
         * File builder.
         *
         * @param excelFile the excel file
         * @return the builder
         */
        public Builder<T> file(String excelFile) {
            files.add(excelFile);

            return this;
        }
    }
}
