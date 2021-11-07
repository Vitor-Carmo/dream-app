package com.example.dream.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.dream.R;

import java.util.Calendar;
import java.util.Locale;

import br.com.sapereaude.maskedEditText.MaskedEditText;

public class ChangeRegistration extends AppCompatActivity {
    private MaskedEditText telefone, celular, cpf, rg, cep;
    private EditText nome, email, endereco, numero, bairro, cidade;
    private Spinner generos, uf;

    private Button dateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_registration);
        Locale.setDefault(new Locale("pt", "BR"));

        nome = findViewById(R.id.nome);
        email = findViewById(R.id.email);
        telefone = findViewById(R.id.telefone);
        celular = findViewById(R.id.celular);
        cpf = findViewById(R.id.cpf);
        rg = findViewById(R.id.rg);
        cep = findViewById(R.id.cep);
        endereco = findViewById(R.id.endereco);
        numero = findViewById(R.id.numero);
        bairro = findViewById(R.id.bairro);

        dateBtn = findViewById(R.id.data_nascimento_btn);
        setGenres();
        setUF();
    }

    public void goToChangePassword(View view) {
        startActivity(new Intent(view.getContext(), ChangePassword.class));
    }


    public void setGenres() {
        generos = findViewById(R.id.generos);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.generos, R.layout.spinner_dropdown_item);
        generos.setAdapter(adapter);
    }


    public void setUF() {
        uf = findViewById(R.id.uf);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.UF, R.layout.spinner_dropdown_item);
        uf.setAdapter(adapter);
    }

    public void setDate(View view) {
        Calendar calendar =Calendar.getInstance();
        int month= calendar.get(Calendar.MONTH);
        int day= calendar.get(Calendar.DAY_OF_MONTH);
        int year= calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateBtn.setText( getString(R.string.date_format, dayOfMonth, (month + 1), year));
            }
        },year, month, day);
        datePickerDialog.show();
    }




    public void goBack(View view) {
        finish();
    }
}