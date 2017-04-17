package com.example.askellio.pizzaclient.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.askellio.pizzaclient.ApiHolder;
import com.example.askellio.pizzaclient.R;
import com.example.askellio.pizzaclient.structs.StructAuth;

public class AuthActivity extends AppCompatActivity {

    TextView txtLogin;
    TextView txtPassword;
    CheckedTextView chText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        txtLogin = (TextView) findViewById(R.id.login);
        txtPassword = (TextView) findViewById(R.id.password);
        chText = (CheckedTextView) findViewById(R.id.chText);

        SharedPreferences sPref = getSharedPreferences("auth", MODE_PRIVATE);
        String login = sPref.getString("login", "");
        String password = sPref.getString("password", "");

        if (!(login.isEmpty() && password.isEmpty())) {
            ApiHolder holder = ApiHolder.getInstance();
            //StructAuth obj = new StructAuth();
            //obj.setLogin(login);
            //obj.setPassword(password);

            //holder.auth(obj, true, this);
            holder.auth(login, password, "user", true, this);
        }
    }

    public void auth (View v) {
        String login = txtLogin.getText().toString();
        String password = txtPassword.getText().toString();

        if (login.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Заполните поля", Toast.LENGTH_LONG).show();
            return;
        }

        ApiHolder holder = ApiHolder.getInstance();
        //StructAuth obj = new StructAuth();
        //obj.setLogin(login);
        //obj.setPassword(password);

        boolean flag = chText.isChecked();

        //holder.auth(obj, flag, this);
        holder.auth(login, password, "user", true, this);
    }
}
