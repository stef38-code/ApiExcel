package org.api.excel.parser.builder;

import org.api.excel.services.FileService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class ReaderExcel<T> {
    private final FileService<T> fileService = new FileService<>();
    private final List<String> files;
    private Class<T> tClass;

    public ReaderExcel() {
        this.files = new ArrayList<>();
    }

    /**
     * Clazz builder.
     *
     * @param obj the obj
     * @return the builder
     */
    public ReaderExcel<T> clazz(Class<T> obj) {
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
    public ReaderExcel<T> file(String excelFile) {
        files.add(excelFile);
        return this;
    }
}
