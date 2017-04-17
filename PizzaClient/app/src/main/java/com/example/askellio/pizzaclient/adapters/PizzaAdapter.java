package com.example.askellio.pizzaclient.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.askellio.pizzaclient.Cart;
import com.example.askellio.pizzaclient.R;
import com.example.askellio.pizzaclient.activities.InfoPizzaActivity;
import com.example.askellio.pizzaclient.structs.StructPizzaForGet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by askellio on 4/1/17.
 */


public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaHolder> {

    public static class PizzaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private TextView price;
        private Button btnAdd;
        private Button btnMore;
        private Context _context;
        private StructPizzaForGet pizza;



        PizzaHolder(View ItemView, Context context){
            super(ItemView);
            name = (TextView) ItemView.findViewById(R.id.txtPizzaName);
            price = (TextView) ItemView.findViewById(R.id.txtPizzaPrice);
            btnAdd = (Button) ItemView.findViewById(R.id.btnAddToCart);
            btnMore = (Button) ItemView.findViewById(R.id.btnMore);
            _context = context;
            ItemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            /*
            Intent intent = new Intent(_context, InfoPizzaActivity.class);
            intent.putExtra("pizza", pizza);
            */
        }
    }

    private Context _context;
    private List<StructPizzaForGet> pizzas;

    public PizzaAdapter(Context context) {
        this._context = context;
        this.pizzas = new ArrayList<StructPizzaForGet>();
    }

    @Override
    public void onBindViewHolder(PizzaHolder holder, final int position) {
        StructPizzaForGet item = pizzas.get(position);
        holder.name.setText(item.getName());
        holder.price.setText(String.valueOf(item.getPrice()));

        holder.pizza = item;

        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(_context, InfoPizzaActivity.class);
                intent.putExtra("pizza", pizzas.get(position));
                _context.startActivity(intent);
            }
        });

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart cart = Cart.getInstance();
                StructPizzaForGet pizza = pizzas.get(position);
                cart.addPizza(pizza.getId(), pizza.getName());
                Toast.makeText(_context, "Добавлено в корзину", Toast.LENGTH_SHORT);
            }
        });
    }

    public void setData (List<StructPizzaForGet> pizzas) {
        this.pizzas = pizzas;
    }


    @Override
    public PizzaHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pizza, parent, false);
        PizzaHolder viewHolder = new PizzaHolder(view, _context);

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }
}
