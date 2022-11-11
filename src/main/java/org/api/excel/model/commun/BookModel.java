package org.api.excel.model.commun;

import org.api.excel.core.annotations.Book;

import java.util.List;

/**
 * The type Sheet model.
 */
public class BookModel {

    private final Book annotationBook;
    private final List<CellModel> cellModels;

    BookModel(Book annotationBook, List<CellModel> cellModels) {

        this.annotationBook = annotationBook;
        this.cellModels = cellModels;
    }

    public static BookModelFluent aNew() {
        return new BookModelBuilder();
    }

    public Book getAnnotationBook() {
        return annotationBook;
    }

    /**
     * Gets cell models.
     *
     * @return the cell models
     */
    public List<CellModel> getCellModels() {
        return cellModels;
    }


}
