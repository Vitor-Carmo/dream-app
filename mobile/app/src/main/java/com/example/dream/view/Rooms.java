package com.example.dream.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dream.R;
import com.example.dream.constant.DreamAppConstants;
import com.example.dream.model.Room;
import com.example.dream.model.RoomAdapter;

import java.sql.SQLException;
import java.util.ArrayList;

public class Rooms extends AppCompatActivity {
    ListView listview;
    ArrayList<Room> rooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        try {
            rooms = Room.getRooms();
            setListView();
        } catch (Exception exception) {
            exception.printStackTrace();
            Log.e("Erro", exception.getMessage());
            Toast.makeText(this, "Ocorreu algum Erro! Por favor, volte mais tarde", Toast.LENGTH_LONG).show();
        }
    }

    public void goBack(View view) {
        finish();
    }

    private void setListView() {
        listview = findViewById(R.id.listview);
        listview.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(view.getContext(), RoomDetail.class);

            Bundle extra = new Bundle();
            extra.putSerializable(DreamAppConstants.ROOM_DETAIL,  rooms.get(position));
            intent.putExtra(DreamAppConstants.ROOM_DETAIL, extra);
            startActivity(intent);
        });
        listview.setAdapter(new RoomAdapter(this, rooms));
    }
}