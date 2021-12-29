package com.android.banner;

import android.content.Context;

public class BannerUtils {

    public static int dip2px(Context context,float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}
