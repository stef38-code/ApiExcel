package sample;

import org.api.excel.annotations.Box;
import org.api.excel.annotations.Page;

@Page(name = "Sheet 1")
public class Bench {
    @Box(stringFormat = true)
    private String first;
    @Box(number =1,stringFormat = true)
    private String last	;
    @Box(number =2,stringFormat = true)
    private String birthday;
    @Box(number =3,stringFormat = true)
    private String 	gender;
    @Box(number =4,stringFormat = true)
    private String 	phone;
    @Box(number =5,stringFormat = true)
    private String 	email;
    @Box(number =6,stringFormat = true)
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
