package com.example.dream.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dream.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //startActivity(new Intent(Home.this, Login.class));
    }
    public void goToReservations(View view) {
        startActivity(new Intent(view.getContext(), Reservations.class));
    }

    public void goToChangeRegistration(View view) {
        startActivity(new Intent(view.getContext(), ChangeRegistration.class));
    }

    public void goToAboutUs(View view) {
        startActivity(new Intent(view.getContext(), AboutUs.class));
    }

    public void goToReservationsMade(View view) {
        startActivity(new Intent(view.getContext(), ReservationsMade.class));
    }

    public void goToSearchRoom(View view) {
        startActivity(new Intent(view.getContext(), SearchRoom.class));
    }
}