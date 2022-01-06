package com.moment.myview;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;

import java.io.IOException;

public class MyEditText extends androidx.appcompat.widget.AppCompatEditText {

    private Bitmap bitmap;

    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMySpan(Uri str, Context context) {
        this.setText(str.toString());
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), str);
            this.getText().setSpan(new ImageSpan(context, bitmap),
                    0,
                    this.getText().length()-1,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );
            this.getText().insert(this.getText().length()-1, "/n");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
