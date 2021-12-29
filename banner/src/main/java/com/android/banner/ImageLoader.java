package com.android.banner;

import android.view.View;

public interface ImageLoader<T> {

    void displayImage(BannerViewHolder viewHolder ,T data ,int position );

    int getLayoutRes();

}
