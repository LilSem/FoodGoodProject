package com.food_good.lilsem.food_good.model;


public class Event {

    public String key;
    public int ivEvent;
    public String restaurantId;
    public String title;

    public Event(String key, int ivEvent, String restaurantId, String title) {
        this.key = key;
        this.ivEvent = ivEvent;
        this.restaurantId = restaurantId;
        this.title = title;
    }

    public Event(){
    }
}
