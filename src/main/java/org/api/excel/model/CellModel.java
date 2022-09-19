package org.api.excel.model;

import org.api.excel.annotations.ExcelCell;

import java.lang.reflect.Field;
import java.util.Objects;

public class CellModel {
    private final Field field;
    private final ExcelCell annotation;

    private CellModel(Builder builder) {
        Objects.requireNonNull( builder.field,"the field cannot is null");
        Objects.requireNonNull( builder.annotation,"the annotation cannot is null");
        this.field = builder.field;
        this.annotation = builder.annotation;
    }

    public Field getField() {
        return field;
    }

    public ExcelCell getAnnotation() {
        return annotation;
    }
    public static Builder builder() {
        return new Builder();
    }
    public static final class Builder {

        private Field field;
        private ExcelCell annotation;

        private Builder() {
        }

        public Builder field(Field field) {
            this.annotation = field.getAnnotation(ExcelCell.class);
            this.field = field;
            return this;
        }

        public CellModel build() {
            return new CellModel(this);
        }
    }
}
