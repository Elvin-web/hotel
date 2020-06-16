package az.elvin.hotel.model;

import java.util.Date;

public class Guest extends HotelModel {
    private String guestName;
    private String guestSurname;
    private City city;
    private String phone;
    private String gender;
    private String email;
    private Passport passport;
    private String passportNumber;
    private Date dob;
    private String image1;
    private String image2;

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestSurname() {
        return guestSurname;
    }

    public void setGuestSurname(String guestSurname) {
        this.guestSurname = guestSurname;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "guestName='" + guestName + '\'' +
                ", guestSurname='" + guestSurname + '\'' +
                ", city=" + city +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", passport=" + passport +
                ", passportNumber='" + passportNumber + '\'' +
                ", dob=" + dob +
                ", image1='" + image1 + '\'' +
                ", image2='" + image2 + '\'' +
                '}';
    }
}


