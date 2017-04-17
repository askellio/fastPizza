package com.example.askellio.pizzaclient.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.askellio.pizzaclient.R;
import com.example.askellio.pizzaclient.activities.InfoOrderActivity;
import com.example.askellio.pizzaclient.structs.StructOrder;
import com.example.askellio.pizzaclient.structs.StructPizzaForPost;
import com.example.askellio.pizzaclient.structs.StructPizzaFromOrder;

import java.util.List;

/**
 * Created by askellio on 4/17/17.
 */


public class PizzaFromOrderAdapter extends RecyclerView.Adapter<PizzaFromOrderAdapter.PizzaFromOrderHolder> {

    public static class PizzaFromOrderHolder extends RecyclerView.ViewHolder  {
        private TextView name;
        private TextView count;
        private Context _context;
        private StructPizzaFromOrder pizza;

        PizzaFromOrderHolder(View ItemView, Context context){
            super(ItemView);
            name = (TextView) ItemView.findViewById(R.id.txtPizzaFromOrderName);
            count = (TextView) ItemView.findViewById(R.id.txtPizzaFromOrderCount);
            _context = context;
        }
    }

    private Context _context;
    List<StructPizzaFromOrder> _pizzas;

    public PizzaFromOrderAdapter(Context context) {
        _context = context;
    }

    @Override
    public void onBindViewHolder(PizzaFromOrderHolder holder, final int position) {
        holder.name.setText(holder.pizza.getName());
        holder.count.setText(Integer.toString(holder.pizza.getCount()));
        holder.pizza = _pizzas.get(position);
    }

    @Override
    public PizzaFromOrderHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pizza_from_order, parent, false);
        PizzaFromOrderHolder viewHolder = new PizzaFromOrderHolder(view, _context);

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return _pizzas.size();
    }

    public void setData (List<StructPizzaFromOrder> pizzas) {
        this._pizzas = pizzas;
    }
}
