package com.moment.myview.view;

/*
    name: OEditTextView
    author: Ouanu
    date: 2021.11.2
 */

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;

import com.moment.myview.view.tools.FloatViewUtil;


public class OEditTextView extends ScrollView {

    private Context context;



    public OEditTextView(Context context) {
        super(context);
        this.context = context;
    }

    public OEditTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public OEditTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        OEditText editText = new OEditText(context);
        editText.setMinHeight(800);
        OToolBar toolBar = new OToolBar(context);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(editText);
        FloatViewUtil util = new FloatViewUtil((Activity) context);
        util.setFloatView(this, toolBar);
        this.addView(linearLayout);

    }




}
