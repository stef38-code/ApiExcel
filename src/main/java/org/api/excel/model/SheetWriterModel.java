package org.api.excel.model;

public class SheetWriterModel {
    private final String name;
    private final int rowNumber;

    SheetWriterModel(String name, int rowNumber) {
        this.name = name;
        this.rowNumber = rowNumber;
    }

    public static SheetWriterModelFluent aNew() {
        return new SheetWriterModelBuilder();
    }

    public String getName() {
        return name;
    }

    public int getRowNumber() {
        return rowNumber;
    }

}
