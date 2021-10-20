package com.example.dream.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dream.R;


public class CommentAdapter extends ArrayAdapter<String> {
    Context context;
    String[] rComment;
    int[]  rStar;

    public CommentAdapter(Context c, String[] comment, int[] star){
        super(c, R.layout.comment, R.id.commentText, comment);
        this.context = c;

        this.rComment = comment;
        this.rStar = star;
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

        mViewHolder.commentText.setText(rComment[position]);

        return convertView;
    }

}
