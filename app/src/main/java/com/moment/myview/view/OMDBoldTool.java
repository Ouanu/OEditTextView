package com.moment.myview.view;


import android.graphics.Typeface;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OMDBoldTool extends OMDToolItem {
    private OMDEditText oetText;
    private final static String REGEX = "(\\*\\*|__)(.*?)\\1";

    public OMDBoldTool(OMDEditText oetText) {
        super(oetText);
    }

    @Override
    public void applyOMDTool() {

        Pattern p = Pattern.compile(REGEX);
        Matcher matcher = p.matcher(getOetText().getText()); // 获取 matcher 对象
//        Log.d("MATCHER", "applyOMDTool: " + oetText.getText());
        while (matcher.find()) {
            Log.d("MATCHER", "applyOMDTool: " + matcher.group());
            getOetText().getText().setSpan(new StyleSpan(Typeface.BOLD), matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            getOetText().getText().setSpan(new AbsoluteSizeSpan(0), matcher.start(), matcher.start()+2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            getOetText().getText().setSpan(new AbsoluteSizeSpan(0), matcher.end()-2, matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

    }
}
