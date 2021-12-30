package com.moment.oetlib.view.tools;

import android.graphics.Color;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BulletSpan;


import com.moment.oetlib.view.OEditText;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OListTool extends OToolItem {

    private static final String REGEX = "(?m)^[\\s]*[-*+] +(.*)";

    public OListTool(OEditText oetText) {

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
            getOetText().getText().setSpan(new BulletSpan(30, Color.BLACK), matcher.start(), matcher.start() + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            getOetText().getText().setSpan(new AbsoluteSizeSpan(0), matcher.start(), matcher.start() + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
}
