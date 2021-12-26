package com.moment.myview.view.tools;

import android.app.Activity;

import android.graphics.Rect;

import android.view.View;
import android.view.ViewTreeObserver;


public class FloatViewUtil {

    private Activity activity;
    private GetScreenWidthOrHeightUtil util;

    public FloatViewUtil(Activity activity) {
        this.activity = activity;
        util = new GetScreenWidthOrHeightUtil(activity);
    }



    public void setFloatView(View rootView, View targetView) {
        ViewTreeObserver.OnGlobalLayoutListener layoutListener = () -> {
            Rect rect = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            int height = util.getScreenHeight() - (rect.top - rect.bottom);
            boolean isShowing = height > util.getScreenHeight() / 3;
            if (isShowing) {
                targetView.animate().translationY(-height).setDuration(0).start();
            } else {
                targetView.animate().translationY(0).start();
            }
        };
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
    }
}
