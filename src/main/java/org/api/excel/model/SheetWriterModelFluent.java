package org.api.excel.model;

import org.api.excel.core.annotations.Page;

public interface SheetWriterModelFluent {
    CreateStep pages(Page[] pages);

    interface CreateStep {
        SheetWriterModel create();


    }
}
