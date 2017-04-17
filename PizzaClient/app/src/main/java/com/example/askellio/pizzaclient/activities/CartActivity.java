package com.example.askellio.pizzaclient.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.askellio.pizzaclient.ApiHolder;
import com.example.askellio.pizzaclient.Cart;
import com.example.askellio.pizzaclient.adapters.CartAdapter;
import com.example.askellio.pizzaclient.R;
import com.example.askellio.pizzaclient.structs.StructOrder;
import com.example.askellio.pizzaclient.structs.StructPizzaForPost;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private EditText editAddress;
    private RecyclerView rv;
    private LinearLayoutManager manager;
    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        editAddress = (EditText) findViewById(R.id.editAddress);
        rv = (RecyclerView) findViewById(R.id.cartRV);

        manager = new LinearLayoutManager(this);

        adapter = new CartAdapter(this);

        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);

        /*Cart cart = Cart.getInstance();
        cart.addPizza(33, "Американка");
        cart.addPizza(44, "Чилийка");
        cart.addPizza(44, "Чилийка");
        cart.addPizza(55, "пуэрто-рика");
        */
        setData();
    }

    public void doOrder(View v) {
        Cart cart = Cart.getInstance();
        String address = editAddress.getText().toString();
        StructOrder order = new StructOrder();
        order.setAddress(address);

        SharedPreferences sPref = getSharedPreferences("auth", MODE_PRIVATE);
        int userId = sPref.getInt("id", 0);

        order.setUserId(userId);

        //List<StructPizzaForPost> pizzas = new ArrayList<StructPizzaForPost>(cart.getCounts().size());
        List<StructPizzaForPost> pizzas = new ArrayList<StructPizzaForPost>();

        for (int i=0; i<cart.getIds().size(); i++) {
            StructPizzaForPost pizza = new StructPizzaForPost();
            pizza.setCount(cart.getCounts().get(i));
            pizza.setIdPizza(cart.getIds().get(i));
            pizzas.add(pizza);
        }

        order.setPizzas(pizzas);
        ApiHolder holder = ApiHolder.getInstance();
        holder.doOrder(order, this);
    }


    public void findAddress (View v) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void setData () {
        adapter.notifyDataSetChanged();
    }
}
