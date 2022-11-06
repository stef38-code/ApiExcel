package org.api.excel.model;

import org.api.excel.core.annotations.Box;
import org.api.excel.core.utils.Conditions;
import org.api.excel.model.commun.CellModel;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RowHeaderWriteModel {
    private final Map<Integer, RowHeaderReader> header;

    private RowHeaderWriteModel(Map<Integer, RowHeaderReader> header) {
        this.header = header;
    }


    public static CellStep aNew() {
        return new StepRowHeaderReader();
    }

    public Map<Integer, RowHeaderReader> getHeader() {
        return header;
    }

    public interface CellStep {
        CreateStep cells(List<CellModel> cellModels);
    }

    public interface CreateStep {
        RowHeaderWriteModel create();
    }

    private static class StepRowHeaderReader implements CellStep, CreateStep {
        private final Map<Integer, RowHeaderReader> header = new HashMap<>();
        private List<CellModel> cellModels;

        @Override
        public CreateStep cells(List<CellModel> cellModels) {
            this.cellModels = cellModels;
            return this;
        }

        @Override
        public RowHeaderWriteModel create() {
            Conditions.requireNotEmpty(cellModels);
            cellModels.forEach(cellReaderModel -> {
                Box annotation = cellReaderModel.getAnnotation();
                int number = annotation.number();
                header.put(number, RowHeaderReader.aNew()
                        .nameField(cellReaderModel.getField().getName())
                        .number(number)
                        .columnName(annotation.name())
                        .create());
            });
            LinkedHashMap<Integer, RowHeaderReader> collectSorted = header.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey()).
                    collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue, LinkedHashMap::new));
            return new RowHeaderWriteModel(collectSorted);
        }
    }
}
