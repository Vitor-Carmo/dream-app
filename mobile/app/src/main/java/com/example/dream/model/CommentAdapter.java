package com.example.dream.model;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dream.R;

import java.util.ArrayList;


public class CommentAdapter extends ArrayAdapter<Rate> {
    Context context;
    ArrayList<Rate> comments;

    public CommentAdapter(Context c, ArrayList<Rate> comments){
        super(c, R.layout.comment, R.id.commentText, comments);
        this.context = c;
        this.comments = comments;
    }

    private static class ViewHolder {
        private TextView commentText;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder;
        if (convertView == null) {
            mViewHolder = new ViewHolder();

            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.comment, parent, false);

            mViewHolder.commentText = convertView.findViewById(R.id.commentText);

            convertView.setScrollContainer(false);
            convertView.setTag(mViewHolder);
        }else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        LinearLayout starContainer = convertView.findViewById(R.id.starContainer);
        addStartsToLayout(context, starContainer, Integer.parseInt(comments.get(position).getAcomodacao()));

        Log.e("Estrelas", comments.get(position).getAcomodacao());
        Log.e("Observacao", comments.get(position).getObservacao());

        mViewHolder.commentText.setText(comments.get(position).getObservacao());

        return convertView;
    }

    private void addStartsToLayout(Context c, LinearLayout layout, int numberOfStars) {
        layout.removeAllViews();
        for (int i = 0; i < numberOfStars; i++) {
            ImageButton startButton = new ImageButton(c);
            startButton.setBackgroundColor(Color.TRANSPARENT);
            startButton.setImageResource(R.drawable.ic_star_20);
            startButton.setClickable(false);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMarginEnd(10);
            startButton.setLayoutParams(params);
            layout.addView(startButton);
        }
    }

}
