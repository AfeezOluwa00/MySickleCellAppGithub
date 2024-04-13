package com.example.travelplannerca3;

// ReadWriteUserDetails.java
public class ReadWriteUserDetails {
    private String email, password, mobile, name, number, dateOfBirth, sickleCellType;
    private String defaultCity;  // User's default city

    // Default constructor required for Firebase
    public ReadWriteUserDetails() {
    }

    public ReadWriteUserDetails(String textEmail, String textPassword, String textMobile, String textName,
                                String textNumber, String textDateOfBirth, String textSickleCellType, String textDefaultCity) {
        this.email = textEmail;
        this.password = textPassword;
        this.mobile = textMobile;
        this.name = textName;
        this.number = textNumber;
        this.dateOfBirth = textDateOfBirth;
        this.sickleCellType = textSickleCellType;
        this.defaultCity = textDefaultCity;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSickleCellType() {
        return sickleCellType;
    }

    public String getDefaultCity() {
        return defaultCity;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setSickleCellType(String sickleCellType) {
        this.sickleCellType = sickleCellType;
    }

    public void setDefaultCity(String defaultCity) {
        this.defaultCity = defaultCity;
    }
}
