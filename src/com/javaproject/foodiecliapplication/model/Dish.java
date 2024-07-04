package com.javaproject.foodiecliapplication.model;

import java.util.Objects;

public class Dish {


         /*
    add the following properties
    --------------------------------------
    Datatype                  variable
    --------------------------------------
    String                      id
    String                      name
    String                      description
    double                      price
     */

    /*
    1. All the fields should be private
    2. Create only no-arg constructor
    3. Create Getters and Setter methods
    4. Override hashCode() and equals() methods
    5. Override toString() methods
     */



    private String id;
    private String name;
    private String description;
    double price;

    public Dish() {
    }

    public Dish setId(String id) {
        this.id = id;
        return this;
    }
    public String getId(){
        return id;
    }

    public Dish setName(String name){
        this.name = name;
        return this;
    }
    public String getName(){
        return name;
    }
    public Dish setDescription(String description){
        this.description = description;
        return this;
    }
    public String getDescription(){
        return description;
    }

    public Dish setPrice(double price){
        this.price = price;
        return this;
    }

    public double getPrice(){
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Double.compare(price, dish.price) == 0 &&
                Objects.equals(id, dish.id) &&
                Objects.equals(name, dish.name) &&
                Objects.equals(description, dish.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

}
