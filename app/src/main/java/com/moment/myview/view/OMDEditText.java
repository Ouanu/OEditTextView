package com.moment.myview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

public class OMDEditText extends androidx.appcompat.widget.AppCompatEditText {

    private float startY = 0.0f;
    private InputMethodManager im;

    public OMDEditText(@NonNull @NotNull Context context) {
        super(context);
        initView();
    }

    public OMDEditText(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public OMDEditText(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        this.setGravity(Gravity.START);
        im = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

    }


    /**
     * Only when you click the view, the soft keyboard will show.
     *
     * @param event Touch event
     * @return result
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startY = event.getY(); // Get the position y when you click it at the first time
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            float endY = event.getY();    // get the end position y when you hand up your finger
            if (endY - startY > 50.0f || startY - endY > 50.0f) {
                this.setFocusable(false);   // lose the focus
                im.hideSoftInputFromWindow(this.getApplicationWindowToken(), 0);
            } else {
                this.setFocusable(true);    // focus back
                this.setFocusableInTouchMode(true);
                im.showSoftInput(this, 0);
            }
        }
        return super.onTouchEvent(event);
    }
}
