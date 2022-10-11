package sample;

import org.api.excel.core.annotations.Box;
import org.api.excel.core.annotations.Page;

import java.time.LocalDate;

@Page
public class Sample {
    @Box
    private String firstname;
    @Box
    private String lastname;
    @Box
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
