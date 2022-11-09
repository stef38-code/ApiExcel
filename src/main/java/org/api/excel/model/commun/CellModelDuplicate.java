package org.api.excel.model.commun;

import org.api.excel.core.annotations.Box;

import java.lang.reflect.Field;
import java.util.Objects;

class CellModelDuplicate implements CellModelDuplicateFluent, CellModelDuplicateFluent.Done {
    private final Field field;
    private Box annotation;

    public CellModelDuplicate(Field field, Box annotation) {
        this.field = field;
        this.annotation = annotation;
    }

    @Override
    public CellModelDuplicate annotation(Box annotation) {
        this.annotation = annotation;
        return this;
    }

    @Override
    public CellModel done() {
        Objects.requireNonNull(this.field, "the field cannot is null");
        Objects.requireNonNull(this.annotation, "the annotation cannot is null");
        return new CellModel(this.field, this.annotation);
    }
}
