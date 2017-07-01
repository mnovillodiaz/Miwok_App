package com.example.android.miwok;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdiaz on 29/06/17.
 */

public class EvenAdapterDelegate extends AdapterDelegate<List<Word>> {
    private ArrayList<Word> words;
    private int colorResourceId;
    private Context context;


    protected EvenAdapterDelegate(Context context, ArrayList<Word> words, int colorResourceId) {
        this.context = context;
        this.words = words;
        this.colorResourceId = colorResourceId;
    }


    @Override
    public int getItemViewType() {
        return 0;
    }

    @Override
    public boolean isForViewType(List<Word> items, int position) {
        return position % 2 == 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View wordView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(wordView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(List<Word> items, int position, RecyclerView.ViewHolder viewHolder) {
        final Word word = items.get(position);
        ViewHolder vh = (ViewHolder) viewHolder;
        ImageView imageView = vh.mImageView;

        if (word.hasImage()) {
            imageView.setImageResource(word.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        TextView textViewDefault = vh.mdefaultText;
        textViewDefault.setText(word.getDefaultTranslation());

        TextView textViewMiwok = vh.mMiwokText;
        textViewMiwok.setText(word.getMiwokTranslation());

       // setting the background color of TextContainer and play to the category one
        vh.mTextContainer.setBackgroundResource(colorResourceId);
        vh.mPlayIcon.setBackgroundResource(colorResourceId);

        //applying basic animation
        setAnimation(vh.mWordGroup);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mdefaultText;
        public TextView mMiwokText;
        public View mTextContainer;
        public View mWordGroup;
        public ImageView mPlayIcon;


        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image);
            mdefaultText = (TextView) itemView.findViewById(R.id.defaultText);
            mMiwokText = (TextView) itemView.findViewById(R.id.miwokText);
            mTextContainer = itemView.findViewById(R.id.text_container);
            mWordGroup = itemView.findViewById(R.id.word_group);
            mPlayIcon = (ImageView) itemView.findViewById(R.id.playIcon);
        }
    }

}
