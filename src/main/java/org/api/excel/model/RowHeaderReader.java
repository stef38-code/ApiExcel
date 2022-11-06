package org.api.excel.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class RowHeaderReader {
    private final String nameField;
    private final int number;
    private final String columnName;

    private RowHeaderReader(String nameField, int number, String columnName) {
        this.nameField = nameField;
        this.number = number;
        this.columnName = columnName;
    }

    public static NameFieldStep aNew() {
        return new StepRowHeaderReader();
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

    public interface NameFieldStep {
        NumberStep nameField(String nameField);
    }

    public interface NumberStep {
        ColumnNameStep number(int number);
    }

    public interface ColumnNameStep {
        CreateStep columnName(String columnName);
    }

    public interface CreateStep {
        RowHeaderReader create();
    }

    private static class StepRowHeaderReader implements NameFieldStep, NumberStep, ColumnNameStep, CreateStep {
        private String nameField;
        private int number;
        private String columnName;

        @Override
        public NumberStep nameField(String nameField) {
            this.nameField = nameField;
            return this;
        }

        @Override
        public ColumnNameStep number(int number) {
            this.number = number;
            return this;
        }

        @Override
        public CreateStep columnName(String columnName) {
            this.columnName = columnName;
            return this;
        }

        @Override
        public RowHeaderReader create() {
            return new RowHeaderReader(nameField, number, columnName);
        }
    }

}
