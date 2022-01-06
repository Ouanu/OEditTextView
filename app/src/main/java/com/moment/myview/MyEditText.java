package com.moment.myview;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyEditText extends androidx.appcompat.widget.AppCompatEditText {

    private Bitmap bitmap;
    private static final String REGEX = "!\\[[^]]*]\\((?<filename>.*?)(?=[\")])(?<optionalpart>\".*\")?\\)";

    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMySpan(Uri str, Context mContext) {
        Pattern p = Pattern.compile(REGEX);
        Matcher matcher = p.matcher(Objects.requireNonNull(this.getText())); // 获取 matcher 对象
//        this.setText(str.toString());
        while (matcher.find()) {
//            Log.d("PIC", "setStyle: " + matcher.group(1));
            try {
                Log.d("+++++++++++++", "setMySpan: " + matcher.group(1));
                File file = new File("/data/user/0/com.moment.myview/files/DCIM/1641491381615.jpg");
                if (file.exists()) {
                    Log.d("++++++++++", "setMySpan: +++++");
                    bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), Uri.fromFile(file));
                } else {
                    Log.d("++++++++++", "setMySpan: ------" + matcher.group(1).replace("file://", ""));
                }

//                bitmap = ;
//                image = BitmapFactory.decodeFile(matcher.group(1));
//                int width = image.getWidth();
//                int height = image.getHeight();
//                // 计算缩放比例.
//                float k = ((float) 640) / width;
//                // 取得想要缩放的matrix参数.
//                Matrix matrix = new Matrix();
//                matrix.postScale(k, k);
//                // 得到新的图片.
//                Bitmap newBitmap = Bitmap.createBitmap(image, 0, 0, width, height, matrix, true);
                this.getText().setSpan(
                        new ImageSpan(mContext, bitmap), matcher.start(), matcher.end(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
//        try {
//            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), str);
//            this.getText().setSpan(new ImageSpan(context, bitmap),
//                    0,
//                    this.getText().length()-1,
//                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
//            );
//            this.getText().insert(this.getText().length()-1, "/n");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}
