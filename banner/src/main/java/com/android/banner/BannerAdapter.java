package com.android.banner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.android.banner.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerViewHolder> {

    private static final int NORMAL_COUNT = 2;

    private int needPage = NORMAL_COUNT;
    private int sidePage = needPage / NORMAL_COUNT;

    private List<Object> mDatas = new ArrayList<>();

    private ImageLoader<Object> mImageLoader;

    private OnItemClickListener mOnItemClickListener;

    public void setImageLoader(ImageLoader imageLoader) {
        this.mImageLoader = imageLoader;
    }

    @Override
    public int getItemCount() {
        return getRealCount() > 1 ? getRealCount() + needPage : getRealCount();
    }

    @Override
    public long getItemId(int position) {
        return toRealPosition(position);
    }

    int getRealCount() {
        return mDatas.size();
    }

    private int toRealPosition(int position) {
        int realPosition = 0;
        if (getRealCount() > 1) {
            realPosition = (position - sidePage) % getRealCount();
        }
        if (realPosition < 0) {
            realPosition += getRealCount();
        }
        return realPosition;
    }


    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(mImageLoader.getLayoutRes(), parent, false);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        itemView.setLayoutParams(layoutParams);
        BannerViewHolder baseViewHolder = new BannerViewHolder(itemView);
        if (getOnItemClickListener() != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnItemClickListener().onItemClick(BannerAdapter.this, baseViewHolder, v, toRealPosition(baseViewHolder.getLayoutPosition()));
                }
            });
        }
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        if (mImageLoader != null) {
            int realPosition = toRealPosition(position);
            mImageLoader.displayImage(holder, mDatas.get(realPosition), realPosition);
        }
    }

    public void setList(List dataList) {
        if (dataList == null || dataList.size() == 0) {
            return;
        }
        mDatas.clear();
        mDatas.addAll(dataList);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public final OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

}
