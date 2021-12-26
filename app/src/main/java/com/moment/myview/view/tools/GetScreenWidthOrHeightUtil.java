package com.moment.myview.view.tools;



import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * get Screen's width or height
 */
public class GetScreenWidthOrHeightUtil {

    private Context context;
    private Resources resources;
    private DisplayMetrics metrics;

    public GetScreenWidthOrHeightUtil(Context context) {
        this.context = context;
        resources = context.getResources();
        metrics = resources.getDisplayMetrics();
    }

    public int getScreenWidth() {
        return metrics.widthPixels;
    }

    public int getScreenHeight() {
        return metrics.heightPixels;
    }
}
