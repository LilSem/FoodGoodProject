package com.food_good.lilsem.food_good.model;


public class Event {

    public String key;
    public String restaurantId;
    public String title;
    public String photoLink;

    public Event(String key, String restaurantId, String title, String photoLink) {
        this.key = key;
        this.restaurantId = restaurantId;
        this.title = title;
        this.photoLink = photoLink;
    }

    public Event(){
    }
}
