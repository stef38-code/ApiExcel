package org.api.excel.file;

import org.api.excel.exception.ExcelException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.FileUtil;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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
}

