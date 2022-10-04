package org.api.excel.annotations;

import org.apache.poi.ss.usermodel.Cell;

import java.lang.annotation.Annotation;

public class BoxBuilder {
    private BoxBuilder() {

    }

    public static Builder box(Box boxOld) {
        return new Builder(boxOld);
    }

    public static final class Builder {
        private Cell cell;
        private Box boxOld;

        private Builder(Box boxOld) {
            this.boxOld = boxOld;
        }

        public Builder cell(Cell cell) {
            this.cell = cell;
            return this;
        }

        public Box build() {
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
