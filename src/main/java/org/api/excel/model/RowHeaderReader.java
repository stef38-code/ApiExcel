package org.api.excel.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class RowHeaderReader {
    private final String nameField;
    private final int number;
    private final String columnName;

    RowHeaderReader(String nameField, int number, String columnName) {
        this.nameField = nameField;
        this.number = number;
        this.columnName = columnName;
    }

    public static RowHeaderReaderFluent aNew() {
        return new RowHeaderReaderBuilder();
    }

    public String getNameField() {
        return nameField;
    }

    public int getNumber() {
        return number;
    }

    public String getColumnName() {
        return columnName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("nameField", nameField)
                .append("number", number)
                .append("columnName", columnName)
                .toString();
    }


}
