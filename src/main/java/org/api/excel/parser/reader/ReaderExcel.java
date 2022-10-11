package org.api.excel.parser.reader;

import java.util.List;
import java.util.Optional;

public interface ReaderExcel<T> {
    ReaderExcel<T> clazz(Class<T> obj);

    Optional<List<T>> build();

    ReaderExcel<T> file(String excelFile);
}
