package com.yakuza.backend.Controller.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class RegisterRequestDto implements Serializable {
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String country;
    @NotNull
    private String street;
    @NotNull
    private String city;
    @NotNull
    private String streetNumber;
    @NotNull
    private Integer userType;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public Integer getUserType() {
        return userType;
    }
}
