package org.api.excel.core.annotations;

import org.apache.poi.ss.usermodel.Cell;

import java.lang.annotation.Annotation;

public class BoxBuilder {
    private BoxBuilder() {

    }

    public static BoxStep aNew() {
        return new StepBoxBuilder();
    }

    public interface BoxStep {
        CellStep box(Box boxOld);
    }

    public interface CellStep {
        CreateStep cell(Cell cell);
    }

    public interface CreateStep {
        Box create();
    }

    private static class StepBoxBuilder implements BoxStep, CellStep, CreateStep {
        private Cell cell;
        private Box boxOld;

        @Override
        public CellStep box(Box boxOld) {
            this.boxOld = boxOld;
            return this;
        }

        @Override
        public CreateStep cell(Cell cell) {
            this.cell = cell;
            return this;
        }

        @Override
        public Box create() {
            return new Box() {

                @Override
                public Class<? extends Annotation> annotationType() {
                    return Box.class;
                }

                @Override
                public int number() {
                    return cell.getColumnIndex();
                }

                @Override
                public String name() {
                    return boxOld.name();
                }

            };
        }
    }
}
