package com.food_good.lilsem.food_good.model;


public class Restaurant {

    public int PhotoUri;

    public String title;
    public String kitchen;
    public String salaryPrice;
    public String deliveryPrice;
    public String key;

    public Restaurant(){
    }

    public Restaurant(int photoUri, String title, String kitchen, String salaryPrice, String deliveryPrice, String key) {
        PhotoUri = photoUri;
        this.title = title;
        this.kitchen = kitchen;
        this.salaryPrice = salaryPrice;
        this.deliveryPrice = deliveryPrice;
        this.key = key;
    }
}