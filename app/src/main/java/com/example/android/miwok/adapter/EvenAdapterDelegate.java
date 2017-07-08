package com.example.android.miwok.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.miwok.AnimationUtils;
import com.example.android.miwok.R;
import com.example.android.miwok.Word;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mdiaz on 29/06/17.
 */

public class EvenAdapterDelegate extends AdapterDelegate<List<Word>> {
    private ArrayList<Word> words;
    private int colorResourceId;
    private Context context;
    public final OnListItemClick listener;


    protected EvenAdapterDelegate(Context context, ArrayList<Word> words, int colorResourceId,
                                  final OnListItemClick listener) {
        this.context = context;
        this.words = words;
        this.colorResourceId = colorResourceId;
        this.listener = listener;
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
        AnimationUtils.setAnimation(vh.mWordGroup);

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickListener(word);
            }
        });

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image) ImageView mImageView;
        @BindView(R.id.defaultText) TextView mdefaultText;
        @BindView(R.id.miwokText) TextView mMiwokText;
        @BindView(R.id.text_container) View mTextContainer;
        @BindView(R.id.word_group) View mWordGroup;
        @BindView(R.id.playIcon) ImageView mPlayIcon;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
