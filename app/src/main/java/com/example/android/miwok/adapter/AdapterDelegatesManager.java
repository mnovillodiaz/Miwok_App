package com.example.android.miwok.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdiaz on 26/06/17.
 */

public class AdapterDelegatesManager<T> {

    private static final int NO_DELEGATE_FOUND = -1;

    private final List<AdapterDelegate<T>> delegateList = new ArrayList<>();

    public AdapterDelegatesManager<T> addDelegate(final AdapterDelegate<T> delegate) {
        delegateList.add(delegate);
        return this;
    }

    public int getItemViewType(final T item, final int position) {
        for (AdapterDelegate<T> adapterDelegate : delegateList) {
            if (adapterDelegate.isForViewType(item, position)) {
                return adapterDelegate.getItemViewType();
            }
        }

        return NO_DELEGATE_FOUND;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int viewType) {
        for (AdapterDelegate<T> adapterDelegate : delegateList) {
            if (adapterDelegate.getItemViewType() == viewType) {
                return adapterDelegate.onCreateViewHolder(viewGroup);
            }
        }
        // if no delegate matches the viewType, use the EmptyItemViewHolder
        return EmptyItemViewHolder.create(viewGroup);
    }

    public void onBindViewHolder(T items, int position, RecyclerView.ViewHolder viewHolder) {
        int viewType = viewHolder.getItemViewType();

        for (AdapterDelegate<T> adapterDelegate : delegateList) {
            if (adapterDelegate.getItemViewType() == viewType) {
                adapterDelegate.onBindViewHolder(items, position, viewHolder);
                break;
            }
        }
    }

    /**
     * Unknown items in RecyclerView.Adapter. An item connected to this ViewHolder does not get drawn on the
     * RecyclerView.
     */
    private static class EmptyItemViewHolder extends RecyclerView.ViewHolder {

        private EmptyItemViewHolder(final View itemView) {
            super(itemView);
        }

        public static EmptyItemViewHolder create(final ViewGroup viewGroup) {
            View emptyView = new View(viewGroup.getContext());
            emptyView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT));
            emptyView.setPadding(0, 0, 0, 0);
            return new EmptyItemViewHolder(emptyView);
        }
    }


}
