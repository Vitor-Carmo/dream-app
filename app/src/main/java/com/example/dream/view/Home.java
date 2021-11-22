package com.example.dream.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.dream.R;
import com.example.dream.constant.DreamAppConstants;
import com.example.dream.data.SecurityPreferences;

public class Home extends AppCompatActivity {
    SecurityPreferences mSecurityPreferences;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.mSecurityPreferences = new SecurityPreferences(this);
        if(mSecurityPreferences.getStoredString(DreamAppConstants.LOGIN_ID_KEY) == null){
            startActivity(new Intent(Home.this, Login.class));
            finish();
        }
        search = findViewById(R.id.search);
    }

    public void goToReservations(View view) {
        startActivity(new Intent(view.getContext(), Rooms.class));
    }

    public void goToChangeRegistration(View view) {
        startActivity(new Intent(view.getContext(), ChangeRegistration.class));
    }

    public void goToAboutUs(View view) {
        startActivity(new Intent(view.getContext(), AboutUs.class));
    }

    public void goToReservationsMade(View view) {
        startActivity(new Intent(view.getContext(), Reservations.class));
    }

    public void goToSearchRoom(View view) {
        Intent intent = new Intent(view.getContext(), SearchRoom.class);
        Bundle extra = new Bundle();
        extra.putSerializable(DreamAppConstants.SEARCH_KEY,  search.getText().toString());
        intent.putExtra(DreamAppConstants.SEARCH_KEY, extra);
        startActivity(intent);
    }

    public void logout(View view) {
        mSecurityPreferences.removeAll();
        startActivity(new Intent(Home.this, Login.class));
        finish();
    }
}