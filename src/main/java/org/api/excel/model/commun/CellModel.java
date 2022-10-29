package org.api.excel.model.commun;

import org.api.excel.core.annotations.Box;

import java.lang.reflect.Field;
import java.util.Objects;

public class CellModel {
    private final Field field;
    private final Box annotation;


    private CellModel(Field field, Box annotation) {
        this.field = field;
        this.annotation = annotation;
    }

    public static StepField aNew() {
        return new StepCellModel();
    }

    public static StepDuplicate duplicate(CellModel cellModelOld) {
        return new StepDuplicateCellModel(cellModelOld.getField(), cellModelOld.getAnnotation());
    }

    public Field getField() {
        return field;
    }

    public Box getAnnotation() {
        return annotation;
    }

    public interface StepField {
        StepBuilderAnnotation field(Field field);
    }


    public interface StepBuilderAnnotation {
        CellModel create();
    }

    public interface StepDuplicate {
        StepDuplicateDone annotation(Box annotation);
    }

    public interface StepDuplicateDone {
        CellModel done();
    }

    private static class StepDuplicateCellModel implements StepDuplicate, StepDuplicateDone {
        private Field field;
        private Box annotation;

        public StepDuplicateCellModel(Field field, Box annotation) {
            this.field = field;
            this.annotation = annotation;
        }

        @Override
        public StepDuplicateCellModel annotation(Box annotation) {
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

    private static class StepCellModel implements StepField, StepBuilderAnnotation {
        private Field field;
        private Box annotation;

        @Override
        public StepCellModel field(Field field) {
            this.annotation = field.getAnnotation(Box.class);
            this.field = field;
            return this;
        }

        @Override
        public CellModel create() {
            Objects.requireNonNull(this.field, "the field cannot is null");
            Objects.requireNonNull(this.annotation, "the annotation cannot is null");
            return new CellModel(this.field, this.annotation);
        }
    }

}
