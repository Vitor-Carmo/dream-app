package com.example.dream.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dream.R;
import com.example.dream.model.Room;
import com.example.dream.model.RoomAdapter;

public class SearchRoom extends AppCompatActivity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_room);
        setListView();
    }

    public void goBack(View view) {
        finish();
    }

    private void setListView(){
        listview = findViewById(R.id.listview);
        listview.setOnItemClickListener((parent, view, position, id) -> startActivity(new Intent(view.getContext(), RoomDetail.class)));
        //listview.setAdapter(new RoomAdapter(this, room.title, room.description, room.prices, room.image, room.stars));
    }
}