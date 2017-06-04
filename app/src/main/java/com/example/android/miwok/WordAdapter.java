package com.example.android.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mdiaz on 18/05/17.
 */


public class WordAdapter extends ArrayAdapter<Word> {

    /**
     * @param context   The current context. Used to inflate the layout file.
     * @param words     A List of Word objects to display in a list
     * @param ColorResourceId The color for the background
     */

    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words, int ColorResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        mColorResourceId = ColorResourceId;
    }

    //Get the view for the list
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.custom_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the ImageView in the custom_item.xml layout with the ID
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        if(currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        }
        else {
            imageView.setVisibility(View.GONE);
        }

        // Find the TextView in the custom_item.xml layout with the ID defaultText
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.defaultText);
        nameTextView.setText(currentWord.getDefaultTranslation());

        // Find the TextView in the list_item.xml layout with the ID miwokText
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.miwokText);
        numberTextView.setText(currentWord.getMiwokTranslation());

        // Find the textContainer to be able to set the color
        View TextContainer = listItemView.findViewById(R.id.text_container);
        TextContainer.setBackgroundResource(mColorResourceId);

        // Return the whole list item layout (containing 2 TextViews)
        return listItemView;
    }

}

