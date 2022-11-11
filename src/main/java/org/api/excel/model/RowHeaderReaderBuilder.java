package org.api.excel.model;

class RowHeaderReaderBuilder implements RowHeaderReaderFluent, RowHeaderReaderFluent.Number, RowHeaderReaderFluent.ColumnName, RowHeaderReaderFluent.Create {
    private String nameField;
    private int number;
    private String columnName;

    @Override
    public RowHeaderReaderBuilder nameField(String nameField) {
        this.nameField = nameField;
        return this;
    }

    @Override
    public RowHeaderReaderBuilder number(int number) {
        this.number = number;
        return this;
    }

    @Override
    public RowHeaderReaderBuilder columnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    @Override
    public RowHeaderReader create() {
        return new RowHeaderReader(nameField, number, columnName);
    }
}
