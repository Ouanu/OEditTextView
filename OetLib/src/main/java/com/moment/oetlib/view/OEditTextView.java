package com.moment.oetlib.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ScrollView;


public class OEditTextView extends ScrollView {
    private OEditText editText;

    public OEditTextView(Context context) {
        super(context);
        initView();
    }

    public OEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public OEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        editText = new OEditText(getContext());
        editText.setBackgroundColor(Color.WHITE);
        editText.setMinHeight(600);
        this.addView(editText);
    }

    public OEditText getEditText() {
        return editText;
    }


}
