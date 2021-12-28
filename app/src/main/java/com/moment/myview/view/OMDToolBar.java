package com.moment.myview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.moment.myview.R;

public class OMDToolBar extends LinearLayout {
    private TextView bold;
    private TextView size;
    private TextView italic;
    private TextView addTitle;
    private ImageView addList;
    private ImageView btnGetUri;

    public OMDToolBar(Context context) {
        super(context);
    }

    public OMDToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public OMDToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.tool_bar, this);
        bold = (TextView) findViewById(R.id.bold);
        size = (TextView) findViewById(R.id.size);
        italic = (TextView) findViewById(R.id.italic);
        addTitle = (TextView) findViewById(R.id.add_title);
        addList = (ImageView) findViewById(R.id.add_list);
        btnGetUri = (ImageView) findViewById(R.id.btn_get_uri);
    }


}
