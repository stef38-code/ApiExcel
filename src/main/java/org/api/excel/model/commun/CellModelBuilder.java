package org.api.excel.model.commun;

import org.api.excel.core.annotations.Box;

import java.lang.reflect.Field;
import java.util.Objects;

class CellModelBuilder implements CellModelFluent, CellModelFluent.Create {
    private Field field;

    @Override
    public CellModelBuilder field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public CellModel create() {
        Objects.requireNonNull(this.field, "the field cannot is null");
        Box annotation = field.getAnnotation(Box.class);
        Objects.requireNonNull(annotation, "the annotation cannot is null");
        return new CellModel(this.field, annotation);
    }
}
