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

    private final ViewPager2 mViewPager;

    private final BannerAdapter mAdapter;

    private static final int NORMAL_COUNT = 2;
    private int needPage = NORMAL_COUNT;
    private int sidePage = needPage / NORMAL_COUNT;
    private int tempPosition;
    private boolean isAutoPlay = true;
    private boolean isBeginPagerChange = true;

    public Banner(@NonNull Context context) {
        this(context, null);
    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mViewPager = new ViewPager2(context);
        mAdapter = new BannerAdapter();
        mViewPager.setOrientation(ORIENTATION_HORIZONTAL);
        mViewPager.setAdapter(mAdapter);
        addView(mViewPager, layoutParams);

        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                System.out.println(" onPageScrolled  " + position);
            }

            @Override
            public void onPageSelected(int position) {
                if (mAdapter.getRealCount() > 1) {
                    tempPosition = position;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
//                    if (tempPosition == sidePage - 1) {
//                        isBeginPagerChange = false;
//                        mViewPager.setCurrentItem(mAdapter.getRealCount() + tempPosition, false);
//                    } else if (tempPosition == mAdapter.getRealCount() + sidePage) {
//                        isBeginPagerChange = false;
//                        mViewPager.setCurrentItem(sidePage, false);
//                    } else {
//                        isBeginPagerChange = true;
//                    }
//                }
            }
        });
    }

    public void setList(List<String> list) {
        mAdapter.setList(list);
        mViewPager.setCurrentItem(1, false);
    }

}
