package org.api.excel.model;

import org.api.excel.annotations.ExcelSheet;
import org.api.excel.utils.PreconditionsUtils;

import java.util.List;
import java.util.Objects;

public class SheetModel {
    private final ExcelSheet sheetAnnotation;
    private final List<CellModel> cellModels;

    private SheetModel(Builder builder) {
        Objects.requireNonNull( builder.sheetAnnotation, "the annotation cannot is null");
        PreconditionsUtils.requireNotEmpty( builder.cellModels, "the Collection cannot be empty");

        this.sheetAnnotation = builder.sheetAnnotation;
        this.cellModels = builder.cellModels;
    }

    public ExcelSheet getSheetAnnotation() {
        return sheetAnnotation;
    }

    public List<CellModel> getCellModels() {
        return cellModels;
    }
    public static Builder builder() {
        return new Builder();
    }
    public static final class Builder {
        private ExcelSheet sheetAnnotation;
        private List<CellModel> cellModels;


        private Builder() {

        }

        public Builder sheetAnnotation(ExcelSheet sheetAnnotation) {

            this.sheetAnnotation = sheetAnnotation;
            return this;
        }

        public Builder cellModels(List<CellModel> cellModels) {
           this.cellModels = cellModels;
            return this;
        }

        public SheetModel build() {

            return new SheetModel(this);
        }
    }
}
