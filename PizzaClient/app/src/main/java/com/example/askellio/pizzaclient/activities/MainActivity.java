package com.example.askellio.pizzaclient.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.askellio.pizzaclient.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showPizza (View v) {
        Intent intent = new Intent (this, PizzaActivity.class);
        String category;

        if (v.getId() == R.id.btnItaly) {
            category = "italic";
        } else
            category = "america";

        //Toast.makeText(this, category, Toast.LENGTH_LONG);
        intent.putExtra("category", category);
        startActivity(intent);
    }

    public void showCart (View v) {
        Intent intent = new Intent (this, CartActivity.class);
        startActivity(intent);
    }

    public void showOrders(View v) {
        Intent intent = new Intent (this, OrdersActivity.class);
        startActivity(intent);
    }

    public void logOut (View v) {
        SharedPreferences sPref = getSharedPreferences("auth", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.remove("login");
        ed.remove("password");
        ed.commit();
        Intent intent = new Intent (this, AuthActivity.class);
        startActivity(intent);
    }
}
