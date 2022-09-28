package org.api.excel.services;

import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RowsService {
    private static final Logger log = LoggerFactory.getLogger(RowsService.class);

    public List<Row> extractDataRows(Iterator<Row> rows, int rowNumber) {
        log.info("Extract rows from row number {}", rowNumber);
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(rows, 0),
                false).filter(row -> row.getRowNum() > (rowNumber - 1)).collect(Collectors.toList());
    }
}
