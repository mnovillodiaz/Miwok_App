package com.example.android.miwok.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.miwok.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdiaz on 01/06/17.
 */

public class RvWordAdapter extends RecyclerView.Adapter {
    public final OnListItemClick listener;
    private ArrayList<Word> mWords;
    private int mColorResourceId;
    private Context mContext;

    private AdapterDelegatesManager<List<Word>> delegatesManager;

    //constructor
    public RvWordAdapter(Context context, ArrayList<Word> words, int ColorResourceId,
                         final OnListItemClick listener) {
        mContext = context;
        mWords = words;
        mColorResourceId = ColorResourceId;
        this.listener = listener;

        // Delegates
        delegatesManager = new AdapterDelegatesManager<>();
        delegatesManager.addDelegate(new OddAdapterDelegate(context, words, ColorResourceId));
        delegatesManager.addDelegate(new EvenAdapterDelegate(context, words, ColorResourceId));

    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegatesManager.onBindViewHolder(mWords, position, holder);

        //TODO Ask Meri: is this the correct place to implement the OnClickListener using delegates?
        final Word word = mWords.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickListener(word);
                }
            });
    }


    @Override
    public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(mWords, position);
    }


    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mWords.size();
    }


    public interface OnListItemClick {
        void onClickListener(Word item);
    }

}

