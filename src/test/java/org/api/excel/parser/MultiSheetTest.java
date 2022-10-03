package org.api.excel.parser;

import org.junit.jupiter.api.Test;
import sample.PersonneMultiSheet;
import tools.FileUtil;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

 class MultiSheetTest {
    @Test
    void toEntities_Lorsque_MultiAnnotationExcelSheets_Attend_ListEntities() {
        //Conditions préalables (given)
        String excelFile = FileUtil.getAbsolutePath("personnes.xls");
        //Une action se produit (when)
        Optional<List<PersonneMultiSheet>> optional = ParseExcel.clazz(PersonneMultiSheet.class)
                .file(excelFile)
                .build();
        //Vérifier la sortie (then)
        assertThat(optional).isPresent()
                .containsInstanceOf(List.class);
        List<PersonneMultiSheet> personnes = optional.get();
        assertThat(personnes).isNotEmpty().hasSize(90);
    }
}
