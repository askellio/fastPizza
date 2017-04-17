package com.example.askellio.pizzaclient.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.askellio.pizzaclient.Cart;
import com.example.askellio.pizzaclient.R;

import pl.polak.clicknumberpicker.ClickNumberPickerListener;
import pl.polak.clicknumberpicker.ClickNumberPickerView;
import pl.polak.clicknumberpicker.PickerClickType;


/**
 * Created by askellio on 4/3/17.
 */



public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {

    public static class CartHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        //private EditText count;
        private ClickNumberPickerView count;
        //private NumberPicker count;

        private Button btnRemove;

        private Context _context;
        private int id;

        CartHolder(View ItemView, Context context){
            super(ItemView);
            name = (TextView) ItemView.findViewById(R.id.txtCartPizzaName);
            //count = (NumberPicker) ItemView.findViewById(R.id.count);
            count = (ClickNumberPickerView) ItemView.findViewById(R.id.count);
            //count = (EditText) ItemView.findViewById(R.id.count);
            btnRemove = (Button) ItemView.findViewById(R.id.btnRemove);
            _context = context;
            ItemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    private Context _context;

    public CartAdapter(Context context) {
        this._context = context;
    }

    @Override
    public void onBindViewHolder(CartHolder holder, final int position) {

        Cart cart = Cart.getInstance();

        holder.name.setText(cart.getNames().get(position));

        holder.count.setPickerValue(cart.getCounts().get(position));
        holder.count.setClickNumberPickerListener(new ClickNumberPickerListener() {
            @Override
            public void onValueChange(float previousValue, float currentValue, PickerClickType pickerClickType) {
                Cart cart = Cart.getInstance();
                cart.setCount(position, (int) currentValue);
            }
        });
        holder.id = cart.getIds().get(position);

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart cart = Cart.getInstance();
                cart.removePizza(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
            }
        });
    }

    @Override
    public CartHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        CartHolder viewHolder = new CartHolder(view, _context);

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        Cart cart = Cart.getInstance();
        return cart.getIds().size();
    }
}
