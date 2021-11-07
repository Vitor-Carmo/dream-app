package com.example.dream.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dream.R;
import com.example.dream.data.ConnectionHelper;
import com.example.dream.model.SpinnerStateCity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.sapereaude.maskedEditText.MaskedEditText;

public class Register extends AppCompatActivity {
    private MaskedEditText celular, cpf, rg, cep;
    private EditText nome, email, endereco, numero, bairro, usuario, password, confirm_password, complemento;
    private Spinner generos;
    private Button dateBtn;
    private SpinnerStateCity spinnerStateCity;
    private String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
        password = findViewById(R.id.password);
        complemento = findViewById(R.id.complemento);
        usuario = findViewById(R.id.usuario);
        confirm_password = findViewById(R.id.confirm_password);
        dateBtn = findViewById(R.id.data_nascimento_btn);
        spinnerStateCity = new SpinnerStateCity(this, findViewById(R.id.uf), findViewById(R.id.cidade));
        setGenres();
    }

    public void setGenres() {
        generos = findViewById(R.id.generos);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.generos, R.layout.spinner_dropdown_item);
        generos.setAdapter(adapter);
    }

    public void setDate(View view) {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateBtn.setText(getString(R.string.date_format, dayOfMonth, (month + 1), year));
                date = String.format("%s-%s-%s", year, (month + 1), dayOfMonth);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void goBack(View view) {
        finish();
    }

    public void registerUser(View view) {
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connect = connectionHelper.connectionClass();

        String query = String.format("\n" +
                        "EXEC\t[dbo].[SP_USUARIO_SET]\n" +
                        "\t\t@ID = NULL,\n" +
                        "\t\t@NIVEL = 3,\n" +
                        "\t\t@USUARIO = \"%s\",\n" +
                        "\t\t@SENHA = \"%s\",\n" +
                        "\t\t@STATUS = 1;\n" +
                        "DECLARE @id int\n" +
                        "\n" +
                        "SELECT TOP 1 @id = ID FROM TB_USUARIO ORDER BY ID DESC;\n" +
                        "\n" +
                        "EXEC SP_HOSPEDE_SET @id, \"%s\", \"%s\", \"%s\", \"%s\", \"+55 %s\", \"%s\";\n" +
                        "\n" +
                        "EXEC SP_ENDERECO_SET @id, \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", '%s';",
                usuario.getText(),
                password.getText(),
                nome.getText(),
                cpf.getText(),
                rg.getRawText(),
                date,
                celular.getText(),
                email.getText(),
                cep.getText(),
                spinnerStateCity.getState(),
                spinnerStateCity.getCity(),
                bairro.getText(),
                endereco.getText(),
                numero.getText(),
                complemento.getText()
        );

        Log.e("DATABASE", query);
        /*
            while (rs.next()){
                bairro.setText(rs.getString(2)); // coluna
            }
        */

        try {
            if (connect != null) {
                Statement st = connect.createStatement();
                st.executeQuery(query);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Ocorreu algum Erro! Por favor, volte mais tarde", Toast.LENGTH_LONG).show();
        }


    }

}