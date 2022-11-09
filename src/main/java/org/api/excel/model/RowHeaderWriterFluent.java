package org.api.excel.model;

import org.api.excel.model.commun.CellModel;

import java.util.List;

public interface RowHeaderWriterFluent {
    Create cells(List<CellModel> cellModels);


    interface Create {
        RowHeaderWriteModel create();
    }
}
