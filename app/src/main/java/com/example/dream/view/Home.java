package com.example.dream.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dream.R;
import com.example.dream.constant.DreamAppConstants;
import com.example.dream.data.SecurityPreferences;

public class Home extends AppCompatActivity {
    SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.mSecurityPreferences = new SecurityPreferences(this);
        if(mSecurityPreferences.getStoredString(DreamAppConstants.LOGIN_ID_KEY) == null){
            startActivity(new Intent(Home.this, Login.class));
            finish();
        }
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

    public void logout(View view) {
        mSecurityPreferences.removeAll();
        startActivity(new Intent(Home.this, Login.class));
        finish();
    }
}