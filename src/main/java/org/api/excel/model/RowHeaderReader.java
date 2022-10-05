package org.api.excel.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class RowHeaderReader {
    private final String nameField;
    private final int number;
    private final String columnName;

    public RowHeaderReader(Builder builder) {
        this.nameField = builder.nameField;
        this.number = builder.number;
        this.columnName = builder.columnName;
    }

    public static Builder builder() {
        return new Builder();
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

    public static final class Builder {
        private String nameField;
        private int number;
        private String columnName;

        private Builder() {
        }


        public Builder nameField(String nameField) {
            this.nameField = nameField;
            return this;
        }

        public Builder number(int number) {
            this.number = number;
            return this;
        }

        public Builder columnName(String columnName) {
            this.columnName = columnName;
            return this;
        }

        public RowHeaderReader build() {
            return new RowHeaderReader(this);
        }
    }
}
