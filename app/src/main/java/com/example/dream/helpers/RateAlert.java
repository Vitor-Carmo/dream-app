package com.example.dream.helpers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dream.R;

public class RateAlert{
    private final Activity activity;
    private AlertDialog dialog;
    private int rate = 0;

    public RateAlert(Activity myActivity){
        this.activity = myActivity;
    }

    @SuppressLint("InflateParams")
    public void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        LayoutInflater inflater = this.activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.rate, null));
        builder.setCancelable(false);


        this.dialog = builder.create();
        this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.dialog.show();


        setButtonsEventListeners();
        setStarEventListeners();
    }

    private void dismissLoadingDialog(){
        dialog.dismiss();
    }

    private void setButtonsEventListeners(){
        Button cancelButton = this.dialog.findViewById(R.id.cancel);
        Button rateButton = this.dialog.findViewById(R.id.rate);

        rateButton.setOnClickListener(v -> {
            if(rate == 0){
                Toast.makeText(v.getContext(), "Você precisa selecionar uma estrela para avaliar o quarto", Toast.LENGTH_LONG).show();
            }else{
                EditText comment = this.dialog.findViewById(R.id.comment);
                Toast.makeText(v.getContext(), "Avaliação enviada!", Toast.LENGTH_SHORT).show();
                dismissLoadingDialog();
            }
        });

        cancelButton.setOnClickListener(v -> dismissLoadingDialog());
    }

    private void setStarEventListeners(){
        ImageButton firstStar = this.dialog.findViewById(R.id.firstStar);
        ImageButton secondStar = this.dialog.findViewById(R.id.secondStar);
        ImageButton thirdStar = this.dialog.findViewById(R.id.thirdStar);
        ImageButton fourthStar = this.dialog.findViewById(R.id.fourthStar);
        ImageButton fifthStar = this.dialog.findViewById(R.id.fifthStar);

        firstStar.setOnClickListener(v -> {
            firstStar.setImageResource(R.drawable.ic_star);
            secondStar.setImageResource(R.drawable.ic_star_empty);
            thirdStar.setImageResource(R.drawable.ic_star_empty);
            fourthStar.setImageResource(R.drawable.ic_star_empty);
            fifthStar.setImageResource(R.drawable.ic_star_empty);

            rate = 1;
        });

        secondStar.setOnClickListener(v -> {
            firstStar.setImageResource(R.drawable.ic_star);
            secondStar.setImageResource(R.drawable.ic_star);
            thirdStar.setImageResource(R.drawable.ic_star_empty);
            fourthStar.setImageResource(R.drawable.ic_star_empty);
            fifthStar.setImageResource(R.drawable.ic_star_empty);
            rate = 2;
        });

        thirdStar.setOnClickListener(v -> {
            firstStar.setImageResource(R.drawable.ic_star);
            secondStar.setImageResource(R.drawable.ic_star);
            thirdStar.setImageResource(R.drawable.ic_star);
            fourthStar.setImageResource(R.drawable.ic_star_empty);
            fifthStar.setImageResource(R.drawable.ic_star_empty);
            rate = 3;
        });

        fourthStar.setOnClickListener(v -> {
            firstStar.setImageResource(R.drawable.ic_star);
            secondStar.setImageResource(R.drawable.ic_star);
            thirdStar.setImageResource(R.drawable.ic_star);
            fourthStar.setImageResource(R.drawable.ic_star);
            fifthStar.setImageResource(R.drawable.ic_star_empty);
            rate = 4;
        });

        fifthStar.setOnClickListener(v -> {
            firstStar.setImageResource(R.drawable.ic_star);
            secondStar.setImageResource(R.drawable.ic_star);
            thirdStar.setImageResource(R.drawable.ic_star);
            fourthStar.setImageResource(R.drawable.ic_star);
            fifthStar.setImageResource(R.drawable.ic_star);
            rate = 5;
        });
    }

}
