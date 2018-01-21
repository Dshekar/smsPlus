package com.example.rajashekarreddy.sample1;

import java.util.Date;

public class Product {

    private int id;
    private String name;
    private String date;
    private String description;

    public Product(int id, String name,  String description,String date) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
