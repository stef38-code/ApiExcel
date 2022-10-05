package org.api.excel.services.reader;

import org.apache.poi.ss.usermodel.Sheet;
import org.api.excel.core.annotations.Page;
import org.api.excel.model.commun.CellModel;

import java.util.List;
import java.util.Optional;

public interface RowsReadService<T> {
    Optional<List<T>> execute(Class<T> aClass, Page annotationPage, Sheet sheet, List<CellModel> cellModels);
}
