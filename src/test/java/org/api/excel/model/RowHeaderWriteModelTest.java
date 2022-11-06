package org.api.excel.model;

import org.api.excel.core.annotations.Box;
import org.api.excel.core.annotations.Page;
import org.api.excel.mapping.ModelMapper;
import org.api.excel.model.commun.BookModel;
import org.api.excel.model.commun.CellModel;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RowHeaderWriteModelTest {

    private static void assertProperties(RowHeaderReader rowHeaderReader, int number, String columnName, String nameField) {
        assertThat(rowHeaderReader.getNumber()).isEqualTo(number);
        assertThat(rowHeaderReader.getColumnName()).hasToString(columnName);
        assertThat(rowHeaderReader.getNameField()).hasToString(nameField);
    }

    @Test
    void _Lorsque__Attend_() {
        //Conditions préalables (given)
        ModelMapper mapper = ModelMapper.getInstance();
        BookModel bookModel = mapper.to(PersonneTest.class);
        List<CellModel> cellModels = bookModel.getCellModels();
        //Une action se produit (when)
        RowHeaderWriteModel rowHaeder = RowHeaderWriteModel.aNew().cells(cellModels).create();
        Map<Integer, RowHeaderReader> header = rowHaeder.getHeader();
        assertThat(header).isNotNull().isNotEmpty().hasSize(6);
        assertProperties(header.get(0), 0, "name", "name");
        assertProperties(header.get(5), 5, "guid", "guid");
    }

    @Page(name = "Feuil1")
    private static class PersonneTest {

        @Box(number = 1, name = "company")
        private String company;
        @Box(name = "name")
        private String name;
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
    }
}

