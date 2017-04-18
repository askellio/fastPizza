package com.example.askellio.pizzaclient.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.askellio.pizzaclient.ApiHolder;
import com.example.askellio.pizzaclient.R;
import com.example.askellio.pizzaclient.adapters.OrderAdapter;
import com.example.askellio.pizzaclient.adapters.PizzaFromOrderAdapter;
import com.example.askellio.pizzaclient.interfaces.setPizzasForOrderCallBack;
import com.example.askellio.pizzaclient.structs.StructOrder;
import com.example.askellio.pizzaclient.structs.StructPizzaFromOrder;

import java.util.List;

public class InfoOrderActivity extends AppCompatActivity {

    private StructOrder order;

    private RecyclerView rv;
    private LinearLayoutManager manager;
    private PizzaFromOrderAdapter adapter;

    TextView txtSummary;
    TextView txtDate;
    TextView txtAddress;
    TextView txtId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_order);

        Intent intent = getIntent();
        if (intent != null) {
            order = (StructOrder) intent.getSerializableExtra("order");

            rv = (RecyclerView) findViewById(R.id.rvPizzaFromOrder);

            manager = new LinearLayoutManager(this);

            adapter = new PizzaFromOrderAdapter(this);

            rv.setLayoutManager(manager);
            rv.setAdapter(adapter);

            txtId = (TextView) findViewById(R.id.txtOrderId);
            txtSummary = (TextView) findViewById(R.id.txtOrderSummary);
            txtDate = (TextView) findViewById(R.id.txtOrderDate);
            txtAddress = (TextView) findViewById(R.id.txtOrderAddress);

            txtId.setText(Integer.toString(order.getId()));
            txtAddress.setText(order.getAddress());
            txtDate.setText(order.getDate().toString());
            txtSummary.setText(Double.toString(order.getSummary()));

            ApiHolder holder = ApiHolder.getInstance();
            holder.getPizzasByOrderId(order.getId(), new setPizzasForOrderCallBack() {
                @Override
                public void setPizzasForOrder(List<StructPizzaFromOrder> pizzas) {
                    adapter.setData(pizzas);
                    adapter.notifyDataSetChanged();
                }
            }, this.getApplicationContext());
        }
    }
}
