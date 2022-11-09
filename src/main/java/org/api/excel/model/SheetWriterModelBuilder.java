package org.api.excel.model;

import org.apache.logging.log4j.util.Strings;
import org.api.excel.core.annotations.Page;
import org.api.excel.core.utils.Conditions;

class SheetWriterModelBuilder implements SheetWriterModelFluent, SheetWriterModelFluent.CreateStep {
    private Page[] pages;

    @Override
    public SheetWriterModel create() {
        Page page = validatePage(pages);
        String name = nameSheetWithPageAnnotation(page);
        return new SheetWriterModel(name, page.rowNumber());
    }

    @Override
    public SheetWriterModelBuilder pages(Page[] pages) {
        this.pages = pages;
        return this;
    }

    private Page validatePage(Page[] pages) {
        Conditions.requireNotEmpty(pages);
        //Taille de 1
        if (pages.length != 1) {
            throw new IllegalArgumentException("Page array size not equal 1");
        }
        return pages[0];
    }

    private String nameSheetWithPageAnnotation(Page page) {
        if (Strings.isNotBlank(page.name())) {
            return page.name();
        }
        return "Sheet ".concat(String.valueOf(page.number()));
    }
}
