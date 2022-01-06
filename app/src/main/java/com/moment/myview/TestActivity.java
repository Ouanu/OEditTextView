package com.moment.myview;

import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private MyEditText etText;
    private Button btnGetImage;
    private Bitmap bitmap;
    private String DcimPath = "";

    private ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), result -> {

        File saveFile = new File(DcimPath, System.currentTimeMillis() + ".jpg");
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result);
            FileOutputStream saveImgOut = new FileOutputStream(saveFile);
            // compress - 压缩的意思
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, saveImgOut);
            //存储完成后需要清除相关的进程
            saveImgOut.flush();
            saveImgOut.close();
            etText.getText().insert(0, "![Image](" + Uri.fromFile(saveFile) + " \"Image\")");
//            etText.setText(Uri.fromFile(saveFile).toString());
//            etText.getText().setSpan(new ImageSpan(this, MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.fromFile(saveFile))), 0, etText.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            Log.d("+++++++++++++", "setMySpan: " + Uri.fromFile(saveFile));
            Log.d("Save Bitmap", "The picture is save to your phone!");
            etText.setMySpan(Uri.fromFile(saveFile), this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        DcimPath = getApplicationContext().getFilesDir().getAbsolutePath() + "/DCIM";
        initView();
    }

    private void initView() {
        etText = findViewById(R.id.et_text);
        btnGetImage = (Button) findViewById(R.id.btn_get_image);
        btnGetImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_get_image) {
            mGetContent.launch("image/*");
        }
    }
}