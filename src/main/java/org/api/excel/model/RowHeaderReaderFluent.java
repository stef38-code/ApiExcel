package org.api.excel.model;

public interface RowHeaderReaderFluent {

    Number nameField(String nameField);


    interface Number {
        ColumnName number(int number);
    }

    interface ColumnName {
        Create columnName(String columnName);
    }

    interface Create {
        RowHeaderReader create();
    }
}
