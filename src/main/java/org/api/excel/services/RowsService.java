package org.api.excel.services;

import org.apache.poi.ss.usermodel.Row;
import org.api.excel.utils.Info;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class RowsService {

    private static Stream<Row> getStream(Iterator<Row> rows) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(rows, 0),
                false);
    }

    public List<Row> extractDataRows(Iterator<Row> rows, int rowNumber) {
        Info.print(this, "Extract rows from row number {0}", rowNumber);
        return getStream(rows).filter(row -> row.getRowNum() > (rowNumber - 1)).collect(Collectors.toList());
    }

    public Optional<Row> getRowsHeader(Iterator<Row> rows, int rowNumber) {
        Info.print(this, "Extract rows header number {0}", rowNumber);
        return getStream(rows).filter(row -> row.getRowNum() == (rowNumber - 1)).findFirst();

    }
}
