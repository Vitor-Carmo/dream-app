package com.example.dream.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dream.R;
import com.example.dream.constant.DreamAppConstants;
import com.example.dream.data.SecurityPreferences;
import com.example.dream.model.Client;
import com.example.dream.model.SpinnerStateCity;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.sapereaude.maskedEditText.MaskedEditText;

public class ReservationSummary extends AppCompatActivity {
    Client client;
    SecurityPreferences mSecurityPreferences;
    int numberGuests = 1;
    private MaskedEditText celular, cpf, rg, cep;
    private EditText nome, email, endereco, numero, bairro, complemento, date, cidade, estado;

    TextView guests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_summary);

        try {

            mSecurityPreferences = new SecurityPreferences(this);
            client = new Client();
            client.read(mSecurityPreferences.getStoredString(DreamAppConstants.LOGIN_ID_KEY));

            guests = findViewById(R.id.guests);
            nome = findViewById(R.id.nome);
            email = findViewById(R.id.email);
            celular = findViewById(R.id.celular);
            cpf = findViewById(R.id.cpf);
            rg = findViewById(R.id.rg);
            cep = findViewById(R.id.cep);
            endereco = findViewById(R.id.endereco);
            numero = findViewById(R.id.numero);
            bairro = findViewById(R.id.bairro);
            complemento = findViewById(R.id.complemento);
            cidade = findViewById(R.id.cidade);
            estado = findViewById(R.id.uf);
            date = findViewById(R.id.data_nascimento_btn);


            nome.setText(client.getNome());
            email.setText(client.getEmail());
            celular.setText(client.getCelular());
            cpf.setText(client.getCpf());
            rg.setText(client.getRg());
            cep.setText(client.getCep());
            endereco.setText(client.getEndereco());
            numero.setText(client.getNumero());
            bairro.setText(client.getBairro());
            complemento.setText(client.getComplemento());
            cidade.setText(client.getCidade());
            estado.setText(client.getEstado());

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.getDefault());
            Date dateF = format.parse(client.getData());

            SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String strDate = format2.format(dateF);
            date.setText(strDate);

            addGuestContainer(1);
        }  catch (Exception ex) {
            ex.printStackTrace();
            Log.e("Erro", ex.getMessage());
            Toast.makeText(this, "Ocorreu algum Erro! Por favor, volte mais tarde", Toast.LENGTH_LONG).show();
        }
    }

    public void goBack(View view) {
        finish();
    }

    public void addGuests(View view) {
        if (numberGuests < 3) {
            numberGuests++;
            guests.setText(String.valueOf(numberGuests));
            addGuestContainer(numberGuests);
        }
    }

    public void removeGuests(View view) {
        if (numberGuests > 1) {
            numberGuests--;
            guests.setText(String.valueOf(numberGuests));
            addGuestContainer(numberGuests);
        }
    }

    private void addGuestContainer(int numberGuests) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.guestsContainer);
        layout.removeAllViews();

        for (int i = 0; i < numberGuests; i++) {
            View child = getLayoutInflater().inflate(R.layout.guest_container, null);
            layout.addView(child);
        }
    }
}