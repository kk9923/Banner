package com.android.banner.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.android.banner.BannerUtils;

public class CircleIndicatorView extends View implements Indicator {

    private final Paint indicatorPaint;
    private final RectF rectF;
    private float offset;
    private int selectedPage;
    private int pagerCount;
    private int unSelectedColor = Color.GRAY;
    private int selectedColor = Color.RED;

    /*--------------- 核心控制点大小距离参数 ---------------*/
    private int mIndicatorRadius;          //  未选中 Radius
    private int mIndicatorWidth;           //  未选中 Width
    private int mIndicatorHeight;          //  Indicator Height
    private int mIndicatorSpacing;         //  Indicator 间距
    private int mIndicatorSelectedRadius;  //  选中 Radius
    private int mIndicatorSelectedWidth;   //  选中 Width

    public CircleIndicatorView(Context context) {
        this(context, null);
    }

    public CircleIndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        rectF = new RectF();
        indicatorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mIndicatorWidth =  BannerUtils.dip2px(getContext(), 10f);
        mIndicatorSelectedWidth = BannerUtils.dip2px(getContext(), 10f);
        mIndicatorSpacing = BannerUtils.dip2px(getContext(), 10f);
        mIndicatorHeight = BannerUtils.dip2px(getContext(), 10f);

        mIndicatorRadius = mIndicatorWidth / 2 ;
        mIndicatorSelectedRadius = mIndicatorSelectedWidth / 2 ;
    }

    @Override
    public void initIndicatorCount(int pagerCount, int currentPage) {
        this.pagerCount = pagerCount;
        this.selectedPage = currentPage;
        setVisibility(pagerCount > 1 ? VISIBLE : GONE);
        requestLayout();
    }

    @Override
    public View getView() {
        return this;
    }

    /**
     * 控制在banner中的位置
     */
    private FrameLayout.LayoutParams params;

    @Override
    public FrameLayout.LayoutParams getParams() {
        if (params == null) {
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
            params.bottomMargin = BannerUtils.dip2px(getContext(), 10);
        }
        return params;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        selectedPage = position;
        invalidate();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int spacingDistance = (pagerCount - 1) * mIndicatorSpacing;
        int indicatorDistance = (pagerCount - 1) * mIndicatorWidth + mIndicatorSelectedWidth;
        int width = spacingDistance + indicatorDistance;
        setMeasuredDimension(width, mIndicatorHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (pagerCount == 0) {
            return;
        }
        float left;
        float top = 0;
        float right;
        float bottom = mIndicatorHeight;
        float indicatorRadius = 0;
        for (int i = 0; i < pagerCount; i++) {
            if (selectedPage == i) {
                indicatorRadius = mIndicatorSelectedRadius;
                left = i * (mIndicatorSpacing + mIndicatorWidth);
                right = left + mIndicatorSelectedWidth;
            } else {
                indicatorRadius = mIndicatorRadius;
                if (i < selectedPage) {
                    left = i * (mIndicatorSpacing + mIndicatorWidth);
                    right = left + mIndicatorWidth;
                } else {
                    left = i * mIndicatorSpacing + mIndicatorWidth * (i - 1) + mIndicatorSelectedWidth;
                    right = left + mIndicatorWidth;
                }
            }
            rectF.set(left, top, right, bottom);
            indicatorPaint.setColor(selectedPage == i ? selectedColor : unSelectedColor);
            canvas.drawRoundRect(rectF, indicatorRadius, indicatorRadius, indicatorPaint);
        }
    }

    /*--------------- 下面是暴露的方法 ---------------*/

    public CircleIndicatorView setUnSelectedColor(int unSelectedColor) {
        this.unSelectedColor = unSelectedColor;
        return this;
    }

    public CircleIndicatorView setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
        return this;
    }

    public CircleIndicatorView setIndicatorWidth(int mIndicatorWidth) {
        this.mIndicatorWidth = mIndicatorWidth;
        mIndicatorRadius = mIndicatorWidth / 2 ;
        return this;
    }

    public CircleIndicatorView setIndicatorHeight(int mIndicatorHeight) {
        this.mIndicatorHeight = mIndicatorHeight;
        return this;
    }

    public CircleIndicatorView setIndicatorSpacing(int mIndicatorSpacing) {
        this.mIndicatorSpacing = mIndicatorSpacing;
        return this;
    }


    public CircleIndicatorView setIndicatorSelectedWidth(int mIndicatorSelectedWidth) {
        this.mIndicatorSelectedWidth = mIndicatorSelectedWidth;
        mIndicatorSelectedRadius = mIndicatorSelectedWidth / 2 ;
        return this;
    }

    public CircleIndicatorView setParams(FrameLayout.LayoutParams params) {
        this.params = params;
        return this;
    }
}
