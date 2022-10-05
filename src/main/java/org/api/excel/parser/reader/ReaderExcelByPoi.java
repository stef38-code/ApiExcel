package org.api.excel.parser.reader;

import org.api.excel.services.reader.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@SuppressWarnings("java:S3740")
public final class ReaderExcelByPoi<T> implements ReaderExcel<T> {
    private final FileReaderService<T> fileReaderService;
    private final List<String> files;

    private Class<T> tClass;

    public ReaderExcelByPoi() {
        CellReadService cellReadService = new CellReadServiceByPoi();
        RowsReadService<T> rowsReadService = new RowsReadServiceByPoi(cellReadService);
        SheetReaderService<T> sheetReaderService = new SheetReaderService(rowsReadService);
        WorkbookReaderService<T> workbookReaderService = new WorkbookReaderServiceByPoi(sheetReaderService);
        this.fileReaderService = new FileReaderService<>(workbookReaderService);
        this.files = new ArrayList<>();
    }

    /**
     * Clazz builder.
     *
     * @param obj the obj
     * @return the builder
     */
    @Override
    public ReaderExcel<T> clazz(Class<T> obj) {
        this.tClass = obj;
        return this;
    }

    /**
     * Build optional.
     *
     * @return the optional
     */
    @Override
    public Optional<List<T>> build() {
        return fileReaderService.execute(tClass, files);
    }

    /**
     * File builder.
     *
     * @param excelFile the excel file
     * @return the builder
     */
    @Override
    public ReaderExcel<T> file(String excelFile) {
        files.add(excelFile);
        return this;
    }
}
