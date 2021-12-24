package com.moment.myview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;


import com.moment.myview.R;

public class OToolBar extends LinearLayout {

    private Context context;

    public OToolBar(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public OToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public OToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    public OToolBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(context).inflate(R.layout.tool_bar, this);
    }

}
