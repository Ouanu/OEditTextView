package com.moment.oetlib.view.tools;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;

import com.moment.oetlib.view.OEditText;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OPictureTool extends OToolItem {

    private static final String REGEX = "!\\[[^]]*]\\((?<filename>.*?)(?=[\")])(?<optionalpart>\".*\")?\\)";
    private Bitmap image;
    private Context context;


    public OPictureTool(OEditText oetText, Context context) {
        super(oetText);
        this.context = context;
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
            try {
                image = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(matcher.group(1)));
                int width = image.getWidth();
                int height = image.getHeight();
                // 计算缩放比例.
                float k = ((float) 640) / width;
                // 取得想要缩放的matrix参数.
                Matrix matrix = new Matrix();
                matrix.postScale(k, k);
                // 得到新的图片.
                Bitmap newBitmap = Bitmap.createBitmap(image, 0, 0, width, height, matrix, true);
                getOetText().getText().setSpan(
                        new ImageSpan(context, newBitmap), matcher.start(), matcher.end(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
