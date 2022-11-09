package org.api.excel.model.commun;

import org.api.excel.core.annotations.Book;
import org.api.excel.core.annotations.Page;

import java.util.List;

public interface BookModelFluent {
    BookModelCellModels page(Page pageAnnotation);

    BookModelCellModels book(Book annotationSheets);

    interface BookModelCellModels {
        BookModelCreate cellModels(List<CellModel> cellModels);
    }

    interface BookModelCreate {
        BookModel create();
    }
}
