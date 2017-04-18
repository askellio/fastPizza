package com.example.askellio.pizzaclient.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.askellio.pizzaclient.ApiHolder;
import com.example.askellio.pizzaclient.R;
import com.example.askellio.pizzaclient.interfaces.setProductsForPizzaCallBack;
import com.example.askellio.pizzaclient.structs.StructPizzaForGet;
import com.example.askellio.pizzaclient.structs.StructProductsComplex;

import java.util.List;

public class InfoPizzaActivity extends AppCompatActivity {

    TextView txtName;
    TextView txtPrice;
    TextView txtWeight;
    TextView txtProducts;
    TextView txtType;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pizza);

        txtName = (TextView) findViewById(R.id.txtInfoName);
        txtPrice = (TextView) findViewById(R.id.txtInfoPrice);
        txtWeight = (TextView) findViewById(R.id.txtInfoWeight);
        txtProducts = (TextView) findViewById(R.id.txtInfoProducts);
        txtType = (TextView) findViewById(R.id.txtInfoType);
        image = (ImageView) findViewById(R.id.imagePizza);

        Intent intent = getIntent();
        StructPizzaForGet pizza = (StructPizzaForGet) intent.getSerializableExtra("pizza");

        String imageUrl = pizza.getImageUrl();

        if (!imageUrl.isEmpty()) {
            Glide.with(this)
                    .load(imageUrl)
                    .into(image);
        }


        txtName.setText(pizza.getName());
        txtPrice.setText(pizza.getPrice()+" руб");
        txtWeight.setText(pizza.getWeight()+" г");
        txtType.setText(pizza.getType());

        ApiHolder holder = ApiHolder.getInstance();
        holder.getProductsForPizza(pizza.getId(), new setProductsForPizzaCallBack() {
            @Override
            public void setProductsForPizza(List<StructProductsComplex> products) {
                StringBuffer buf = new StringBuffer();
                buf.append("Продукты: ");
                for(int i=0; i<products.size(); i++) {
                    if (i==0)
                        buf.append(products.get(i).getName());
                    else
                        buf.append(", "+products.get(i).getName());
                }
                txtProducts.setText(buf.toString());
            }
        }, this.getApplicationContext());
    }
}
