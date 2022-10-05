package org.api.excel.model;

import org.apache.logging.log4j.util.Strings;
import org.api.excel.core.annotations.Page;
import org.api.excel.core.utils.Conditions;

public class SheetWriterModel {
    private final String name;
    private final int rowNumber;

    private SheetWriterModel(Builder builder) {
        this.name = builder.name();
        this.rowNumber = builder.rowNumber();
    }

    public static Builder pages(Page[] pages) {
        return new Builder().pages(pages);
    }

    public String getName() {
        return name;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public static final class Builder {
        private Page[] pages;
        private Page page;

        private Builder() {

        }

        private String name() {
            return nameSheetWithPageAnnotation(page);
        }

        private int rowNumber() {
            return page.rowNumber();
        }

        public Builder pages(Page[] pages) {
            this.pages = pages;
            return this;
        }

        public SheetWriterModel build() {
            page = validatePage(pages);
            return new SheetWriterModel(this);
        }

        private String nameSheetWithPageAnnotation(Page page) {
            if (Strings.isNotBlank(page.name())) {
                return page.name();
            }
            return "Sheet ".concat(String.valueOf(page.number()));
        }

        private Page validatePage(Page[] pages) {
            Conditions.requireNotEmpty(pages);
            //Taille de 1
            if (pages.length != 1) {
                throw new IllegalArgumentException("Page array size not equal 1");
            }
            return pages[0];
        }
    }
}
