package com.example.ordermaid;

public class Itemclass {

    private String name;
    private String Price;

    public Itemclass(String name, String price) {
        this.name = name;
        Price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
