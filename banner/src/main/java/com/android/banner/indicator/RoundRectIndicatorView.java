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

public class RoundRectIndicatorView extends View implements Indicator {

    private final Paint indicatorPaint;
    private final RectF rectF;
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

    public RoundRectIndicatorView(Context context) {
        this(context, null);
    }

    public RoundRectIndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundRectIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        rectF = new RectF();
        indicatorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


        mIndicatorRadius = mIndicatorSelectedRadius = BannerUtils.dip2px(getContext(), 3f);
        mIndicatorWidth =  BannerUtils.dip2px(getContext(), 14f);
        mIndicatorSelectedWidth = BannerUtils.dip2px(getContext(), 14f);
        mIndicatorSpacing = BannerUtils.dip2px(getContext(), 10f);
        mIndicatorHeight = BannerUtils.dip2px(getContext(), 4f);
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

    public RoundRectIndicatorView setUnSelectedColor(int unSelectedColor) {
        this.unSelectedColor = unSelectedColor;
        return this;
    }

    public RoundRectIndicatorView setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
        return this;
    }

    public RoundRectIndicatorView setIndicatorWidth(int mIndicatorWidth) {
        this.mIndicatorWidth = mIndicatorWidth;
        return this;
    }

    public RoundRectIndicatorView setIndicatorHeight(int mIndicatorHeight) {
        this.mIndicatorHeight = mIndicatorHeight;
        return this;
    }

    public RoundRectIndicatorView setIndicatorSpacing(int mIndicatorSpacing) {
        this.mIndicatorSpacing = mIndicatorSpacing;
        return this;
    }

    public RoundRectIndicatorView setIndicatorSelectedWidth(int mIndicatorSelectedWidth) {
        this.mIndicatorSelectedWidth = mIndicatorSelectedWidth;
        return this;
    }

    public RoundRectIndicatorView setIndicatorRadius(int mIndicatorRadius) {
        this.mIndicatorRadius = mIndicatorRadius;
        return this;
    }

    public RoundRectIndicatorView setIndicatorSelectedRadius(int mIndicatorSelectedRadius) {
        this.mIndicatorSelectedRadius = mIndicatorSelectedRadius;
        return this;
    }

    public RoundRectIndicatorView setParams(FrameLayout.LayoutParams params) {
        this.params = params;
        return this;
    }
}
