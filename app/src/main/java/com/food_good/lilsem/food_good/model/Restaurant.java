package com.food_good.lilsem.food_good.model;


public class Restaurant {

    public String key;
    public String photoLink;
    public String id;
    public String title;
    public String restaurantKitchen;
    public String restaurantSalaryPrice;
    public String restaurantDeliveryPrice;

    public Restaurant(){
    }

    public Restaurant(String key, String photoLink, String id, String title, String restaurantKitchen, String restaurantSalaryPrice, String restaurantDeliveryPrice) {
        this.key = key;
        this.photoLink = photoLink;
        this.id = id;
        this.title = title;
        this.restaurantKitchen = restaurantKitchen;
        this.restaurantSalaryPrice = restaurantSalaryPrice;
        this.restaurantDeliveryPrice = restaurantDeliveryPrice;
    }
}