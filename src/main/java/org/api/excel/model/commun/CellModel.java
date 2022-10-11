package org.api.excel.model.commun;

import org.api.excel.core.annotations.Box;

import java.lang.reflect.Field;
import java.util.Objects;

public class CellModel {
    private final Field field;
    private final Box annotation;

    CellModel(Builder builder) {
        Objects.requireNonNull(builder.field, "the field cannot is null");
        Objects.requireNonNull(builder.annotation, "the annotation cannot is null");
        this.field = builder.field;
        this.annotation = builder.annotation;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder duplique(CellModel cellModelOld) {
        return new Builder(cellModelOld.getAnnotation(), cellModelOld.getField());
    }

    public Field getField() {
        return field;
    }

    public Box getAnnotation() {
        return annotation;
    }

    public static final class Builder {

        private Field field;
        private Box annotation;

        private Builder() {
        }

        private Builder(Box annotation, Field field) {
            this.annotation = annotation;
            this.field = field;
        }

        public Builder field(Field field) {
            this.annotation = field.getAnnotation(Box.class);
            this.field = field;
            return this;
        }

        public Builder annotation(Box annotation) {
            this.annotation = annotation;
            return this;
        }

        public CellModel build() {
            return new CellModel(this);
        }
    }
}
