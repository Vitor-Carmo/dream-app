package com.example.dream.model;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dream.R;
import com.example.dream.helpers.ImageLoadTask;
import com.example.dream.helpers.MoneyFormat;


public class RoomAdapter extends ArrayAdapter<String> {
    Context context;
    String[] rTitle;
    String[] rDescription;
    double[] rPrice;
    String[] rImage;
    int[]  rStar;

    public RoomAdapter(Context c, String[] title, String[] description, double[] price, String[] image, int[] star){
        super(c, R.layout.room_card, R.id.room_title, title);
        this.context = c;
        this.rTitle = title;
        this.rDescription = description;
        this.rPrice = price;
        this.rImage = image;
        this.rStar = star;
    }

    private static class ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView description;
        private TextView price;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder;
        if (convertView == null) {
            mViewHolder = new ViewHolder();

            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.room_card, parent, false);


            mViewHolder.image = convertView.findViewById(R.id.room_image);
            mViewHolder.title = convertView.findViewById(R.id.room_title);
            mViewHolder.description = convertView.findViewById(R.id.room_description);
            mViewHolder.price = convertView.findViewById(R.id.room_price);
            convertView.setTag(mViewHolder);
            convertView.setClickable(false);

        }else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        LinearLayout starContainer = convertView.findViewById(R.id.stars);

        addStartsToLayout(context, starContainer, rStar[position]);

        new ImageLoadTask(rImage[position], mViewHolder.image).execute();
        mViewHolder.title.setText(rTitle[position]);
        mViewHolder.description.setText(rDescription[position]);
        mViewHolder.price.setText(MoneyFormat.format(rPrice[position]));

        return convertView;
    }


    private void addStartsToLayout(Context c, LinearLayout layout, int numberOfStars) {
        for (int i = 0; i < numberOfStars; i++) {
            ImageButton startButton = new ImageButton(c);
            startButton.setBackgroundColor(Color.TRANSPARENT);
            startButton.setImageResource(R.drawable.ic_star_20);
            startButton.setClickable(false);
            LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.setMarginEnd(10);
            startButton.setLayoutParams(params);
            layout.addView(startButton);
        }
    }
}
