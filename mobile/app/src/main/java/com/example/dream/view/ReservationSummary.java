package com.example.dream.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dream.R;
import com.example.dream.constant.DreamAppConstants;
import com.example.dream.data.SecurityPreferences;
import com.example.dream.helpers.ImageLoadTask;
import com.example.dream.helpers.MoneyFormat;
import com.example.dream.model.Client;
import com.example.dream.model.Companion;
import com.example.dream.model.PaymentType;
import com.example.dream.model.PaymentTypeAdapter;
import com.example.dream.model.Reservation;
import com.example.dream.model.Room;
import com.example.dream.model.SpinnerStateCity;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import br.com.sapereaude.maskedEditText.MaskedEditText;

public class ReservationSummary extends AppCompatActivity {
    private Client client;
    private Room room;
    private PaymentTypeAdapter paymentTypeAdapter;
    private PaymentType paymentType;
    private SecurityPreferences mSecurityPreferences;
    private int numberGuests = 0;
    private MaskedEditText celular, cpf, rg, cep;
    private EditText quarto, valor_total, nome, email, endereco, numero, bairro, complemento, date, cidade, estado, qtd_hospedes, qtd_cama_solteiros, qtd_cama_casal;
    private ImageView foto;
    private Spinner tipo_pagamento;
    private TextView guests, limit;
    private ArrayList<Companion> companions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_summary);

        try {
            Bundle extra = getIntent().getBundleExtra(DreamAppConstants.ROOM_DETAIL);
            room = (Room) extra.getSerializable(DreamAppConstants.ROOM_DETAIL);

            mSecurityPreferences = new SecurityPreferences(this);

            client = new Client();
            paymentType = new PaymentType();

            client.read(mSecurityPreferences.getStoredString(DreamAppConstants.LOGIN_ID_KEY));

            foto = findViewById(R.id.foto);
            quarto = findViewById(R.id.quarto);
            valor_total = findViewById(R.id.valor_total);
            qtd_hospedes = findViewById(R.id.qtd_hospedes);
            qtd_cama_solteiros = findViewById(R.id.qtd_cama_solteiros);
            qtd_cama_casal = findViewById(R.id.qtd_cama_casal);
            tipo_pagamento = findViewById(R.id.tipo_pagamento);

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
            limit = findViewById(R.id.limit);


            quarto.setText(room.getNome());
            valor_total.setText(MoneyFormat.format(room.getValor()));
            qtd_hospedes.setText(String.valueOf(room.getQtdHospede()));
            qtd_cama_casal.setText(String.valueOf(room.getQtdCamaCasal()));
            qtd_cama_solteiros.setText(String.valueOf(room.getQtdCamaSolteiro()));

            new ImageLoadTask(room.getFoto(), foto).execute();
            paymentTypeAdapter = new PaymentTypeAdapter(this, PaymentType.getPaymentsTypes());
            tipo_pagamento.setAdapter(paymentTypeAdapter);

            tipo_pagamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    paymentType = paymentTypeAdapter.getItem(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
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


            guests.setText("0");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.getDefault());
            Date dateF = format.parse(client.getData());

            SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String strDate = format2.format(dateF);
            date.setText(strDate);

        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e("Erro", ex.getMessage());
            Toast.makeText(this, "Ocorreu algum Erro! Por favor, volte mais tarde", Toast.LENGTH_LONG).show();
        }
    }

    public void goBack(View view) {
        finish();
    }

    public void addGuests(View view) {
        if (numberGuests < (room.getQtdHospede() - 1)) {
            numberGuests++;
            guests.setText(String.valueOf(numberGuests));
            addGuestContainer(numberGuests);
        }
    }

    public void removeGuests(View view) {
        if (numberGuests > 0) {
            numberGuests--;
            guests.setText(String.valueOf(numberGuests));
            addGuestContainer(numberGuests);
        }
    }

    private void addGuestContainer(int numberGuests) {
        limit.setText(String.format(Locale.getDefault(), "Você pode incluir até %d acompanhantes", (room.getQtdHospede() - 1) ));

        LinearLayout layout = (LinearLayout) findViewById(R.id.guestsContainer);
        layout.removeAllViews();

        for (int i = 0; i < numberGuests; i++) {
            View child = getLayoutInflater().inflate(R.layout.guest_container, null);
            layout.addView(child);
        }
    }

    public void pay(View view) {
        try {

            Reservation reservation = new Reservation();

            try {
                companions = getCompanionsValues();
            }catch (Exception ex){
                ex.printStackTrace();
                Toast.makeText(this, "Por favor, Preencha todos os campos!", Toast.LENGTH_LONG).show();
                return;
            }

            reservation.setHospede(mSecurityPreferences.getStoredString(DreamAppConstants.LOGIN_ID_KEY));
            reservation.setQtd_acompanhante(numberGuests);
            reservation.setQuarto(room.getId());
            reservation.setTipo_pagamento(paymentType.getId());
            reservation.setTotal(room.getValor());
            reservation.setCompanions(companions);
            reservation.create();


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Reserva feita com sucesso!");
            builder.setMessage("O pagamento da reserva deve ser efetuado no local");
            builder.setCancelable(false);

            builder.setPositiveButton("Ok, Entendi!",(dialog, which) -> {
                startActivity(new Intent(this, Home.class));
                finish();
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } catch (Exception ex) {
            Log.e("Erro", ex.getMessage());
            ex.printStackTrace();
            Toast.makeText(this, "Ocorreu algum Erro! Por favor, volte mais tarde", Toast.LENGTH_LONG).show();
        }

    }


    private ArrayList<Companion> getCompanionsValues() throws Exception {
        LinearLayout layout = (LinearLayout) findViewById(R.id.guestsContainer);
        int count = layout.getChildCount();
        View v = null;
        ArrayList<Companion> companions = new ArrayList<Companion>();
        for (int i = 0; i < count; i++) {
            Companion companion = new Companion();
            v = layout.getChildAt(i);
            EditText nome;
            MaskedEditText cpf;
            nome = v.findViewById(R.id.nome);
            cpf = v.findViewById(R.id.cpf);

            if(nome.getText().toString().equals("") || cpf.getRawText().equals("") ){
                throw new Exception("Preencha todos os inputs!");
            }

            companion.setNome(nome.getText().toString());
            companion.setCpf(cpf.getRawText());

            companions.add(companion);
        }

        return companions;
    }
}