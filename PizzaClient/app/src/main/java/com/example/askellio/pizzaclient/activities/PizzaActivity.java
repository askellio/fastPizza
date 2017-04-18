package com.example.askellio.pizzaclient.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.askellio.pizzaclient.ApiHolder;
import com.example.askellio.pizzaclient.adapters.PizzaAdapter;
import com.example.askellio.pizzaclient.R;
import com.example.askellio.pizzaclient.interfaces.setPizzasCallBack;
import com.example.askellio.pizzaclient.structs.StructPizzaForGet;

import java.util.List;

public class PizzaActivity extends AppCompatActivity {

    private RecyclerView rv;
    private LinearLayoutManager manager;
    private PizzaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        rv = (RecyclerView) findViewById(R.id.listRV);

        manager = new LinearLayoutManager(this);

        adapter = new PizzaAdapter(this);

        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);

        String category;
        Intent intent = getIntent();
        if (intent != null) {
            category = intent.getStringExtra("category");
            ApiHolder holder = ApiHolder.getInstance();
            holder.getPizzasCategory(category, new setPizzasCallBack() {
                @Override
                public void setPizzas(List<StructPizzaForGet> pizzas) {
                    adapter.setData(pizzas);
                    adapter.notifyDataSetChanged();
                }
            }, this.getApplicationContext());
        }
    }
}
