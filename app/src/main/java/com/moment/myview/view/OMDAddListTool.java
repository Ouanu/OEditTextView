package com.moment.myview.view;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BulletSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OMDAddListTool extends OMDToolItem{

    private static final String REGEX = "^[\\s]*[-\\*\\+] +(.*)";

    public OMDAddListTool(OMDEditText oetText) {

        super(oetText);
    }

    @Override
    public void applyOMDTool() {
        Pattern p = Pattern.compile(REGEX);
        Matcher matcher = p.matcher(getOetText().getText()); // 获取 matcher 对象
        Log.d("MATCHER", "applyOMDTool: " + getOetText().getText());
        while (matcher.find()) {
            getOetText().getText().setSpan(new BulletSpan(30, Color.BLACK), matcher.start(), matcher.start() + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            getOetText().getText().setSpan(new AbsoluteSizeSpan(0), matcher.start(), matcher.start() + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
}
