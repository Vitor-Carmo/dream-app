package com.example.dream.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dream.R;
import com.example.dream.constant.DreamAppConstants;
import com.example.dream.data.SecurityPreferences;
import com.example.dream.model.Client;

import java.sql.SQLException;

public class ChangePassword extends AppCompatActivity {
    private EditText old_password, new_password, confirm_password;

    private Client client;
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        old_password = findViewById(R.id.old_password);
        new_password = findViewById(R.id.new_password);
        confirm_password = findViewById(R.id.confirm_password);

        client = new Client();
        mSecurityPreferences = new SecurityPreferences(this);

        try {
            client.read(this.mSecurityPreferences.getStoredString(DreamAppConstants.LOGIN_ID_KEY));
        } catch (SQLException ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Ocorreu algum Erro! Por favor, volte mais tarde", Toast.LENGTH_LONG).show();
        }
    }

    public void goBack(View view) {
        finish();
    }


    public void changePassword(View view) {
        String user = client.getUsuario();
        try {
            String id = client.login(user, old_password.getText().toString());
            if (id == null) {
                Toast.makeText(this, "Senha incorreta, Por favor tente novamente", Toast.LENGTH_LONG).show();
                return;
            }

            if(!(new_password.getText().toString().equals(confirm_password.getText().toString()))){
                Toast.makeText(this, "A confirmação da senha não confere.", Toast.LENGTH_LONG).show();
                return;
            }

            client.setSenha(new_password.getText().toString());
            client.updatePassword(this.mSecurityPreferences.getStoredString(DreamAppConstants.LOGIN_ID_KEY));

            Toast.makeText(this, "Senha alterada com sucesso!", Toast.LENGTH_LONG).show();
            finish();
        } catch (Exception exception) {
            exception.printStackTrace();
            Toast.makeText(this, "Ocorreu algum Erro! Por favor, volte mais tarde", Toast.LENGTH_LONG).show();
        }
    }
}