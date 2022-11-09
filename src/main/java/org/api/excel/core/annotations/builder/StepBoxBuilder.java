package org.api.excel.core.annotations.builder;

import org.apache.poi.ss.usermodel.Cell;
import org.api.excel.core.annotations.Box;

import java.lang.annotation.Annotation;

public class StepBoxBuilder implements BoxStep, BoxStep.CellStep, BoxStep.CreateStep {
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

