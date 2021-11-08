package com.example.dream.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.dream.R;
import com.example.dream.constant.DreamAppConstants;
import com.example.dream.data.SecurityPreferences;
import com.example.dream.model.Client;
import com.example.dream.model.SpinnerStateCity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.com.sapereaude.maskedEditText.MaskedEditText;

public class ChangeRegistration extends AppCompatActivity {
    private Client client;
    private SecurityPreferences mSecurityPreferences;

    private MaskedEditText celular, cpf, rg, cep;
    private EditText nome, email, endereco, numero, bairro, usuario, complemento;
    private Button dateBtn;
    private SpinnerStateCity spinnerStateCity;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_registration);
        Locale.setDefault(new Locale("pt", "BR"));

        this.mSecurityPreferences = new SecurityPreferences(this);

        nome = findViewById(R.id.nome);
        email = findViewById(R.id.email);
        celular = findViewById(R.id.celular);
        cpf = findViewById(R.id.cpf);
        rg = findViewById(R.id.rg);
        cep = findViewById(R.id.cep);
        endereco = findViewById(R.id.endereco);
        numero = findViewById(R.id.numero);
        bairro = findViewById(R.id.bairro);
        usuario = findViewById(R.id.usuario);
        complemento = findViewById(R.id.complemento);
        usuario = findViewById(R.id.usuario);
        dateBtn = findViewById(R.id.data_nascimento_btn);
        spinnerStateCity = new SpinnerStateCity(this, findViewById(R.id.uf), findViewById(R.id.cidade));

        client = new Client();

        try {
            client.read(mSecurityPreferences.getStoredString(DreamAppConstants.LOGIN_ID_KEY));
            nome.setText(client.getNome());
            email.setText(client.getEmail());
            celular.setText(client.getCelular());
            cpf.setText(client.getCpf());
            rg.setText(client.getRg());
            cep.setText(client.getCpf());
            endereco.setText(client.getEndereco());
            numero.setText(client.getNumero());
            bairro.setText(client.getBairro());
            complemento.setText(client.getComplemento());
            date = client.getData();
            this.setInitialDate();

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    private void setInitialDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.getDefault());
        Date date = format.parse(client.getData());

        SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate = format2.format(date);
        dateBtn.setText(strDate);
    }

    public void goToChangePassword(View view) {
        startActivity(new Intent(view.getContext(), ChangePassword.class));
    }


    public void setDate(View view) {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String strDate = format.format(calendar.getTime());
                dateBtn.setText(strDate);
                date = String.format("%s-%s-%s", year, (month + 1), dayOfMonth);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void goBack(View view) {
        finish();
    }
}