package com.moment.myview;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.moment.oetlib.view.OEditTextView;
import com.moment.oetlib.view.OToolBarView;
import com.moment.oetlib.view.tools.OPictureTool;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

public class MarkDownActivity extends AppCompatActivity implements View.OnClickListener {

    private OEditTextView oetText;
    TextView bold;
    TextView italic;
    TextView addTitle;
    ImageView addList;
    ImageView btnGetUri;
    private ContentResolver resolver;
    private int startSelect;

    // Activity返回结果
    private final ActivityResultLauncher<String> mGetContent = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            result -> {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    );


    private OToolBarView toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_down);

        initView();
    }

    private void initView() {
        oetText = findViewById(R.id.oet_text);

        resolver = getContentResolver();

        bold = findViewById(R.id.bold);
        italic = findViewById(R.id.italic);
        addTitle = findViewById(R.id.add_title);
        addList = findViewById(R.id.add_list);
        btnGetUri = findViewById(R.id.btn_get_uri);

        bold.setOnClickListener(this);
        italic.setOnClickListener(this);
        addTitle.setOnClickListener(this);
        addList.setOnClickListener(this);
        btnGetUri.setOnClickListener(this);
        toolbar = findViewById(R.id.toolbar);


    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bold:
                Objects.requireNonNull(oetText.getEditText().getText()).insert(oetText.getEditText().getSelectionStart(), "****");
                oetText.getEditText().setSelection(oetText.getEditText().getSelectionStart() - 2);
                break;
            case R.id.add_title:
                Objects.requireNonNull(oetText.getEditText().getText()).append("\n# ");
                oetText.getEditText().setSelection(oetText.getEditText().getSelectionStart());
                break;
            case R.id.italic:
                Objects.requireNonNull(oetText.getEditText().getText()).insert(oetText.getEditText().getSelectionStart(), "**");
                oetText.getEditText().setSelection(oetText.getEditText().getSelectionStart() - 1);
                break;
            case R.id.add_list:
                Objects.requireNonNull(oetText.getEditText().getText()).insert(oetText.getEditText().getSelectionStart(), "* ");
                oetText.getEditText().setSelection(oetText.getEditText().getSelectionStart());
                break;
            case R.id.btn_get_uri:
                startSelect = oetText.getEditText().getSelectionStart();
                mGetContent.launch("image/*");
            default:
                break;
        }

    }

    // 解析uri，获取图片真实地址
    private String parseImageUri(Uri uri) {
        String imagePath = null;

        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);


            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {   // content://com.miui.gallery.open/raw/%2Fstorage%2Femulated%2F0%2FDCIM%2FScreenshots%2FScreenshot_2020-10-25-14-43-57-798_com.miui.home.jpg


            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {


            imagePath = uri.getPath();
        }


        return imagePath;
    }

    // 获取真实图片地址
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }


}