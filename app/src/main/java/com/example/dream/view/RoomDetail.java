package com.example.dream.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.dream.R;
import com.example.dream.constant.DreamAppConstants;
import com.example.dream.helpers.ImageLoadTask;
import com.example.dream.helpers.MoneyFormat;
import com.example.dream.model.Comment;
import com.example.dream.model.CommentAdapter;
import com.example.dream.model.Room;
import com.example.dream.model.RoomAdapter;

public class RoomDetail extends AppCompatActivity {
    Room room;
    ImageView foto;
    TextView nome, description, valor;

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

        nome.setText(room.getNome());
        description.setText(room.getDescricao());
        valor.setText(MoneyFormat.format(room.getValor()));
        new ImageLoadTask(room.getFoto(), foto).execute();

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
        ListView listview = findViewById(R.id.listview);
        Comment comment = new Comment();
        listview.setAdapter(new CommentAdapter(this, comment.comment, comment.stars));
    }

    public void goToReservationSummary(View view) {
        startActivity(new Intent(view.getContext(), ReservationSummary.class));
    }
}
