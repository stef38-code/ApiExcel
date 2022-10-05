package org.api.excel.services.reader;

import org.api.excel.model.commun.BookModel;

import java.util.List;
import java.util.Optional;

public interface WorkbookReaderService<T> {
    Optional<List<T>> execute(BookModel bookModel, String file, Class<T> aClass);
}
