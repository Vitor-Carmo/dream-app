package com.example.dream.model;

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
import com.example.dream.helpers.MoneyFormat;


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
        }else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        new ImageLoadTask(rImage[position], mViewHolder.image).execute();
        mViewHolder.title.setText(rTitle[position]);
        mViewHolder.description.setText(rDescription[position]);
        mViewHolder.price.setText(MoneyFormat.format(rPrice[position]));

        // [...] some changes that must be done at refresh
        return convertView;
    }
}
