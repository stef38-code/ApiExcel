package org.api.excel.model;

import org.api.excel.core.annotations.Box;
import org.api.excel.core.utils.Conditions;
import org.api.excel.model.commun.CellModel;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RowHeaderWriterBuilder implements RowHeaderWriterFluent, RowHeaderWriterFluent.Create {
    private final Map<Integer, RowHeaderReader> header = new HashMap<>();
    private List<CellModel> cellModels;

    @Override
    public RowHeaderWriterBuilder cells(List<CellModel> cellModels) {
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
