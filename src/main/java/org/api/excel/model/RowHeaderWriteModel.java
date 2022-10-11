package org.api.excel.model;

import org.apache.commons.collections4.MapUtils;
import org.api.excel.core.annotations.Box;
import org.api.excel.model.commun.CellModel;
import org.api.excel.core.utils.Conditions;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowHeaderWriteModel {
    private final Map<Integer, RowHeaderReader> header;

    private RowHeaderWriteModel(Builder builder) {
        this.header = builder.header();
    }

    public static Builder cell(List<CellModel> cellModels) {
        return new Builder().cells(cellModels);
    }

    public Map<Integer, RowHeaderReader> getHeader() {
        return header;
    }

    public static class Builder {
        private List<CellModel> cellModels;
        private Map<Integer, RowHeaderReader> header = new HashMap<>();

        public RowHeaderWriteModel build() {
            Conditions.requireNotEmpty(cellModels);
            cellModels.forEach(cellReaderModel -> {
                Box annotation = cellReaderModel.getAnnotation();
                int number = annotation.number();
                header.put(number, RowHeaderReader.builder()
                        .number(number)
                        .columnName(annotation.name())
                        .nameField(cellReaderModel.getField().getName())
                        .build());
            });
            return new RowHeaderWriteModel(this);
        }

        public Builder cells(List<CellModel> cellModels) {
            this.cellModels = cellModels;
            return this;
        }

        public Map<Integer, RowHeaderReader> header() {
            if (MapUtils.isNotEmpty(header)) {
                header.entrySet()
                        .stream()
                        .sorted(Map.Entry.<Integer, RowHeaderReader>comparingByKey());
                return header;
            }
            return Collections.emptyMap();
        }
    }
}
