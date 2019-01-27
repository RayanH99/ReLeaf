package com.example.johnnybahama.homeless;

public class soloPost {

    private int price;
    private  int point;
    private int numRequired;
    private int numDonated;
    private String name;
    private String description;
    private String ID;


    public soloPost(int price, int point, int numRequired, int numDonated, String name){
        this.price = price;
        this.point = point;
        this.numRequired = numRequired;
        this.numDonated = numDonated;
        this.name = name;

    }

    public void setPrice(int price){
        this.price = price;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getID() {
        return ID;
    }

    public void setNumRequired(int numRequired) {
        this.numRequired = numRequired;
    }

    public void setNumDonated(int numDonated) {
        this.numDonated = numDonated;
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

    public int getNumRequired() {
        return numRequired;
    }

    public int getNumDonated() {
        return numDonated;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

}
