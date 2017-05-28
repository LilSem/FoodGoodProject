package com.food_good.lilsem.food_good.model;

public class Basket {

    public Basket(){
    }

    public String key;
    public String userId;
    public String dishId;
    public String price;
    public String restaurantId;
    public String weight;
    public String title;
    public String composition;

    public Basket(String key, String userId, String dishId, String price, String restaurantId, String weight, String title, String composition) {
        this.key = key;
        this.userId = userId;
        this.dishId = dishId;
        this.price = price;
        this.restaurantId = restaurantId;
        this.weight = weight;
        this.title = title;
        this.composition = composition;
    }
}
