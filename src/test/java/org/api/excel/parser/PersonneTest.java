package org.api.excel.parser;

import org.junit.jupiter.api.Test;
import sample.Personne;
import tools.FileUtil;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class PersonneTest {
    @Test
    void toEntities_Lorsque_FileExcel_Attend_ListEntities() {
        //Conditions préalables (given)
        String excelFile = FileUtil.getAbsolutePath("personnes.xls");
        //Une action se produit (when)
        Optional<List<Personne>> optional = ParseExcel.read(Personne.class)
                .file(excelFile)
                .build();
        //Vérifier la sortie (then)
        assertThat(optional).isPresent()
                .containsInstanceOf(List.class);
        List<Personne> personnes = optional.get();
        assertThat(personnes).isNotEmpty().hasSize(30);
    }

    @Test
    void toEntities_Lorsque_TwoFileExcel_Attend_ListEntities() {
        //Conditions préalables (given)
        String excelFile = FileUtil.getAbsolutePath("personnes.xls");
        String excelFile2 = FileUtil.getAbsolutePath("personnes.xlsx");
        //Une action se produit (when)
        Optional<List<Personne>> optional = ParseExcel.read(Personne.class)
                .file(excelFile)
                .file(excelFile2)
                .build();
        //Vérifier la sortie (then)
        assertThat(optional).isPresent()
                .containsInstanceOf(List.class);
        List<Personne> personnes = optional.get();
        assertThat(personnes).isNotEmpty().hasSize(60);
    }
}
