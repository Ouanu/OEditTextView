package com.moment.myview;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.moment.oetlib.view.OEditTextView;
import com.moment.oetlib.view.OToolBarView;
import com.moment.oetlib.view.tools.OPictureTool;

import java.io.File;
import java.io.FileOutputStream;
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
    private Bitmap bitmap;
    private String DcimPath = "";

    // Activity返回结果
    private final ActivityResultLauncher<String> mGetContent = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            result -> {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result);
                    File saveFile = new File(DcimPath, System.currentTimeMillis() + ".jpg");

                    try {
                        FileOutputStream saveImgOut = new FileOutputStream(saveFile);
                        // compress - 压缩的意思
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, saveImgOut);
                        //存储完成后需要清除相关的进程
                        saveImgOut.flush();
                        saveImgOut.close();
                        Log.d("Save Bitmap", "The picture is save to your phone!");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    oetText.getEditText().getText().insert(startSelect, "![Image](" + saveFile.getAbsolutePath() + " \"Image\")");
                    oetText.getEditText().getOTools().addToolItem(new OPictureTool(oetText.getEditText(), resolver));
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
        DcimPath = getApplicationContext().getFilesDir().getAbsolutePath() + "/DCIM";
        Log.d("PATH++++", "onCreate: " + DcimPath);
        initView();
        fileInit();
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

    public void fileInit() {
        File file = new File(DcimPath);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
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
}