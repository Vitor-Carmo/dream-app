package com.example.dream.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dream.R;
import com.example.dream.constant.DreamAppConstants;
import com.example.dream.data.SecurityPreferences;
import com.example.dream.helpers.ImageLoadTask;
import com.example.dream.helpers.MoneyFormat;
import com.example.dream.model.Rate;
import com.example.dream.model.Room;

import java.sql.SQLException;

public class ReservationDetail extends AppCompatActivity {
    private Room room;
    private Rate rate;
    private EditText quarto, qtd_hospedes, qtd_cama_solteiros, qtd_cama_casal, valor_total;
    private ImageView foto;
    private Button btn_avaliar_reserva;
    private SecurityPreferences mSecurityPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_made_detail);

        Bundle extra = getIntent().getBundleExtra(DreamAppConstants.ROOM_DETAIL);
        room = (Room) extra.getSerializable(DreamAppConstants.ROOM_DETAIL);


        foto = findViewById(R.id.foto);
        quarto = findViewById(R.id.quarto);
        valor_total = findViewById(R.id.valor_total);
        qtd_hospedes = findViewById(R.id.qtd_hospedes);
        qtd_cama_solteiros = findViewById(R.id.qtd_cama_solteiros);
        qtd_cama_casal = findViewById(R.id.qtd_cama_casal);
        btn_avaliar_reserva = findViewById(R.id.btn_avaliar_reserva);

        quarto.setText(room.getNome());
        valor_total.setText(MoneyFormat.format(room.getValor()));
        qtd_hospedes.setText(String.valueOf(room.getQtdHospede()));
        qtd_cama_casal.setText(String.valueOf(room.getQtdCamaCasal()));
        qtd_cama_solteiros.setText(String.valueOf(room.getQtdCamaSolteiro()));

        new ImageLoadTask(room.getFoto(), foto).execute();

        rate = new Rate();
        mSecurityPreferences = new SecurityPreferences(this);
        checkIfCanGoToRate();
    }

    public void goBack(View view) {
        finish();
    }

    public void goToRate(View view) {
        Intent intent = new Intent(view.getContext(), RateActivity.class);

        Bundle extra = new Bundle();
        extra.putSerializable(DreamAppConstants.ROOM_DETAIL, room);
        intent.putExtra(DreamAppConstants.ROOM_DETAIL, extra);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        checkIfCanGoToRate();
    }

    private void checkIfCanGoToRate(){
        rate.setReserva(room.getReserva());
        try {
            if(rate.hasRated(mSecurityPreferences.getStoredString(DreamAppConstants.LOGIN_ID_KEY))){
                btn_avaliar_reserva.setEnabled(false);
                btn_avaliar_reserva.setText("Reserva já foi avaliada");
                btn_avaliar_reserva.getBackground().setAlpha(128);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}