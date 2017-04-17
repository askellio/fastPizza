package com.example.askellio.pizzaclient.structs;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by askellio on 4/3/17.
 */

public class StructOrder implements Serializable {
    Date date;
    String Address;
    int Id;
    int UserId;
    double Summary;
    List<StructPizzaForPost> Pizzas;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public double getSummary() {
        return Summary;
    }

    public void setSummary(double summary) {
        Summary = summary;
    }

    public List<StructPizzaForPost> getPizzas() {
        return Pizzas;
    }

    public void setPizzas(List<StructPizzaForPost> pizzas) {
        Pizzas = pizzas;
    }
}
