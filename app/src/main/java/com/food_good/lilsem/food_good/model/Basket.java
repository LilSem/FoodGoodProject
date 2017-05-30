package com.food_good.lilsem.food_good.model;

public class Basket {


    public String userId;
    public String dishId;
    public String price;
    public String restaurantId;
    public String weight;
    public String title;
    public String composition;
    public String orderId;
    public String photoLink;


    public Basket(String userId, String dishId, String price, String restaurantId, String weight, String title, String composition, String orderId, String photoLink) {
        this.userId = userId;
        this.dishId = dishId;
        this.price = price;
        this.restaurantId = restaurantId;
        this.weight = weight;
        this.title = title;
        this.composition = composition;
        this.orderId = orderId;
        this.photoLink = photoLink;
    }

    public Basket(){
    }
}
