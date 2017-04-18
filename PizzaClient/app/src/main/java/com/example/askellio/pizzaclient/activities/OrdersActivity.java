package com.example.askellio.pizzaclient.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.askellio.pizzaclient.ApiHolder;
import com.example.askellio.pizzaclient.R;
import com.example.askellio.pizzaclient.adapters.OrderAdapter;
import com.example.askellio.pizzaclient.adapters.PizzaAdapter;
import com.example.askellio.pizzaclient.interfaces.setOrdersCallBack;
import com.example.askellio.pizzaclient.structs.StructOrder;
import com.example.askellio.pizzaclient.structs.StructPizzaForGet;

import java.util.List;

public class OrdersActivity extends AppCompatActivity {

    private RecyclerView rv;
    private LinearLayoutManager manager;
    private OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        rv = (RecyclerView) findViewById(R.id.rvOrders);

        manager = new LinearLayoutManager(this);

        adapter = new OrderAdapter(this);

        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);

        SharedPreferences sPref = getSharedPreferences("auth", MODE_PRIVATE);
        int id = sPref.getInt("id", 0);

        ApiHolder holder = ApiHolder.getInstance();
        holder.getOrdersById(id, new setOrdersCallBack() {
            @Override
            public void setOrders(List<StructOrder> orders) {
                adapter.setData(orders);
                adapter.notifyDataSetChanged();
            }
        }, this.getApplicationContext());

    }
}
