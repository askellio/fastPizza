package com.example.askellio.pizzaclient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.askellio.pizzaclient.activities.CartActivity;
import com.example.askellio.pizzaclient.activities.InfoOrderActivity;
import com.example.askellio.pizzaclient.activities.InfoPizzaActivity;
import com.example.askellio.pizzaclient.activities.MainActivity;
import com.example.askellio.pizzaclient.activities.OrdersActivity;
import com.example.askellio.pizzaclient.activities.PizzaActivity;
import com.example.askellio.pizzaclient.interfaces.setCartCallBack;
import com.example.askellio.pizzaclient.interfaces.setOrdersCallBack;
import com.example.askellio.pizzaclient.interfaces.setPizzasCallBack;
import com.example.askellio.pizzaclient.interfaces.setPizzasForOrderCallBack;
import com.example.askellio.pizzaclient.interfaces.setProductsForPizzaCallBack;
import com.example.askellio.pizzaclient.structs.StructAuth;
import com.example.askellio.pizzaclient.structs.StructOrder;
import com.example.askellio.pizzaclient.structs.StructPizzaForGet;
import com.example.askellio.pizzaclient.structs.StructPizzaFromOrder;
import com.example.askellio.pizzaclient.structs.StructProductsComplex;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by askellio on 4/3/17.
 */

public class ApiHolder {

    private final String URL = "http://192.168.137.1/";

    private Gson gson = new GsonBuilder().create();

    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(URL)
            .build();

    private Link interf = retrofit.create(Link.class);

    private static final ApiHolder holder = new ApiHolder();

    public static ApiHolder getInstance() {
        return holder;
    }


    public void getPizzasCategory (String category, final setPizzasCallBack callback, final Context context) {

        Call<List<StructPizzaForGet>> call = interf.getPizzasCategory(category);

        call.enqueue(new Callback<List<StructPizzaForGet>>() {
            @Override
            public void onResponse(Call<List<StructPizzaForGet>> call, Response<List<StructPizzaForGet>> response) {
                callback.setPizzas(response.body());
            }

            @Override
            public void onFailure(Call<List<StructPizzaForGet>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    public void getOrdersById (int id, final setOrdersCallBack callback, final Context context) {

        Call<List<StructOrder>> call = interf.getOrdersById(id);

        call.enqueue(new Callback<List<StructOrder>>() {
            @Override
            public void onResponse(Call<List<StructOrder>> call, Response<List<StructOrder>> response) {
                callback.setOrders(response.body());
            }

            @Override
            public void onFailure(Call<List<StructOrder>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    //public void auth (final StructAuth obj, final boolean flag, final Activity activity) {
    public void auth (final String login, final String password, String role, final boolean flag, final Context context) {

        //Call<Integer> call = interf.auth(obj);
        Call<StructAuth> call = interf.logIn(login, password, role);
        call.enqueue(new Callback<StructAuth>() {
            @Override
            public void onResponse(Call<StructAuth> call, Response<StructAuth> response) {
                int id = response.body().getId();

                SharedPreferences sPref = context.getSharedPreferences("auth", context.MODE_PRIVATE);
                SharedPreferences.Editor ed = sPref.edit();
                ed.putInt("id", id);
                if (flag) {
                    /*ed.putString("login", obj.getLogin());
                    ed.putString("password", obj.getPassword());*/
                    ed.putString("login", login);
                    ed.putString("password", password);
                }
                ed.commit();

                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<StructAuth> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void doOrder (StructOrder order, final setCartCallBack callBack, final Context context) {

        Call<ResponseBody> call = interf.addOrder(order);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(context, "Заказ выполнен", Toast.LENGTH_LONG).show();
                Cart cart = Cart.getInstance();
                cart.clear();
                callBack.setCart();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getProductsForPizza (int id, final setProductsForPizzaCallBack callback, final Context context) {

        Call<List<StructProductsComplex>> call = interf.getProductsForPizza(id);
        call.enqueue(new Callback<List<StructProductsComplex>>() {
            @Override
            public void onResponse(Call<List<StructProductsComplex>> call, Response<List<StructProductsComplex>> response) {
                callback.setProductsForPizza(response.body());
            }

            @Override
            public void onFailure(Call<List<StructProductsComplex>> call, Throwable t) {
                Toast.makeText(context, "Не получилось загрузить список продуктов", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getPizzasByOrderId (int id, final setPizzasForOrderCallBack callBack, final Context context) {

        Call<List<StructPizzaFromOrder>> call = interf.getPizzasForOrder(id);

        call.enqueue(new Callback<List<StructPizzaFromOrder>>() {
            @Override
            public void onResponse(Call<List<StructPizzaFromOrder>> call, Response<List<StructPizzaFromOrder>> response) {
                Toast.makeText(context, "good", Toast.LENGTH_LONG).show();
                callBack.setPizzasForOrder(response.body());
            }

            @Override
            public void onFailure(Call<List<StructPizzaFromOrder>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}
