package com.example.askellio.pizzaclient;

import com.example.askellio.pizzaclient.structs.StructAuth;
import com.example.askellio.pizzaclient.structs.StructOrder;
import com.example.askellio.pizzaclient.structs.StructPizzaForGet;
import com.example.askellio.pizzaclient.structs.StructPizzaFromOrder;
import com.example.askellio.pizzaclient.structs.StructProductsComplex;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by askellio on 4/2/17.
 */

public interface Link {
    @GET("pizzas/{id}")
    Call<StructPizzaForGet> getPizza (@Path("id") int id);

    @GET("pizzas/category/{category}")
    Call<List<StructPizzaForGet>> getPizzasCategory (@Path("category") String category);

    //@POST("orders")
    //Call<StructOrder> addPizza (@Body StructOrder order);

    @GET("users/{id}/orders")
    Call<List<StructOrder>> getOrdersById (@Path("id") int id);

    @POST("users/login")
    Call<Integer> auth (@Body StructAuth obj);


    @GET("users/login")
    Call<StructAuth> logIn (@Header("login") String login, @Header("password") String password, @Header("role") String role);

    @POST("orders")
    //Call<String> addOrder (@Body StructOrder order);
    Call<ResponseBody> addOrder (@Body StructOrder order);

    @GET("products/pizzas/{id}")
    Call<List<StructProductsComplex>> getProductsForPizza (@Path("id") int id);

    @GET("pizzas/orders/{id}")
    Call<List<StructPizzaFromOrder>> getPizzasForOrder (@Path("id") int id);
}
