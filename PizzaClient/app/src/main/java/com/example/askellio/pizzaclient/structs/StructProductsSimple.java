package com.example.askellio.pizzaclient.structs;

import java.io.Serializable;

/**
 * Created by askellio on 4/17/17.
 */

public class StructProductsSimple implements Serializable {
    int IdProduct;
    int Count;

    public int getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(int idProduct) {
        IdProduct = idProduct;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }
}
