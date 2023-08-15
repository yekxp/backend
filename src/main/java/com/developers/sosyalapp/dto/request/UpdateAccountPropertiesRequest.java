package com.developers.sosyalapp.dto.request;

public class UpdateAccountPropertiesRequest {
    private int age;
    private boolean smoking;
    private double income;
    private String city;
    private String district;

    public UpdateAccountPropertiesRequest() {
    }

    public UpdateAccountPropertiesRequest(int age, boolean smoking, double income, String city, String district) {
        this.age = age;
        this.smoking = smoking;
        this.income = income;
        this.city = city;
        this.district = district;
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