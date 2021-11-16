package com.android.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

import static androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL;

public class Banner extends FrameLayout {

    private final ViewPager2 mViewPager ;

    private final BannerAdapter mAdapter;

    public Banner(@NonNull Context context) {
        this(context,null);
    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mViewPager = new ViewPager2(context);
        mAdapter = new BannerAdapter();
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setOrientation(ORIENTATION_HORIZONTAL);
        mViewPager.setAdapter(mAdapter);
        addView(mViewPager,layoutParams);
    }

    public void setList(List<String> list ){
        mAdapter.setList(list);
    }

}
