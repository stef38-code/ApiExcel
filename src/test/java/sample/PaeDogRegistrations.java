package sample;

import org.api.excel.core.annotations.Book;
import org.api.excel.core.annotations.Box;
import org.api.excel.core.annotations.Page;

@Book(value = {
        @Page(name = "pae_dog_registrations_2019")
})
public class PaeDogRegistrations {
    @Box(name = "AnimalName")
    private String name;
    @Box(name = "animalclass")
    private String classe;
    @Box(name = "animalbreed")
    private String breed;
    @Box(name = "animalcolour")
    private String colour;
    @Box(name = "animalgender")
    private String gender;
    @Box(name = "animaldesexed")
    private String sexed;
    @Box(name = "suburb_name")
    private String suburb;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSexed() {
        return sexed;
    }

    public void setSexed(String sexed) {
        this.sexed = sexed;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }
}
