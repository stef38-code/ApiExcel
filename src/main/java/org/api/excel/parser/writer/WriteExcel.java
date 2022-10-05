package org.api.excel.parser.writer;

import java.util.List;

public interface WriteExcel<T> {
    WriteExcelByPoi<T> file(String excelFilePath);

    WriteExcel<T> entities(List<T> entities);

    WriteExcel<T> clazz(Class<T> tClass);

    void build();
}
