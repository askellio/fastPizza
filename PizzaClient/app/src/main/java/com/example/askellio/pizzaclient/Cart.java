package com.example.askellio.pizzaclient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by askellio on 4/3/17.
 */

public class Cart {

    private static Cart cart = new Cart();

    public static Cart getInstance() {
        return cart;
    }

    private List<Integer> ids;
    private List<Integer> counts;
    private List<String> names;

    private Cart() {
        ids =  new ArrayList<Integer>();
        counts =  new ArrayList<Integer>();
        names = new ArrayList<String>();
    }

    public void addPizza(int number, String name) {
        for (int i=0; i<ids.size(); i++) {
            if (ids.get(i) == number) {
                counts.set(i, counts.get(i)+1);
                return;
            }
        }

        ids.add(number);
        names.add(name);
        counts.add(1);
    }

    public void removePizza (int position) {
        ids.remove(position);
        counts.remove(position);
        names.remove(position);
    }

    public List<Integer> getIds() {
        return ids;
    }

    public List<Integer> getCounts() {
        return counts;
    }

    public List<String> getNames() {
        return names;
    }

    public void setCount (int position, Integer count) {
        counts.set(position, count);
    }

    public void clear () {
        ids.clear();
        counts.clear();
        names.clear();
    }
}