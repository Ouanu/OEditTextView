package com.moment.myview.view.tools;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class KeyboardChangeListener implements ViewTreeObserver.OnGlobalLayoutListener {

    private GetScreenWidthOrHeight screenWidthOrHeight;
    private Context context;
    private Activity activity;
    private View rootView;
    private View floatView;

//    public KeyboardChangeListener(Context context, Activity activity) {
//        this.context = context;
//        this.activity = activity;
//        screenWidthOrHeight = new GetScreenWidthOrHeight(context);
//    }

    public KeyboardChangeListener(View rootView, View floatView) {
        this.rootView = rootView;
        this.floatView = floatView;
        screenWidthOrHeight = new GetScreenWidthOrHeight(rootView.getContext());
    }

    @Override
    public void onGlobalLayout() {
        Rect rect = new Rect();
        rootView.getWindowVisibleDisplayFrame(rect);
//        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int height = screenWidthOrHeight.getScreenHeight();
        int keyboardHeight = height - rect.bottom;
        if (Math.abs(keyboardHeight) > height / 5) {
            rootView.setLayoutParams(new LinearLayout.LayoutParams(screenWidthOrHeight.getScreenWidth() / 2, height));
            Log.d("+++++++++", "onGlobalLayout: ");
        } else {
            rootView.requestLayout();
        }
    }
}
