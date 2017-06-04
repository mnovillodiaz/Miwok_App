package com.example.android.miwok;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mdiaz on 01/06/17.
 */

public class RvWordAdapter extends RecyclerView.Adapter<RvWordAdapter.ViewHolder> {
    private ArrayList<Word> mWords;
    private int mColorResourceId;
    private Context mContext;
    private int lastPosition = -1;

    //constructor
    public RvWordAdapter(Context context, ArrayList<Word> words, int ColorReourceId) {
        mContext = context;
        mWords = words;
        mColorResourceId = ColorReourceId;

    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public RvWordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the custom layout
        View wordView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(wordView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(RvWordAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Word word = mWords.get(position);

        // Set item views based on your views and data model
        ImageView imageView = viewHolder.mImageView;

        if(word.hasImage()) {
            imageView.setImageResource(word.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        }
        else {
            imageView.setVisibility(View.GONE);
        }

        TextView textViewDefault = viewHolder.mdefaultText;
        textViewDefault.setText(word.getDefaultTranslation());

        TextView textViewMiwok = viewHolder.mMiwokText;
        textViewMiwok.setText(word.getMiwokTranslation());

        // Find the textContainer to be able to set the color depending in position
        View TextContainer = viewHolder.mTextContainer;
        if(position % 2 == 0){
            TextContainer.setBackgroundResource(R.color.tan_background);
            textViewDefault.setTextColor(Color.GRAY);
            textViewMiwok.setTextColor(Color.GRAY);
        }
        else {
            TextContainer.setBackgroundResource(mColorResourceId);
        }

        //Applying a basic animation to the word group
        setAnimation(viewHolder.mWordGroup);

    }

    // Basic Fade in Animation
    private void setAnimation(View viewToAnimate) {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.fade_in);
            viewToAnimate.startAnimation(animation);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mdefaultText;
        public TextView mMiwokText;
        public View mTextContainer;
        public View mWordGroup;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image);
            mdefaultText = (TextView) itemView.findViewById(R.id.defaultText);
            mMiwokText = (TextView) itemView.findViewById(R.id.miwokText);
            mTextContainer = itemView.findViewById(R.id.text_container);
            mWordGroup = itemView.findViewById(R.id.word_group);
        }
    }
}

