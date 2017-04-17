package com.example.askellio.pizzaclient.structs;

import java.io.Serializable;

/**
 * Created by askellio on 4/3/17.
 */

public class StructPizzaForPost  implements Serializable {
    int IdPizza;
    int Count;

    public int getIdPizza() {
        return IdPizza;
    }

    public void setIdPizza(int IdPizza) {
        this.IdPizza = IdPizza;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }
}
