package com.android.banner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.banner.indicator.CircleIndicatorView;
import com.android.banner.indicator.IndicatorView;
import com.android.banner.indicator.RoundRectIndicatorView;
import com.android.banner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<BannerBean> paths = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            BannerBean bannerBean = new BannerBean();
            bannerBean.url = Constants.IMAGE_URL[i];
            bannerBean.text = String.valueOf(i + 1);
            paths.add(bannerBean);
        }
        Banner banner = findViewById(R.id.banner);
        banner.setImageLoader(new ImageLoader<BannerBean>() {
            @Override
            public void displayImage(BannerViewHolder viewHolder, BannerBean data, int position) {
                ImageView imageView = viewHolder.getView(R.id.imageView);
                Glide.with(imageView.getContext()).load(data.url).into(imageView);
                viewHolder.setText(R.id.numTextView, String.format(" index = %s  time = %s ", position, data.text));
            }

            @Override
            public int getLayoutRes() {
                return R.layout.item_banner_image;
            }
        });

        RoundRectIndicatorView roundRectIndicatorView = new RoundRectIndicatorView(this);
        CircleIndicatorView circleIndicatorView = new CircleIndicatorView(this);
        banner.setIndicator(circleIndicatorView)
                .setAutoPlay(true)
                .setStartPosition(3)
                .setData(paths);
        banner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BannerAdapter adapter, RecyclerView.ViewHolder viewHolder, View view, int position) {
                System.out.println(" onItemClick  " + position);

                banner.setStartPosition(position);
                paths.get(position).text = System.currentTimeMillis() + "";
                adapter.notifyDataSetChanged();
            }
        });
    }
}