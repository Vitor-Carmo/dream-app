package com.example.dream.view;

import androidx.appcompat.app.AppCompatActivity;
import com.example.dream.R;
import com.example.dream.helpers.ImageLoadTask;
import com.example.dream.model.Rate;
import com.example.dream.constant.DreamAppConstants;
import com.example.dream.model.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class RateActivity extends AppCompatActivity {
    Rate rate;
    Room room;
    private Spinner nota_atendimento, nota_acomodacao, nota_recomendacao;
    private final Integer[] rates = new Integer[]{1, 2, 3, 4, 5};
    private String atendimento = "1", acomodacao = "1", recomendacao = "1";
    private EditText comment;
    private ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        Bundle extra = getIntent().getBundleExtra(DreamAppConstants.ROOM_DETAIL);
        room = (Room) extra.getSerializable(DreamAppConstants.ROOM_DETAIL);

        rate = new Rate();
        nota_atendimento = findViewById(R.id.nota_atendimento);
        nota_acomodacao = findViewById(R.id.nota_acomodacao);
        nota_recomendacao = findViewById(R.id.nota_recomendacao);
        foto = findViewById(R.id.foto);
        comment = findViewById(R.id.comment);

        nota_atendimento.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_dropdown_item, rates));
        nota_acomodacao.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_dropdown_item, rates));
        nota_recomendacao.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_dropdown_item, rates));

        new ImageLoadTask(room.getFoto(), foto).execute();

        nota_atendimento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                atendimento = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        nota_acomodacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                acomodacao = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        nota_recomendacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recomendacao = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void rate(View view) {
        try {
            rate.setAcomodacao(acomodacao);
            rate.setAtendimento(atendimento);
            rate.setRecomendacao(recomendacao);
            rate.setObservacao(comment.getText().toString());
            rate.setReserva(room.getReserva());

            rate.create();
            Toast.makeText(this, "Avaliação Feita com sucesso!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, Home.class));
            finish();
        }catch (Exception ex){
            ex.printStackTrace();
            Log.e("Erro", ex.getMessage());
            Toast.makeText(this, "Ocorreu algum Erro! Por favor, volte mais tarde", Toast.LENGTH_LONG).show();
        }
    }

    public void goBack(View view) {
        finish();
    }
}