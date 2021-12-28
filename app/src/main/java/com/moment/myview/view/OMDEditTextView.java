package com.moment.myview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;


public class OMDEditTextView extends ScrollView {
    private OMDEditText editText;

    public OMDEditTextView(Context context) {
        super(context);
        initView();
    }

    public OMDEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public OMDEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        editText = new OMDEditText(getContext());
        editText.setMinHeight(600);
        this.addView(editText);
    }

    public OMDEditText getEditText() {
        return editText;
    }



}
