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
 * Created by mdiaz on 27/06/17.
 */

public class OddAdapterDelegate extends AdapterDelegate<List<Word>> {
    private ArrayList<Word> words;
    private int colorResourceId;
    private Context context;

    public OddAdapterDelegate(Context context, ArrayList<Word> words, int colorResourceId) {
        this.context = context;
        this.words = words;
        this.colorResourceId = colorResourceId;
    }

    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public boolean isForViewType(List<Word> items, int position) {
        return position % 2 != 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View wordView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_odd,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(wordView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(List<Word> items, int position, RecyclerView.ViewHolder viewHolder) {
        final Word word = items.get(position);
        ViewHolder vh = (ViewHolder) viewHolder;
        ImageView imageView = vh.mImageViewOdd;

        if (word.hasImage()) {
            imageView.setImageResource(word.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        TextView textViewDefault = vh.mdefaultTextOdd;
        textViewDefault.setText(word.getDefaultTranslation());

        TextView textViewMiwok = vh.mMiwokTextOdd;
        textViewMiwok.setText(word.getMiwokTranslation());

        //applying basic animation
        setAnimation(vh.mWordGroupOdd);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageViewOdd;
        public TextView mdefaultTextOdd;
        public TextView mMiwokTextOdd;
        public View mTextContainerOdd;
        public View mWordGroupOdd;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageViewOdd = (ImageView) itemView.findViewById(R.id.image_odd);
            mdefaultTextOdd = (TextView) itemView.findViewById(R.id.defaultText_odd);
            mMiwokTextOdd = (TextView) itemView.findViewById(R.id.miwokText_odd);
            mTextContainerOdd = itemView.findViewById(R.id.text_container_odd);
            mWordGroupOdd = itemView.findViewById(R.id.word_group_odd);

        }
    }
}
