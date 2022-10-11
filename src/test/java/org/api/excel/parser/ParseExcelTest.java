package org.api.excel.parser;

import org.api.excel.parser.reader.ReaderExcel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import sample.Personne;
import tools.FileUtil;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ParseExcelTest {

    @Test
    void toEntities_then_BlankFile_When_OptionalEmpty() {
        String excelFile = FileUtil.getAbsolutePath("blank.xlsx");
        ReaderExcel<sample.Personne> personneBuilder = ParseExcel.read(Personne.class)
                .file(excelFile);
        Assertions.assertThatThrownBy(personneBuilder::build)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Sheet Is Empty");
    }

    @Test
    void toEntities_then_NoData_Attend_OptionalEmpty() {
        String excelFile = FileUtil.getAbsolutePath("empty.xlsx");
        //Une action se produit (when)
        Optional<List<Personne>> optional = ParseExcel.read(Personne.class)
                .file(excelFile)
                .build();
        assertThat(optional).isNotPresent();
    }

}
