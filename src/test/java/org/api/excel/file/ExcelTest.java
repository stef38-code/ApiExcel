package org.api.excel.file;

import org.apache.poi.ss.usermodel.Workbook;
import org.api.excel.exception.ExcelException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tools.FileUtil;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;

class ExcelTest {


    /**
     * Method under test: {@link Excel#read(String)}
     */
    @Test
    void read_then_SimpleFile_when_ExcelException() {
        String simpleFile = FileUtil.getAbsolutePath("simple.txt");
        Assertions.assertThatThrownBy(() -> Excel.read(simpleFile))
                .isInstanceOf(ExcelException.class)
                .hasMessageContaining("Invalid header signature");
    }

    /**
     * Method under test: {@link Excel#read(String)}
     */
    @Test
    void read_then_fileNotExiste_when_IllegalArgumentException() {
        assertThatExceptionOfType(IllegalArgumentException.class).
                isThrownBy(() -> Excel.read("fileNotExiste"))
                .withMessage("Is not file");
    }

    /**
     * Method under test: {@link Excel#close(Workbook)}
     */
    @Test
    void close_then_workBookNull_when_NullPointerException() {
        Assertions.assertThatThrownBy(() -> Excel.close(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Workbook cannot be null");
    }

    /**
     * Method under test: {@link Excel#close(Workbook)}
     */
    @Test
    void close_then_workBookCloseException_when_NullPointerException() throws IOException {
        Workbook workbook = mock(Workbook.class);
        Mockito.doThrow(new IOException("mock Workbook")).when(workbook).close();
        Assertions.assertThatThrownBy(() -> Excel.close(workbook))
                .isInstanceOf(ExcelException.class)
                .hasMessage("java.io.IOException: mock Workbook");
    }
}

