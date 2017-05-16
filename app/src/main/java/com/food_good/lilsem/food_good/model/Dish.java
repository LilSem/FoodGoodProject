package com.food_good.lilsem.food_good.model;



public class Dish {


    public String title;
    public String composition;
    public String weight;
    public String price;
    public String key;

    public Dish(){
    }

    public Dish(String title, String composition, String weight, String price, String key) {
        this.title = title;
        this.composition = composition;
        this.weight = weight;
        this.price = price;
        this.key = key;
    }
}
