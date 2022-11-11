package org.api.excel.model.commun;

import org.api.excel.core.annotations.Box;

public interface CellModelDuplicateFluent {
    Done annotation(Box annotation);

    interface Done {
        CellModel done();
    }
}
