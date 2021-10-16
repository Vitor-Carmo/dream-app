package com.example.dream.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dream.R;
import com.example.dream.helpers.ImageLoadTask;

import java.util.zip.Inflater;

public class RoomAdapter extends ArrayAdapter<String> {
    Context context;
    String[] rTitle;
    String[] rDescription;
    double[] rPrice;
    String[] rImage;

    public RoomAdapter(Context c, String[] title, String[] description, double[] price, String[] image){
        super(c, R.layout.room_card, R.id.room_title, title);
        this.context = c;
        this.rTitle = title;
        this.rDescription = description;
        this.rPrice = price;
        this.rImage = image;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View room_list = layoutInflater.inflate(R.layout.room_card, parent, false);

        ImageView image = room_list.findViewById(R.id.room_image);
        TextView title = room_list.findViewById(R.id.room_title);
        TextView description = room_list.findViewById(R.id.room_description);
        TextView price = room_list.findViewById(R.id.room_price);

        new ImageLoadTask(rImage[position], image).execute();
        title.setText(rTitle[position]);
        description.setText(rDescription[position]);
        price.setText(String.valueOf(rPrice[position]));

        return room_list;
    }
}
