package com.moment.oetlib.view.tools;


import android.graphics.Typeface;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;


import com.moment.oetlib.view.OEditText;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OBoldTool extends OToolItem {
    private final static String REGEX = "(\\*\\*|__)(.*?)\\1";

    public OBoldTool(OEditText oetText) {
        super(oetText);
    }

    @Override
    public void applyOMDTool() {
        setStyle();

    }

    @Override
    public void setStyle() {
        Pattern p = Pattern.compile(REGEX);
        Matcher matcher = p.matcher(Objects.requireNonNull(getOetText().getText())); // 获取 matcher 对象
        while (matcher.find()) {
            getOetText().getText().setSpan(new StyleSpan(Typeface.BOLD), matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            getOetText().getText().setSpan(new AbsoluteSizeSpan(0), matcher.start(), matcher.start()+2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            getOetText().getText().setSpan(new AbsoluteSizeSpan(0), matcher.end()-2, matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
}
