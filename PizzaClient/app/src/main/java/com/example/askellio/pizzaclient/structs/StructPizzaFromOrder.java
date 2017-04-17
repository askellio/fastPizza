package com.example.askellio.pizzaclient.structs;

/**
 * Created by askellio on 4/17/17.
 */

public class StructPizzaFromOrder {
    int id;
    String Name;
    int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
