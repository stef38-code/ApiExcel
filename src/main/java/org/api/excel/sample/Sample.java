package org.api.excel.sample;

import org.api.excel.annotations.ExcelCell;
import org.api.excel.annotations.ExcelSheet;

import java.time.LocalDate;

@ExcelSheet
public class Sample {
    @ExcelCell
    private String firstname;
    @ExcelCell
    private String lastname;
    @ExcelCell
    private int age;
    private LocalDate toDay;
    private String stringValue;

    public LocalDate getToDay() {
        return toDay;
    }

    public void setToDay(LocalDate toDay) {
        this.toDay = toDay;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStringValue() {
        return stringValue;
    }
}
