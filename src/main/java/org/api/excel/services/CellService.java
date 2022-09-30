package org.api.excel.services;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.api.excel.annotations.Box;
import org.api.excel.annotations.BoxBuilder;
import org.api.excel.model.CellModel;
import org.api.excel.utils.Info;

import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CellService {

    private static Stream<Cell> getStream(Iterator<Cell> cells) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(cells, 0),
                false);
    }

    private static Box getAnnotation(Box box, Cell cell) {
        return BoxBuilder.box(box).cell(cell).build();
    }

    public void findPosition(CellModel cellModel, Iterator<Cell> cells) {
        Box box = cellModel.getAnnotation();
        String name = box.name();
        Optional<Cell> optionalCell = getStream(cells).filter(cell ->
                StringUtils.equalsIgnoreCase(cell.getStringCellValue(), name)
        ).findFirst();
        if (optionalCell.isPresent()) {
            Cell cell = optionalCell.get();
            Info.print(this, "cellAnnotation[{0},{1}] trouvé: {2},{3}", name, box.number(), cell.getColumnIndex(), cell.getStringCellValue());
            cellModel = CellModel.duplique(cellModel).annotation(getAnnotation(box, cell)).build();
        }
    }
}
