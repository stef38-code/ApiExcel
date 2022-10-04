package org.api.excel.parser.builder;

import org.api.excel.converter.RowConverter;
import org.api.excel.services.CellService;
import org.api.excel.services.FileService;
import org.api.excel.services.RowsService;
import org.api.excel.services.WorkbookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class ReaderExcel<T> {
    private final FileService<T> fileService;
    private final WorkbookService<T> workbookService;
    private final List<String> files;
    private final RowsService rowsService;
    private final RowConverter rowConverter;
    private final CellService cellService;
    private Class<T> tClass;

    public ReaderExcel() {
        this.rowsService = new RowsService();
        this.rowConverter = RowConverter.getInstance();
        this.cellService = new CellService();
        this.workbookService = new WorkbookService<>(rowsService, rowConverter, cellService);
        this.fileService = new FileService<>(workbookService);
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
