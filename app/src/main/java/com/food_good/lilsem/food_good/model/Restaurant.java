package com.food_good.lilsem.food_good.model;


public class Restaurant {

    int PhotoUri;
    String Name;
    String Kitchen;
    String Salary;

    public int getPhotoUri() {
        return PhotoUri;
    }

    public void setPhotoUri(int photoUri) {
        PhotoUri = photoUri;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getKitchen() {
        return Kitchen;
    }

    public void setKitchen(String kitchen) {
        Kitchen = kitchen;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getDelivery() {
        return Delivery;
    }

    public void setDelivery(String delivery) {
        Delivery = delivery;
    }

    String Delivery;

    public Restaurant(int photoUri, String name, String kitchen, String salary, String delivery) {
        PhotoUri = photoUri;
        Name = name;
        Kitchen = kitchen;
        Salary = salary;
        Delivery = delivery;
    }

}