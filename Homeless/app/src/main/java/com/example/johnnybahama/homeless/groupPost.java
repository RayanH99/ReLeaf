package com.example.johnnybahama.homeless;

public class groupPost {

    private int price;
    private  int point;
    private int moneyRequired;
    private int moneyDonated;
    private String name;
    private String description;


    public groupPost(int price, int point, int moneyRequired, int moneyDonated, String name, String description){
        this.price = price;
        this.point = point;
        this.moneyRequired = moneyRequired;
        this.moneyDonated = moneyDonated;
        this.name = name;
        this.description = description;

    }

    public void setPrice(int price){
        this.price = price;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setMoneyRequired(int moneyRequired) {
        this.moneyRequired = moneyRequired;
    }

    public void setMoneyDonated(int moneyDonated) {
        this.moneyDonated = moneyDonated;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public int getPoint() {
        return point;
    }

    public int getMoneyRequired() {
        return moneyRequired;
    }

    public int getMoneyDonated() {
        return moneyDonated;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

}