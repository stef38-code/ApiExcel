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

    private BookModel(Book annotationBook, List<CellModel> cellModels) {

        this.annotationBook = annotationBook;
        this.cellModels = cellModels;
    }

    public static StepAnnotationSheets aNew() {
        return new StepBookModelBuilder();
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

    public interface StepAnnotationSheets {
        StepCellModels pageAnnotationSheet(Page pageAnnotation);

        StepCellModels bookAnnotationSheet(Book annotationSheets);
    }

    public interface StepCellModels {
        StepBuilder cellModels(List<CellModel> cellModels);
    }

    public interface StepBuilder {
        BookModel create();
    }

    private static class StepBookModelBuilder implements StepAnnotationSheets, StepCellModels, StepBuilder {
        private Book annotationSheets;
        private List<CellModel> cellModels;

        @Override
        public StepBookModelBuilder pageAnnotationSheet(Page pageAnnotation) {
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
        public StepBookModelBuilder bookAnnotationSheet(Book annotationSheets) {
            this.annotationSheets = annotationSheets;
            return this;
        }

        @Override
        public StepBookModelBuilder cellModels(List<CellModel> cellModels) {
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


}
