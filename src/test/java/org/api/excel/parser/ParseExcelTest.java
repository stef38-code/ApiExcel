package org.api.excel.parser;

import org.api.excel.sample.Personne;
import org.junit.jupiter.api.Test;
import tools.FileUtil;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseExcelTest {
    @Test
    void toEntities_Lorsque_FileExcel_Attend_ListEntities() {
        //Conditions préalables (given)
        String excelFile = FileUtil.getAbsolutePath("exemple.xls");
        //Une action se produit (when)
        Optional personnes = ParseExcel.clazz(Personne.class)
                .file(excelFile)
                        .build();
        //Vérifier la sortie (then)
        assertThat(personnes).isNotPresent();
    }

}
