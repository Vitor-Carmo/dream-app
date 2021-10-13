package com.example.dream.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dream.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goToRegister(View view) {
        startActivity(new Intent(view.getContext(), Register.class));
    }

    public void login(View view) {
        startActivity(new Intent(view.getContext(), Home.class));
    }


}