package org.api.excel.parser;

import sample.Personne;
import sample.Personne2;
import sample.Personne3;
import org.junit.jupiter.api.Test;
import tools.FileUtil;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ParseExcelTest {

    @Test
    void toEntities_then_BlankFile_When_OptionalEmpty() {
        String excelFile = FileUtil.getAbsolutePath("blank.xlsx");
        //Une action se produit (when)
        Optional<List<Personne>> optional = ParseExcel.clazz(Personne.class)
                .file(excelFile)
                .build();
        assertThat(optional).isNotPresent();
    }

    @Test
    void toEntities_then_NoData_Attend_OptionalEmpty() {
        String excelFile = FileUtil.getAbsolutePath("empty.xlsx");
        //Une action se produit (when)
        Optional<List<Personne>> optional = ParseExcel.clazz(Personne.class)
                .file(excelFile)
                .build();
        assertThat(optional).isNotPresent();
    }
    @Test
    void toEntities_Lorsque_RowHeaderOtherOne_Attend_ListEntities() {
        //Conditions préalables (given)
        String excelFile = FileUtil.getAbsolutePath("exemple.xls");
        //Une action se produit (when)
        Optional<List<Personne2>> optional = ParseExcel.clazz(Personne2.class)
                .file(excelFile)
                .build();
        //Vérifier la sortie (then)
        assertThat(optional).isPresent()
                .containsInstanceOf(List.class);
        List<Personne2> personnes = optional.get();
        assertThat(personnes).isNotEmpty().hasSize(30);
    }
    @Test
    void toEntities_Lorsque_MultiAnnotationExcelSheets_Attend_ListEntities() {
        //Conditions préalables (given)
        String excelFile = FileUtil.getAbsolutePath("exemple.xls");
        //Une action se produit (when)
        Optional<List<Personne3>> optional = ParseExcel.clazz(Personne3.class)
                .file(excelFile)
                .build();
        //Vérifier la sortie (then)
        assertThat(optional).isPresent()
                .containsInstanceOf(List.class);
        List<Personne3> personnes = optional.get();
        assertThat(personnes).isNotEmpty().hasSize(90);
    }
    @Test
    void toEntities_Lorsque_FileExcel_Attend_ListEntities() {
        //Conditions préalables (given)
        String excelFile = FileUtil.getAbsolutePath("exemple.xls");
        //Une action se produit (when)
        Optional<List<Personne>> optional = ParseExcel.clazz(Personne.class)
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
        String excelFile = FileUtil.getAbsolutePath("exemple.xls");
        String excelFile2 = FileUtil.getAbsolutePath("exemple.xlsx");
        //Une action se produit (when)
        Optional<List<Personne>> optional = ParseExcel.clazz(Personne.class)
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
