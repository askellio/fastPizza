package com.example.askellio.pizzaclient.structs;

import java.io.Serializable;
import java.util.List;

/**
 * Created by askellio on 4/2/17.
 */


public class StructPizzaForGet implements Serializable {
    private String ImageUrl;
    private String Name;
    private String Type;
    private int Weight;
    private double Price;
    private int Id;

    private List<StructProductsSimple> Products;

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public List<StructProductsSimple> getProducts() {
        return Products;
    }

    public void setProducts(List<StructProductsSimple> products) {
        Products = products;
    }
}
