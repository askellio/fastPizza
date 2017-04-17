package com.example.askellio.pizzaclient.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.askellio.pizzaclient.Cart;
import com.example.askellio.pizzaclient.R;
import com.example.askellio.pizzaclient.activities.InfoOrderActivity;
import com.example.askellio.pizzaclient.structs.StructOrder;

import java.util.List;

/**
 * Created by askellio on 4/14/17.
 */


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {

    public static class OrderHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView id;
        private TextView price;
        private Context _context;
        private StructOrder order;

        OrderHolder(View ItemView, Context context){
            super(ItemView);
            id = (TextView) ItemView.findViewById(R.id.txtOrderId);
            price = (TextView) ItemView.findViewById(R.id.txtOrderPrice);
            _context = context;
            ItemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(_context, InfoOrderActivity.class);
            intent.putExtra("order", order);
        }
    }

    private Context _context;
    List<StructOrder> _orders;

    public OrderAdapter(Context context) {
        _context = context;
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, final int position) {
        holder.id.setText(Integer.toString(holder.order.getId()));
        holder.price.setText(Double.toString(holder.order.getSummary()));
        holder.order = _orders.get(position);
    }

    @Override
    public OrderHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        OrderHolder viewHolder = new OrderHolder(view, _context);

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return _orders.size();
    }

    public void setData (List<StructOrder> orders) {
        this._orders = orders;
    }
}
