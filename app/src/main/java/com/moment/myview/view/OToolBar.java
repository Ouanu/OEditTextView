package com.moment.myview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moment.myview.R;

public class OToolBar extends LinearLayout {

    private Context context;
    private TextView bold;
    private TextView size;
    private TextView italic;
    private TextView addTitle;
    private ImageView addList;
    private ImageView btnGetUri;

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
