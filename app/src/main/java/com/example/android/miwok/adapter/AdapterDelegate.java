package com.example.android.miwok.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @param <T> the type of adapters data source i.e. List<Accessory>
 */

public abstract class AdapterDelegate<T> {

    /**
     * Get the view type integer. Must be unique within every Adapter
     *
     * @return the integer representing the view type
     */
    public abstract int getItemViewType();

    /**
     * Called to determine whether this AdapterDelegate is the responsible for the given data
     * element.
     *
     * @param items    The data source of the Adapter
     * @param position The position in the datasource
     * @return true, if this item is responsible,  otherwise false
     */
    public abstract boolean isForViewType(T items, int position);

    /**
     * Creates the  {@link RecyclerView.ViewHolder} for the given data source item
     *
     * @param parent The ViewGroup parent of the given datasource
     * @return The new instantiated {@link RecyclerView.ViewHolder}
     */

    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);

    /**
     * Called to bind the {@link RecyclerView.ViewHolder} to the item of the datas source set
     *
     * @param items      The data source
     * @param position   The position in the datasource
     * @param viewHolder The {@link RecyclerView.ViewHolder} to bind
     */

    public abstract void onBindViewHolder(T items, int position, RecyclerView.ViewHolder viewHolder);


}
