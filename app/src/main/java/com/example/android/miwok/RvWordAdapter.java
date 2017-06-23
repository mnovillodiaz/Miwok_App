package com.example.android.miwok;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mdiaz on 01/06/17.
 */

public class RvWordAdapter extends RecyclerView.Adapter<RvWordAdapter.ViewHolder> {
    private final OnListItemClick listener;
    private ArrayList<Word> mWords;
    private int mColorResourceId;
    private Context mContext;
    //constructor
    public RvWordAdapter(Context context, ArrayList<Word> words, int ColorReourceId, final OnListItemClick listener) {
        mContext = context;
        mWords = words;
        mColorResourceId = ColorReourceId;
        this.listener = listener;

    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public RvWordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ViewType.EVEN.ordinal()) {
            View wordView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(wordView);
            return viewHolder;
        } else if (viewType == ViewType.ODD.ordinal()) {
            View wordView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_odd, parent, false);
            ViewHolder viewHolder = new ViewHolder(wordView);
            return viewHolder;
        }
        return null;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(RvWordAdapter.ViewHolder viewHolder, final int position) {

        final Word word = mWords.get(position);
        if (getItemViewType(position) == ViewType.EVEN.ordinal()) {
            ImageView imageView = viewHolder.mImageView;

            if (word.hasImage()) {
                imageView.setImageResource(word.getImageResourceId());
                imageView.setVisibility(View.VISIBLE);
            } else {
                imageView.setVisibility(View.GONE);
            }

            TextView textViewDefault = viewHolder.mdefaultText;
            textViewDefault.setText(word.getDefaultTranslation());

            TextView textViewMiwok = viewHolder.mMiwokText;
            textViewMiwok.setText(word.getMiwokTranslation());

            // setting the background color of TextContainer and play to the category one
            viewHolder.mTextContainer.setBackgroundResource(mColorResourceId);
            viewHolder.mPlayIcon.setBackgroundResource(mColorResourceId);

            //Applying a basic animation to the word group
            setAnimation(viewHolder.mWordGroup);

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    listener.onClickListener(word);
                }
            });
        } else if (getItemViewType(position) == ViewType.ODD.ordinal()) {
            ImageView imageView = viewHolder.mImageViewOdd;

            if (word.hasImage()) {
                imageView.setImageResource(word.getImageResourceId());
                imageView.setVisibility(View.VISIBLE);
            } else {
                imageView.setVisibility(View.GONE);
            }

            TextView textViewDefault = viewHolder.mdefaultTextOdd;
            textViewDefault.setText(word.getDefaultTranslation());

            TextView textViewMiwok = viewHolder.mMiwokTextOdd;
            textViewMiwok.setText(word.getMiwokTranslation());

            //Applying a basic animation to the word group
            setAnimation(viewHolder.mWordGroupOdd);

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    listener.onClickListener(word);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    // Basic Fade in Animation
    private void setAnimation(final View viewToAnimate) {

        ValueAnimator va = ValueAnimator.ofFloat(0, 1);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                viewToAnimate.setAlpha(value);
            }
        });
        va.setDuration(500);
        va.start();
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mWords.size();
    }

    private enum ViewType {
        EVEN,
        ODD,
    }

    public interface OnListItemClick {
        void onClickListener(Word item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mdefaultText;
        public TextView mMiwokText;
        public View mTextContainer;
        public View mWordGroup;
        public ImageView mPlayIcon;

        public ImageView mImageViewOdd;
        public TextView mdefaultTextOdd;
        public TextView mMiwokTextOdd;
        public View mTextContainerOdd;
        public View mWordGroupOdd;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image);
            mdefaultText = (TextView) itemView.findViewById(R.id.defaultText);
            mMiwokText = (TextView) itemView.findViewById(R.id.miwokText);
            mTextContainer = itemView.findViewById(R.id.text_container);
            mWordGroup = itemView.findViewById(R.id.word_group);
            mPlayIcon = (ImageView) itemView.findViewById(R.id.playIcon);
            mImageViewOdd = (ImageView) itemView.findViewById(R.id.image_odd);
            mdefaultTextOdd = (TextView) itemView.findViewById(R.id.defaultText_odd);
            mMiwokTextOdd = (TextView) itemView.findViewById(R.id.miwokText_odd);
            mTextContainerOdd = itemView.findViewById(R.id.text_container_odd);
            mWordGroupOdd = itemView.findViewById(R.id.word_group_odd);

        }
    }


}

