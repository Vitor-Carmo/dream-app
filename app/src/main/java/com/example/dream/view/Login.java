package com.example.dream.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dream.R;
import com.example.dream.constant.DreamAppConstants;
import com.example.dream.data.SecurityPreferences;
import com.example.dream.model.Client;

public class Login extends AppCompatActivity {
    private EditText user, password;
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.user);
        password = findViewById(R.id.password);

        this.mSecurityPreferences = new SecurityPreferences(this);
    }

    public void goToRegister(View view) {
        startActivity(new Intent(view.getContext(), Register.class));
    }

    public void login(View view) {
        Client client = new Client();
        try {
            String id = client.login(user.getText().toString(), password.getText().toString());
            if (id == null) {
                Toast.makeText(this, "Usuário ou senha incorreta, Por favor tente novamente", Toast.LENGTH_LONG).show();
                return;
            }

            this.mSecurityPreferences.storeString(DreamAppConstants.LOGIN_ID_KEY, id);

            Toast.makeText(this, "Usuário Logado com sucesso", Toast.LENGTH_LONG).show();
            startActivity(new Intent(view.getContext(), Home.class));
            finish();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }


}