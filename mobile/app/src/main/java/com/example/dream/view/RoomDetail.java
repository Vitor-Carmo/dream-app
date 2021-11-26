package com.example.dream.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.dream.R;
import com.example.dream.constant.DreamAppConstants;
import com.example.dream.helpers.ImageLoadTask;
import com.example.dream.helpers.MoneyFormat;
import com.example.dream.model.CommentAdapter;
import com.example.dream.model.Room;

import java.sql.SQLException;

public class RoomDetail extends AppCompatActivity {
    Room room;
    ImageView foto;
    TextView nome, description, valor, qtd_cama_casal, qtd_cama_solteiros, qtd_hospedes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        Bundle extra = getIntent().getBundleExtra(DreamAppConstants.ROOM_DETAIL);
        room = (Room) extra.getSerializable(DreamAppConstants.ROOM_DETAIL);

        foto = findViewById(R.id.foto);
        nome = findViewById(R.id.nome);
        description = findViewById(R.id.description);
        valor = findViewById(R.id.valor);
        qtd_cama_casal = findViewById(R.id.qtd_cama_casal);
        qtd_cama_solteiros = findViewById(R.id.qtd_cama_solteiros);
        qtd_hospedes = findViewById(R.id.qtd_hospedes);
        nome.setText(room.getNome());
        description.setText(room.getDescricao());
        valor.setText(MoneyFormat.format(room.getValor()));
        new ImageLoadTask(room.getFoto(), foto).execute();
        qtd_cama_casal.setText(String.format("%s cama%s de casal", room.getQtdCamaCasal(), plural(room.getQtdCamaCasal())));
        qtd_cama_solteiros.setText(String.format("%s cama%s de solteiro", room.getQtdCamaSolteiro(), plural(room.getQtdCamaSolteiro())));
        qtd_hospedes.setText(String.format("Acomoda %s pessoa%s", room.getQtdHospede(), plural(room.getQtdHospede())));
        setComments();
    }

    public void goBack(View view) {
        finish();
    }

    public void scrollToComment(View view){
        ScrollView scrollView = findViewById(R.id.room_detail_scroll);
        LinearLayout comments = findViewById(R.id.comments);

        scrollView.post(() -> scrollView.smoothScrollTo(0, comments.getTop() - 30));
    }

    private void setComments(){
        try {
            ListView listview = findViewById(R.id.listview);
            listview.setAdapter(new CommentAdapter(this, room.getComments()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private String plural(int qtd){
        return qtd > 1 ? "s" : "";
    }
    public void goToReservationSummary(View view) {
        Intent intent = new Intent(view.getContext(), ReservationSummary.class);

        Bundle extra = new Bundle();
        extra.putSerializable(DreamAppConstants.ROOM_DETAIL,  room);
        intent.putExtra(DreamAppConstants.ROOM_DETAIL, extra);

        startActivity(intent);
    }
}
