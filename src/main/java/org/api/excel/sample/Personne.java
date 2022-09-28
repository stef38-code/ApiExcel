package org.api.excel.sample;

import org.api.excel.annotations.ExcelCell;
import org.api.excel.annotations.ExcelSheet;

@ExcelSheet(name = "Feuil1")
public class Personne {
    @ExcelCell(stringFormat = true)
    private String name;
    @ExcelCell(number = 1)
    private String company;
    @ExcelCell(number = 2)
    private String address;
    @ExcelCell(number = 3, stringFormat = true)
    private String postalZip;
    @ExcelCell(number = 4)
    private String city;
    @ExcelCell(number = 5)
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
