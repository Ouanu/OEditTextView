package com.moment.oetlib.view;

import android.annotation.SuppressLint;
import android.content.Context;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.moment.oetlib.view.tools.OToolItem;
import com.moment.oetlib.view.tools.OTools;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class OEditText extends androidx.appcompat.widget.AppCompatEditText {

    private float startY = 0.0f;
    private InputMethodManager im;
    private OTools oTools;
    private final StringBuilder builder = new StringBuilder();
    private long startTime = 0;
    private Context context;

    public OEditText(@NonNull @NotNull Context context) {
        super(context);
        initView();
        this.context = context;
    }

    public OEditText(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public OEditText(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        this.setGravity(Gravity.START);
        this.setLineSpacing(10, 1.2f);
        this.setTextSize(20);

        im = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        oTools = new OTools(this, getContext().getContentResolver());

        /*
        if you wanna use the default, you can uncomment these two lines
         */
        oTools.autoTool();
        this.addTextChangedListener(textWatcher);
        this.setOnTouchListener(setTouchEvent);
    }


    /**
     * Not suggest you use it
     * Only when you click the view, the soft keyboard will show.
     * it got some bugs, and i have no time to fix it (maybe i will fix in the future if i remember)
     * when you touch and move the screen it may be not apply the tools' styles
     * i suggest you can set a button and control it when you wanna edit
     */

    OnTouchListener setTouchEvent = new OnTouchListener() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                startY = event.getY(); // Get the position y when you click it at the first time
                startTime = System.currentTimeMillis();
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                long time = System.currentTimeMillis() - startTime;
                float endY = event.getY();    // get the end position y when you hand up your finger
                if ((endY - startY > 50.0f || startY - endY > 50.0f)) {
                    v.setFocusable(false);   // lose the focus
                    v.setFocusableInTouchMode(false);
                    im.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    for (OToolItem oToolItem : oTools.getToolList()) {
                        oToolItem.applyOMDTool();
                    }

                } else if ((endY - startY < 50.0f || startY - endY < 50.0f) && time < 500){
                    v.setFocusable(true);    // focus back
                    v.setFocusableInTouchMode(true);
                    im.showSoftInput(v, 0);
                    setOetText();
                }
            }
            return false;
        }
    };

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            builder.replace(0, builder.length(), s.toString());
        }
    };

    private void setOetText() {
        this.setText(builder);
    }

    /*
    Get keyboard method
     */
    public InputMethodManager getIm() {
        return im;
    }

    /*
    Get tools method
     */
    public OTools getOTools() {
        return oTools;
    }

    /*
    Get text of editText
     */
    public StringBuilder getBuilder() {
        return builder;
    }
}
