package com.example.ordermaid;

public class OrderClass {
    private String ID;
    private String Name;
    private String Total;

    public OrderClass(String ID, String name, String total) {
        this.ID = ID;
        Name = name;
        Total = total;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }
}
