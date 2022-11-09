package org.api.excel.model.commun;

import org.api.excel.core.annotations.Box;

import java.lang.reflect.Field;

public class CellModel {
    private final Field field;
    private final Box annotation;


    CellModel(Field field, Box annotation) {
        this.field = field;
        this.annotation = annotation;
    }

    public static CellModelFluent aNew() {
        return new CellModelBuilder();
    }

    public static CellModelDuplicateFluent duplicate(CellModel cellModelOld) {
        return new CellModelDuplicate(cellModelOld.getField(), cellModelOld.getAnnotation());
    }

    public Field getField() {
        return field;
    }

    public Box getAnnotation() {
        return annotation;
    }

}
