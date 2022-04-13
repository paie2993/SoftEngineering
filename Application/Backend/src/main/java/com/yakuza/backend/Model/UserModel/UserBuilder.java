package com.yakuza.backend.Model.UserModel;

import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.time.LocalDate;

public class UserBuilder {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String country;
    private String street;
    private String city;
    private String streetNumber;
    private UserType userType;

    public UserBuilder(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Author buildAuthor() {
        Author author = new Author();
        author.setUsername(username);
        author.setPassword(password);
        author.setEmail(email);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setDateOfBirth(dateOfBirth);
        author.setPhoneNumber(phoneNumber);
        author.setCountry(country);
        author.setStreet(street);
        author.setCity(city);
        author.setStreetNumber(streetNumber);
        author.setUserType(userType);
        return author;
    }

    public Reviewer buildReviewer() {
        Reviewer reviewer = new Reviewer();
        reviewer.setUsername(username);
        reviewer.setPassword(password);
        reviewer.setEmail(email);
        reviewer.setFirstName(firstName);
        reviewer.setLastName(lastName);
        reviewer.setDateOfBirth(dateOfBirth);
        reviewer.setPhoneNumber(phoneNumber);
        reviewer.setCountry(country);
        reviewer.setStreet(street);
        reviewer.setCity(city);
        reviewer.setStreetNumber(streetNumber);
        reviewer.setUserType(userType);
        return reviewer;
    }

    public Chair buildChair() {
        Chair chair = new Chair();
        chair.setUsername(username);
        chair.setPassword(password);
        chair.setEmail(email);
        chair.setFirstName(firstName);
        chair.setLastName(lastName);
        chair.setDateOfBirth(dateOfBirth);
        chair.setPhoneNumber(phoneNumber);
        chair.setCountry(country);
        chair.setStreet(street);
        chair.setCity(city);
        chair.setStreetNumber(streetNumber);
        chair.setUserType(userType);
        return chair;
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public UserBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    public UserBuilder setStreet(String street) {
        this.street = street;
        return this;
    }

    public UserBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public UserBuilder setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public UserBuilder setUserType(UserType userType) {
        this.userType = userType;
        return this;
    }
}
