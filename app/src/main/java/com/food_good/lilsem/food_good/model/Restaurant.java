package com.food_good.lilsem.food_good.model;


public class Restaurant {

    public String key;
    public int PhotoUri;
    public String id;
    public String title;
    public String kitchen;
    public String salaryPrice;
    public String deliveryPrice;

    public Restaurant(){
    }

    public Restaurant(String key, int photoUri, String id, String title, String kitchen, String salaryPrice, String deliveryPrice) {
        this.key = key;
        PhotoUri = photoUri;
        this.id = id;
        this.title = title;
        this.kitchen = kitchen;
        this.salaryPrice = salaryPrice;
        this.deliveryPrice = deliveryPrice;
    }
}