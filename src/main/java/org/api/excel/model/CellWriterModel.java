package org.api.excel.model;

import org.apache.commons.lang3.StringUtils;
import org.api.excel.core.annotations.Box;

public class CellWriterModel {
    private final int number;
    private final String name;

    private CellWriterModel(Builder builder) {
        this.name = builder.name();
        this.number = builder.number();
    }

    public static Builder box(Box box) {
        return new Builder().box(box);
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public static final class Builder {
        private Box box;

        private Builder() {

        }

        public CellWriterModel build() {
            return new CellWriterModel(this);
        }

        public Builder box(Box box) {
            this.box = box;
            return this;
        }

        private String name() {
            if (StringUtils.isNotBlank(box.name())) {
                return box.name();
            }
            return "Column ".concat("???");
        }

        private int number() {
            return -1;
        }
    }
}
