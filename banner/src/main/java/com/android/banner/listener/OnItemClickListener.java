package com.android.banner.listener;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.android.banner.BannerAdapter;

public interface OnItemClickListener {
    /**
     * Callback method to be invoked when an item in this RecyclerView has
     * been clicked.
     *
     * @param adapter  the adpater
     * @param view     The itemView within the RecyclerView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     */
    void onItemClick(BannerAdapter adapter, RecyclerView.ViewHolder viewHolder, View view, int position);

}
