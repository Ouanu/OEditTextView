package com.moment.myview.view;
/*
    name: OEditView
    author: Ouanu
    date: 2021.11.2
 */

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;

import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A View which can insert images
 */
public class OEditText extends androidx.appcompat.widget.AppCompatEditText {
//    private static final String TAG = "OEditText";
    private float startY = 0.0f;
    private InputMethodManager im;
    private final StringBuilder desc = new StringBuilder();
    private static int selectStart = 0;


    public OEditText(@NonNull @NotNull Context context) {
        super(context);
        initView();
    }

    public OEditText(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public OEditText(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }



    private void initView() {
        this.setGravity(Gravity.START);
        im = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        desc.append(this.getText());
        loadText(desc);

    }

    private void loadText(StringBuilder desc) {
        SpannableStringBuilder builder = new SpannableStringBuilder(desc);
        String rex = "<([^>]*)>";
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(desc);
        ContentResolver contentResolver = getContext().getContentResolver();
        String uri;
        while (matcher.find()) {
            uri = matcher.group().replaceAll("[<>]", "");
            try {
                Bitmap bitmap = scaleImage(MediaStore.Images.Media.getBitmap(contentResolver, Uri.parse(uri)));
                builder.setSpan(
                        new ImageSpan(getContext(), bitmap),
                        matcher.start(),
                        matcher.end(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.setText(builder);
            this.setSelection(desc.length());
        }
    }

    private Bitmap scaleImage(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 计算缩放比例.
        float k = ((float) 640) / width;
        // 取得想要缩放的matrix参数.
        Matrix matrix = new Matrix();
        matrix.postScale(k, k);
        // 得到新的图片.
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }


    /**
     * Only when you click the view, the soft keyboard will show.
     *
     * @param event Touch event
     * @return result
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startY = event.getY(); // Get the position y when you click it at the first time
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            float endY = event.getY();    // get the end position y when you hand up your finger
            if (endY - startY > 50.0f || startY - endY > 50.0f) {
                this.setFocusable(false);   // lose the focus
                im.hideSoftInputFromWindow(this.getApplicationWindowToken(), 0);
            } else {
                this.setFocusable(true);    // focus back
                this.setFocusableInTouchMode(true);
                im.showSoftInput(this, 0);
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * get Selection's location
     * @param selStart location
     */
    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        selStart = selStart;
    }

    public static int getSelectStart() {
        return selectStart;
    }
}
