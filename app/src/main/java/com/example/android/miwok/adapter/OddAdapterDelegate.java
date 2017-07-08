package com.example.android.miwok.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.miwok.R;
import com.example.android.miwok.Word;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.miwok.AnimationUtils.setAnimation;

/**
 * Created by mdiaz on 27/06/17.
 */

public class OddAdapterDelegate extends AdapterDelegate<List<Word>> {
    private ArrayList<Word> words;
    private int colorResourceId;
    private Context context;
    public final OnListItemClick listener;

    public OddAdapterDelegate(Context context, ArrayList<Word> words, int colorResourceId,
                              final OnListItemClick listener) {
        this.context = context;
        this.words = words;
        this.colorResourceId = colorResourceId;
        this.listener = listener;
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

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickListener(word);
            }
        });

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_odd) ImageView mImageViewOdd;
        @BindView(R.id.defaultText_odd) TextView mdefaultTextOdd;
        @BindView(R.id.miwokText_odd) TextView mMiwokTextOdd;
        @BindView(R.id.word_group_odd) View mWordGroupOdd;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
