package com.moment.myview.view;
/*
    name: OEditView
    author: Ouanu
    date: 2021.11.2
 */
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A View which can insert images
 */
public class OEditText extends androidx.appcompat.widget.AppCompatEditText {
    private static final String TAG = "EditText触摸事件";
    private float startY = 0.0f;
    private float endY = 0.0f;
    private InputMethodManager im;

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
        im = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    /**
     * insert images into edittext
     *
     * @param desc select uri from the description
     */
    public void insertImage(String desc) {
        SpannableStringBuilder builder = new SpannableStringBuilder(desc);
        String rex = "<([^>]*)>";    //regular expression
        Pattern pattern = Pattern.compile(rex);// mount regular expression
        Matcher matcher = pattern.matcher(desc);
        ContentResolver contentResolver = getContext().getContentResolver();
        Bitmap bitmap;
        String uri;
        while (matcher.find()) {
            uri = matcher.group().replaceAll("[<>]", "");
            try {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, Uri.parse(uri));
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                // 计算缩放比例.
                float k = ((float) 640) / width;
                // 取得想要缩放的matrix参数.
                Matrix matrix = new Matrix();
                matrix.postScale(k, k);
                // 得到新的图片.
                Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
                builder.setSpan(
                        new ImageSpan(getContext(), newBitmap), matcher.start(), matcher.end(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                this.setText(builder);
                this.setSelection(desc.length());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * save the image
     *
     * @param uri of image
     * @return the uri which image we save
     */

    public Uri saveImage(Uri uri, String path) {
        Toast.makeText(getContext(), path, Toast.LENGTH_SHORT).show();
        String name = String.valueOf(System.currentTimeMillis());
        File saveFile = new File(path, name);
        FileOutputStream saveOutImage;
        try {
            saveOutImage = new FileOutputStream(saveFile);
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, saveOutImage);
            saveOutImage.flush();
            saveOutImage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Uri.fromFile(saveFile);
    }

    /**
     * delete the Image File of item
     *
     * @param path the file which we need to deal with
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void deleteImage(String path) {
        File file = new File(path);
        if (file.exists())
            file.delete();
    }

    /**
     * Only when you click the view, the soft keyboard will show.
     *
     * @param event Touch event
     * @return result
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startY = event.getY(); // Get the position y when you click it at the first time
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            endY = event.getY();    // get the end position y when you hand up your finger
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
}
