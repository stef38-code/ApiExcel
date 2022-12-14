package sample;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.api.excel.core.annotations.Box;
import org.api.excel.core.annotations.Page;

@Page(name = "Feuil1")
public class Personne {

    @Box( name = "name")
    private String name;
    @Box(number = 1, name = "company")
    private String company;
    @Box(number = 2, name = "address")
    private String address;
    @Box(number = 3, name = "postalZip")
    private String postalZip;
    @Box(number = 4, name = "city")
    private String city;
    @Box(number = 5, name = "guid")
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("company", company)
                .append("address", address)
                .append("postalZip", postalZip)
                .append("city", city)
                .append("guid", guid)
                .toString();
    }
}
