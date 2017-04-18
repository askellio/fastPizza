package com.example.askellio.pizzaclient.interfaces;

import com.example.askellio.pizzaclient.structs.StructPizzaFromOrder;

import java.util.List;

/**
 * Created by askellio on 4/18/17.
 */

public interface setPizzasForOrderCallBack {
    public void setPizzasForOrder(List<StructPizzaFromOrder> pizzas);
}
