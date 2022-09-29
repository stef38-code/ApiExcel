package org.api.excel.model;

import org.api.excel.annotations.ExcelSheet;
import org.api.excel.annotations.ExcelSheets;
import org.api.excel.utils.Conditions;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;

/**
 * The type Sheet model.
 */
public class SheetModel {

    private final ExcelSheets annotationSheets;
    private final List<CellModel> cellModels;

    private SheetModel(Builder builder) {
        Objects.requireNonNull(builder.annotationSheets, "the annotation cannot is null");
        Conditions.requireNotEmpty(builder.annotationSheets.value(), "the annotation cannot is null");
        Conditions.requireNotEmpty(builder.cellModels, "the Collection cannot be empty");

        this.annotationSheets = builder.annotationSheets;
        this.cellModels = builder.cellModels;
    }

    /**
     * Builder builder.
     *
     * @return the builder
     */
    public static Builder annotationSheets(ExcelSheets annotationSheets) {
        return new Builder().annotationSheets(annotationSheets);
    }

    public ExcelSheets getAnnotationSheets() {
        return annotationSheets;
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
        private ExcelSheets annotationSheets;
        private List<CellModel> cellModels;


        private Builder() {

        }

        /**
         * Sheet annotation builder.
         *
         * @param sheetAnnotation the sheet annotation
         * @return the builder
         */
        public Builder sheetAnnotation(ExcelSheet sheetAnnotation) {
            if(Objects.nonNull(sheetAnnotation)) {
                this.annotationSheets = new ExcelSheets() {

                    @Override
                    public Class<? extends Annotation> annotationType() {
                        return ExcelSheets.class;
                    }

                    @Override
                    public ExcelSheet[] value() {
                        return new ExcelSheet[]{sheetAnnotation};
                    }
                };
            }
            return this;
        }

        public Builder annotationSheets(ExcelSheets annotationSheets) {

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
        public SheetModel build() {

            return new SheetModel(this);
        }
    }

}
