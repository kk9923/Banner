package com.android.banner;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

public class BannerViewHolder extends RecyclerView.ViewHolder {

    /**
     * Views indexed with their IDs
     */
    private final SparseArray<View> views;

    public BannerViewHolder(@NonNull View itemView) {
        super(itemView);
        this.views = new SparseArray<>();
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public BannerViewHolder setText(@IdRes int viewId, CharSequence value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }

    public BannerViewHolder setText(@IdRes int viewId, @StringRes int strId) {
        TextView view = getView(viewId);
        view.setText(strId);
        return this;
    }


}
