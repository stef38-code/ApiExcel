package org.api.excel.model;

import org.api.excel.annotations.ExcelSheet;
import org.api.excel.utils.Conditions;

import java.util.List;
import java.util.Objects;

/**
 * The type Sheet model.
 */
public class SheetModel {
    private final ExcelSheet sheetAnnotation;
    private final List<CellModel> cellModels;

    private SheetModel(Builder builder) {
        Objects.requireNonNull( builder.sheetAnnotation, "the annotation cannot is null");
        Conditions.requireNotEmpty( builder.cellModels, "the Collection cannot be empty");

        this.sheetAnnotation = builder.sheetAnnotation;
        this.cellModels = builder.cellModels;
    }

    /**
     * Gets sheet annotation.
     *
     * @return the sheet annotation
     */
    public ExcelSheet getSheetAnnotation() {
        return sheetAnnotation;
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
     * Builder builder.
     *
     * @return the builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * The type Builder.
     */
    public static final class Builder {
        private ExcelSheet sheetAnnotation;
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

            this.sheetAnnotation = sheetAnnotation;
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
