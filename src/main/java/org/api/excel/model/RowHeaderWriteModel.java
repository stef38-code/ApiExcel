package org.api.excel.model;

import java.util.Map;

public class RowHeaderWriteModel {
    private final Map<Integer, RowHeaderReader> header;

    RowHeaderWriteModel(Map<Integer, RowHeaderReader> header) {
        this.header = header;
    }


    public static RowHeaderWriterFluent aNew() {
        return new RowHeaderWriterBuilder();
    }

    public Map<Integer, RowHeaderReader> getHeader() {
        return header;
    }


}
