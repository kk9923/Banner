package com.android.banner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

class BannerAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    private List<String> paths = new ArrayList<>();

    private int needPage = 2;

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner_image, parent, false);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        itemView.setLayoutParams(layoutParams);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        System.out.println("  onBindViewHolder  " + position + "  RealPosition  " + getRealPosition(position) );
        Glide.with(holder.mImageView.getContext()).load(paths.get(getRealPosition(position))).into(holder.mImageView);
        holder.num.setText("position :  " + getRealPosition(position));
    }

    @Override
    public int getItemCount() {
        return paths.size() + needPage;
    }

    int getRealCount() {
        return paths.size();
    }

    private int getRealPosition(int position) {
        int realPosition = 0;
        if (getRealCount() > 1) {
            realPosition = (position - 1) % getRealCount();
        }
        if (realPosition < 0) {
            realPosition += getRealCount();
        }
        return realPosition;
    }

    public void setList(List<String> imageList) {
        if (imageList == null || imageList.size() == 0) {
            return;
        }
        paths.clear();
        paths.addAll(imageList);
        notifyDataSetChanged();
    }
}
