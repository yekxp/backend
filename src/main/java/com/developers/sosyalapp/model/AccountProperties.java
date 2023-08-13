package com.developers.sosyalapp.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "account_properties")
public class AccountProperties {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private int age;
    private boolean smoking;
    private double income;
    private String city;
    private String district;

    @OneToOne(mappedBy = "accountProperties")
    private Account account;

    public AccountProperties() {
    }

    public AccountProperties(int age, boolean smoking, double income, String city, String district, Account account) {
        this.age = age;
        this.smoking = smoking;
        this.income = income;
        this.city = city;
        this.district = district;
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
