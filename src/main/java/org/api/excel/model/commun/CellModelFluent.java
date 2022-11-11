package org.api.excel.model.commun;

import java.lang.reflect.Field;

public interface CellModelFluent {
    Create field(Field field);

    interface Create {
        CellModel create();
    }
}
