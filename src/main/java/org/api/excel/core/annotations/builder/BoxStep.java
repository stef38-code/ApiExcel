package org.api.excel.core.annotations.builder;

import org.apache.poi.ss.usermodel.Cell;
import org.api.excel.core.annotations.Box;

public interface BoxStep {
    CellStep box(Box boxOld);

    interface CellStep {
        CreateStep cell(Cell cell);
    }

    interface CreateStep {
        Box create();
    }
}
