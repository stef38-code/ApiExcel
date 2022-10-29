package org.api.excel.parser.reader;

import org.api.excel.parser.ParseExcel;
import org.junit.jupiter.api.Test;
import sample.PaeDogRegistrations;
import tools.FileUtil;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ReaderFilePaeDogRegistrationsTest {
    @Test
    void parseFile_Lorsque_PaeDogRegistrations2019_Attend_ListSize14567() {
        String excelFile = FileUtil.getAbsolutePath("pae_dog_registrations_2019.xlsx");
        //Une action se produit (when)
        Optional<List<PaeDogRegistrations>> optional = ParseExcel.read(PaeDogRegistrations.class)
                .file(excelFile)
                .build();
        //Vérifier la sortie (then)
        assertThat(optional).isPresent()
                .containsInstanceOf(List.class);
        List<PaeDogRegistrations> dogs = optional.get();
        assertThat(dogs).isNotEmpty().hasSize(14567);
        assertRowValue(dogs.get(0), "Sheldon", "Non-Standard - Concession", "German Shepherd Dog", "Black", "M", "N", "NORTH HAVEN");
        assertRowValue(dogs.get(99), "Bella", "Standard - Both desexed & microchipped", "Labrador Retriever", "Gold", "F", "Y", "NORTH HAVEN");
        assertRowValue(dogs.get(342), "Alyx", "Standard - Both desexed & microchipped", "Australian Shepherd", "Tri Colour", "F", "Y", "NORTH HAVEN");
        assertRowValue(dogs.get(9354), "Rudie", "Standard - Concession", "Australian Cattle Dog", "Blue", "M", "Y", "DEVON PARK");
        assertRowValue(dogs.get(14566), "Violet", "Standard - Both desexed & microchipped", "Dachshund (Miniature)", "Black", "F", "Y", "GREENACRES");

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
