package com.example.askellio.pizzaclient;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.askellio.pizzaclient.activities.CartActivity;
import com.example.askellio.pizzaclient.activities.InfoOrderActivity;
import com.example.askellio.pizzaclient.activities.InfoPizzaActivity;
import com.example.askellio.pizzaclient.activities.MainActivity;
import com.example.askellio.pizzaclient.activities.OrdersActivity;
import com.example.askellio.pizzaclient.activities.PizzaActivity;
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

    private Activity currActivity;

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


    public void getPizzasCategory (String category, final Activity activity) {

        currActivity = activity;

        Call<List<StructPizzaForGet>> call = interf.getPizzasCategory(category);

        call.enqueue(new Callback<List<StructPizzaForGet>>() {
            @Override
            public void onResponse(Call<List<StructPizzaForGet>> call, Response<List<StructPizzaForGet>> response) {
                //Toast.makeText(currActivity, "good", Toast.LENGTH_LONG).show();
                ((PizzaActivity) currActivity).setData(response.body());
                //((PizzaActivity) currActivity).
            }

            @Override
            public void onFailure(Call<List<StructPizzaForGet>> call, Throwable t) {
                Toast.makeText(currActivity, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    public void getOrdersById (int id, final Activity activity) {
        currActivity = activity;

        Call<List<StructOrder>> call = interf.getOrdersById(id);

        call.enqueue(new Callback<List<StructOrder>>() {
            @Override
            public void onResponse(Call<List<StructOrder>> call, Response<List<StructOrder>> response) {
                //Toast.makeText(currActivity, "good", Toast.LENGTH_LONG).show();
                ((OrdersActivity) currActivity).setData(response.body());
            }

            @Override
            public void onFailure(Call<List<StructOrder>> call, Throwable t) {
                Toast.makeText(currActivity, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    //public void auth (final StructAuth obj, final boolean flag, final Activity activity) {
    public void auth (final String login, final String password, String role, final boolean flag, final Activity activity) {
        currActivity = activity;

        //Call<Integer> call = interf.auth(obj);
        Call<StructAuth> call = interf.logIn(login, password, role);
        call.enqueue(new Callback<StructAuth>() {
            @Override
            public void onResponse(Call<StructAuth> call, Response<StructAuth> response) {
                int id = response.body().getId();

                SharedPreferences sPref = currActivity.getSharedPreferences("auth", currActivity.MODE_PRIVATE);
                SharedPreferences.Editor ed = sPref.edit();
                ed.putInt("id", id);
                if (flag) {
                    /*ed.putString("login", obj.getLogin());
                    ed.putString("password", obj.getPassword());*/
                    ed.putString("login", login);
                    ed.putString("password", password);
                }
                ed.commit();


                Intent intent = new Intent(currActivity, MainActivity.class);
                currActivity.startActivity(intent);
            }

            @Override
            public void onFailure(Call<StructAuth> call, Throwable t) {
                Toast.makeText(currActivity, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void doOrder (StructOrder order, final Activity activity) {
        currActivity = activity;
        Call<ResponseBody> call = interf.addOrder(order);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(currActivity, "Заказ выполнен", Toast.LENGTH_LONG);
                Cart cart = Cart.getInstance();
                cart.clear();
                ((CartActivity) currActivity).setData();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(currActivity, t.getMessage(), Toast.LENGTH_LONG);
            }
        });
    }

    public void getProductsForPizza (int id, final Activity activity) {
        currActivity = activity;
        Call<List<StructProductsComplex>> call = interf.getProductsForPizza(id);
        call.enqueue(new Callback<List<StructProductsComplex>>() {
            @Override
            public void onResponse(Call<List<StructProductsComplex>> call, Response<List<StructProductsComplex>> response) {
                ((InfoPizzaActivity) currActivity).setProducts(response.body());
            }

            @Override
            public void onFailure(Call<List<StructProductsComplex>> call, Throwable t) {
                Toast.makeText(currActivity, "Не получилось загрузить список продуктов", Toast.LENGTH_LONG);
            }
        });
    }

    public void getPizzasByOrderId (int id, final Activity activity) {
        currActivity = activity;

        Call<List<StructPizzaFromOrder>> call = interf.getPizzasForOrder(id);

        call.enqueue(new Callback<List<StructPizzaFromOrder>>() {
            @Override
            public void onResponse(Call<List<StructPizzaFromOrder>> call, Response<List<StructPizzaFromOrder>> response) {
                Toast.makeText(currActivity, "good", Toast.LENGTH_LONG).show();
                ((InfoOrderActivity) currActivity).setData(response.body());
            }

            @Override
            public void onFailure(Call<List<StructPizzaFromOrder>> call, Throwable t) {
                Toast.makeText(currActivity, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}
