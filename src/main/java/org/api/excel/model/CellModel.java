package org.api.excel.model;

import org.api.excel.annotations.ExcelCell;

import java.lang.reflect.Field;
import java.util.Objects;

public class CellModel {
    private final Field field;
    private final ExcelCell annotation;

    private CellModel(Builder builder) {
        this.field = builder.field;
        this.annotation = builder.annotation;
    }

    public Field getField() {
        return field;
    }

    public ExcelCell getAnnotation() {
        return annotation;
    }

    public static final class Builder {

        private Field field;
        private ExcelCell annotation;

        private Builder() {
        }

        public Builder field(Field field) {
            Objects.requireNonNull(field,"the field cannot is null");
            this.field = field;
            return this;
        }

        public CellModel build() {
            this.annotation = field.getAnnotation(ExcelCell.class);
            Objects.requireNonNull(field,"the annotation cannot is null");
            return new CellModel(this);
        }
    }
}
