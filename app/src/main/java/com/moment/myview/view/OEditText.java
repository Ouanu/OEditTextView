package com.moment.myview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.moment.myview.view.tools.OToolItem;
import com.moment.myview.view.tools.OTools;

import org.jetbrains.annotations.NotNull;

public class OEditText extends androidx.appcompat.widget.AppCompatEditText {

    private float startY = 0.0f;
    private InputMethodManager im;
    private OTools oTools;
    private StringBuilder builder = new StringBuilder();

    public OEditText(@NonNull @NotNull Context context) {
        super(context);
        initView();
    }

    public OEditText(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public OEditText(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        this.setGravity(Gravity.START);
        this.setLineSpacing(10, 1.2f);
        this.setTextSize(20);
        im = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        oTools = new OTools(this);
        oTools.autoTool();

        this.addTextChangedListener(new TextWatcher() {
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
        });
    }


    /**
     * Only when you click the view, the soft keyboard will show.
     *
     * @param event Touch event
     * @return result
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        long preTime = 0;
        long nowTime = 0;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startY = event.getY(); // Get the position y when you click it at the first time
            preTime = System.currentTimeMillis();

        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            float endY = event.getY();    // get the end position y when you hand up your finger
            nowTime = System.currentTimeMillis();
            if ((endY - startY > 50.0f || startY - endY > 50.0f)) {
                this.setFocusable(false);   // lose the focus
                im.hideSoftInputFromWindow(this.getApplicationWindowToken(), 0);
                for (OToolItem oToolItem : oTools.getToolList()) {
                    oToolItem.applyOMDTool();
                }

            } else {
                this.setFocusable(true);    // focus back
                this.setFocusableInTouchMode(true);
                im.showSoftInput(this, 0);
                this.setText(builder);
            }
        }
        return super.onTouchEvent(event);
    }
}
