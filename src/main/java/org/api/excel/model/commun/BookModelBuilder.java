package org.api.excel.model.commun;

import org.api.excel.core.annotations.Book;
import org.api.excel.core.annotations.Page;
import org.api.excel.core.utils.Conditions;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;

class BookModelBuilder implements BookModelFluent, BookModelFluent.BookModelCellModels, BookModelFluent.BookModelCreate {
    private Book annotationSheets;
    private List<CellModel> cellModels;

    @Override
    public BookModelBuilder page(Page pageAnnotation) {
        if (Objects.nonNull(pageAnnotation)) {
            this.annotationSheets = new Book() {

                @Override
                public Class<? extends Annotation> annotationType() {
                    return Book.class;
                }

                @Override
                public Page[] value() {
                    return new Page[]{pageAnnotation};
                }
            };
        }
        return this;
    }

    @Override
    public BookModelBuilder book(Book annotationSheets) {
        this.annotationSheets = annotationSheets;
        return this;
    }

    @Override
    public BookModelBuilder cellModels(List<CellModel> cellModels) {
        this.cellModels = cellModels;
        return this;
    }

    @Override
    public BookModel create() {
        Objects.requireNonNull(this.annotationSheets, "the annotation cannot is null");
        Conditions.requireNotEmpty(this.annotationSheets.value(), "the annotation cannot is null");
        Conditions.requireNotEmpty(this.cellModels);
        return new BookModel(this.annotationSheets, this.cellModels);
    }
}
