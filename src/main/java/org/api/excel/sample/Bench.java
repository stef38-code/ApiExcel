package org.api.excel.sample;

import org.api.excel.annotations.ExcelCell;
import org.api.excel.annotations.ExcelSheet;

@ExcelSheet(name = "Sheet 1")
public class Bench {
    @ExcelCell(stringFormat = true)
    private String first;
    @ExcelCell(number =1,stringFormat = true)
    private String last	;
    @ExcelCell(number =2,stringFormat = true)
    private String birthday;
    @ExcelCell(number =3,stringFormat = true)
    private String 	gender;
    @ExcelCell(number =4,stringFormat = true)
    private String 	phone;
    @ExcelCell(number =5,stringFormat = true)
    private String 	email;
    @ExcelCell(number =6,stringFormat = true)
    private String 	city;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
