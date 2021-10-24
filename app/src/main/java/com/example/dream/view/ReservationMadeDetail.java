package com.example.dream.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.dream.R;
import com.example.dream.helpers.RateAlert;

public class ReservationMadeDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_made_detail);
    }

    public void goBack(View view) {
        finish();
    }

    public void rate(View view) {
        RateAlert loadingDialog = new RateAlert(this);
        loadingDialog.startLoadingDialog();
    }
}