package org.api.excel.model.commun;

import org.api.excel.core.annotations.Book;
import org.api.excel.core.annotations.Page;
import org.api.excel.core.utils.Conditions;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;

/**
 * The type Sheet model.
 */
public class BookModel {

    private final Book annotationBook;
    private final List<CellModel> cellModels;

    private BookModel(Builder builder) {
        Objects.requireNonNull(builder.annotationSheets, "the annotation cannot is null");
        Conditions.requireNotEmpty(builder.annotationSheets.value(), "the annotation cannot is null");
        Conditions.requireNotEmpty(builder.cellModels);

        this.annotationBook = builder.annotationSheets;
        this.cellModels = builder.cellModels;
    }

    /**
     * Builder builder.
     *
     * @return the builder
     */
    public static Builder annotationSheets(Book annotationSheets) {
        return new Builder().annotationSheets(annotationSheets);
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

    /**
     * The type Builder.
     */
    public static final class Builder {
        private Book annotationSheets;
        private List<CellModel> cellModels;


        private Builder() {

        }

        /**
         * Sheet annotation builder.
         *
         * @param pageAnnotation the sheet annotation
         * @return the builder
         */
        public Builder sheetAnnotation(Page pageAnnotation) {
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

        public Builder annotationSheets(Book annotationSheets) {

            this.annotationSheets = annotationSheets;
            return this;
        }

        /**
         * Cell models builder.
         *
         * @param cellModels the cell models
         * @return the builder
         */
        public Builder cellModels(List<CellModel> cellModels) {
            this.cellModels = cellModels;
            return this;
        }

        /**
         * Build sheet model.
         *
         * @return the sheet model
         */
        public BookModel build() {

            return new BookModel(this);
        }
    }

}