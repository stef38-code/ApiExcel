package org.api.excel.services.reader;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.api.excel.core.annotations.Box;
import org.api.excel.core.annotations.BoxBuilder;
import org.api.excel.core.utils.Debug;
import org.api.excel.model.commun.CellModel;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CellReadServiceByPoi implements CellReadService {

    private static Stream<Cell> getStream(Iterator<Cell> cells) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(cells, 0),
                false);
    }

    private static Box getAnnotation(Box box, Cell cell) {
        return BoxBuilder.aNew().box(box).cell(cell).create();
    }

    @Override
    public CellModel findPosition(CellModel cellModel, Iterator<Cell> cells) {
        Box box = cellModel.getAnnotation();
        String name = box.name();
        Optional<Cell> optionalCell = getStream(cells).filter(cell ->
                StringUtils.equalsIgnoreCase(cell.getStringCellValue(), name)
        ).findFirst();
        return optionalCell.map(
                cell -> {
                    Debug.print(this, "Remplace annotation [{0},{1}] par [{3},{2}]", name, box.number(), cell.getColumnIndex(), cell.getStringCellValue());
                    return CellModel.duplicate(cellModel).annotation(getAnnotation(box, cell)).done();
                }
        ).orElse(cellModel);
    }

    @Override
    public List<CellModel> getCellModelCorrecte(List<CellModel> cellModels, Optional<Row> headerRowOptional) {
        Row row = headerRowOptional.orElseThrow();
        return cellModels.stream().map(
                cellModel -> findPosition(cellModel, row.cellIterator())).collect(Collectors.toList());
    }
}
