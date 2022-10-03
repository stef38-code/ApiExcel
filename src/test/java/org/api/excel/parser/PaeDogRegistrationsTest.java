package org.api.excel.parser;

import org.junit.jupiter.api.Test;
import sample.PaeDogRegistrations;
import tools.FileUtil;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class PaeDogRegistrationsTest {
    @Test
    void parseFile_Lorsque_PaeDogRegistrations2019_Attend_ListSize14567() {
        String excelFile = FileUtil.getAbsolutePath("pae_dog_registrations_2019.xlsx");
        //Une action se produit (when)
        Optional<List<PaeDogRegistrations>> optional = ParseExcel.clazz(PaeDogRegistrations.class)
                .file(excelFile)
                .build();
        //Vérifier la sortie (then)
        assertThat(optional).isPresent()
                .containsInstanceOf(List.class);
        List<PaeDogRegistrations> personnes = optional.get();
        assertThat(personnes).isNotEmpty().hasSize(14567);
        assertRowValue(personnes.get(0), "Sheldon", "Non-Standard - Concession", "German Shepherd Dog", "Black", "M", "N", "NORTH HAVEN");
        assertRowValue(personnes.get(99), "Bella", "Standard - Both desexed & microchipped", "Labrador Retriever", "Gold", "F", "Y", "NORTH HAVEN");
        assertRowValue(personnes.get(342), "Alyx", "Standard - Both desexed & microchipped", "Australian Shepherd", "Tri Colour", "F", "Y", "NORTH HAVEN");
        assertRowValue(personnes.get(9354), "Rudie", "Standard - Concession", "Australian Cattle Dog", "Blue", "M", "Y", "DEVON PARK");
        assertRowValue(personnes.get(14566), "Violet", "Standard - Both desexed & microchipped", "Dachshund (Miniature)", "Black", "F", "Y", "GREENACRES");

    }

    void assertRowValue(PaeDogRegistrations dogRegistrations, String AnimalName, String animalclass, String animalbreed, String animalcolour, String animalgender, String animaldesexed, String suburb_name) {
        assertThat(dogRegistrations.getName()).hasToString(AnimalName);
        assertThat(dogRegistrations.getClasse()).hasToString(animalclass);
        assertThat(dogRegistrations.getBreed()).hasToString(animalbreed);
        assertThat(dogRegistrations.getColour()).hasToString(animalcolour);
        assertThat(dogRegistrations.getGender()).hasToString(animalgender);
        assertThat(dogRegistrations.getSexed()).hasToString(animaldesexed);
        assertThat(dogRegistrations.getSexed()).hasToString(animaldesexed);
        assertThat(dogRegistrations.getSuburb()).hasToString(suburb_name);

    }
}
