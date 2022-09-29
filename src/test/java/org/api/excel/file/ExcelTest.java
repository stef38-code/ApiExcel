package org.api.excel.file;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbookFactory;
import org.apache.poi.poifs.crypt.temp.SXSSFWorkbookWithCustomZipEntrySource;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.api.excel.annotations.ExcelSheet;
import org.api.excel.exception.ExcelException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tools.FileUtil;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExcelTest {
    /**
     * Method under test: {@link Excel#read(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void read() throws ExcelException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Is not file
        //       at org.api.excel.utils.Require.execute(Require.java:43)
        //       at org.api.excel.utils.Require.positiveTest(Require.java:26)
        //       at org.api.excel.utils.Conditions.requireIsFile(Conditions.java:94)
        //       at org.api.excel.utils.Conditions.requireFileAndExists(Conditions.java:72)
        //       at org.api.excel.file.Excel.read(Excel.java:21)
        //   In order to prevent read(String)
        //   from throwing IllegalArgumentException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   read(String).
        //   See https://diff.blue/R013 to resolve this issue.

        Excel.read("42");
    }

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
    void close_then_workBookNull_when_NullPointerException() throws ExcelException {
        Assertions.assertThatThrownBy(() -> Excel.close(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Workbook cannot be null");
    }
    /**
     * Method under test: {@link Excel#close(Workbook)}
     */
    @Test
    void close_then_workBookCloseException_when_NullPointerException() throws  IOException {
        Workbook workbook = mock(Workbook.class);
        Mockito.doThrow(new IOException("mock Workbook")).when(workbook).close();
        Assertions.assertThatThrownBy(() -> Excel.close(workbook))
                .isInstanceOf(ExcelException.class)
                .hasMessage("java.io.IOException: mock Workbook");
    }
}

