package org.api.excel.exception;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CellConvertExceptionTest {
    /**
     * Method under test: {@link CellConvertException#CellConvertException(String, Cell)}
     */
    @Test
    void constructorAndMessage() {

        Cell cell = mock(Cell.class);
        Row row = mock(Row.class);
        Sheet sheet = mock(Sheet.class);
        String msg = "";
        //
        given(cell.getRowIndex()).willReturn(32);

        given(row.getRowNum()).willReturn(31);
        given(cell.getRow()).willReturn(row);

        given(sheet.getSheetName()).willReturn("Mockito");
        given(cell.getSheet()).willReturn(sheet);

        // Act
        CellConvertException actualCellConvertException = new CellConvertException(msg, cell);
        // Assert
        Assertions.assertThat(actualCellConvertException.getMessage()).hasToString(" cell: 32 row: 31 sheet: Mockito");
    }

}

