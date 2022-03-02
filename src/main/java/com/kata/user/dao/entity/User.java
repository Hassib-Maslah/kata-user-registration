package com.kata.user.dao.entity;

import com.kata.user.constants.GenderEnum;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { User.USERNAME_COLUMN_NAME, User.BIRTHDAY_COLUMN_NAME, User.COUNTRY_COLUMN_NAME}) })
public class User {
    public final static String USERNAME_COLUMN_NAME = "username";
    public final static String BIRTHDAY_COLUMN_NAME = "birthday";
    public final static String COUNTRY_COLUMN_NAME = "country";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = USERNAME_COLUMN_NAME)
    private String username;

    @Column(name = BIRTHDAY_COLUMN_NAME)
    private LocalDate birthday;

    @Column(name = COUNTRY_COLUMN_NAME)
    private String country;

    private String phone;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    public User() {
    }

    public User(String username, LocalDate birthday, String country, String phone, GenderEnum gender) {
        this.username = username;
        this.birthday = birthday;
        this.country = country;
        this.phone = phone;
        this.gender = gender;
    }

    public User(Long id, String username, LocalDate birthday, String country, String phone, GenderEnum gender) {
        this.id = id;
        this.username = username;
        this.birthday = birthday;
        this.country = country;
        this.phone = phone;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

}
