package org.api.excel.parser.writer;

import org.api.excel.services.writer.FileWriterService;

import java.util.List;

public final class WriteExcelByPoi<T> implements WriteExcel<T> {
    private final FileWriterService<T> fileWriterService;

    private String excelFilePath;
    private List<T> entities;
    private Class<T> tClass;

    public WriteExcelByPoi() {
        fileWriterService = new FileWriterService<>();
    }

    @Override
    public WriteExcelByPoi<T> file(String excelFilePath) {
        this.excelFilePath = excelFilePath;
        return this;
    }

    @Override
    public WriteExcel<T> entities(List<T> entities) {
        this.entities = entities;
        return this;
    }


    @Override
    public WriteExcel<T> clazz(Class<T> tClass) {
        this.tClass = tClass;
        return this;
    }

    @Override
    public void build() {

        fileWriterService.execute(excelFilePath,this.tClass,entities);

    }



}
