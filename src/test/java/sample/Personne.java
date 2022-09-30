package sample;

import org.api.excel.annotations.Box;
import org.api.excel.annotations.Page;

@Page(name = "Feuil1")
public class Personne {
    @Box(stringFormat = true)
    private String name;
    @Box(number = 1)
    private String company;
    @Box(number = 2)
    private String address;
    @Box(number = 3, stringFormat = true)
    private String postalZip;
    @Box(number = 4)
    private String city;
    @Box(number = 5)
    private String guid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalZip() {
        return postalZip;
    }

    public void setPostalZip(String postalZip) {
        this.postalZip = postalZip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}
