package com.moment.myview;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.moment.myview.view.OEditTextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "MAIN_ACTIVITY";

    private OEditTextView oetText;
    private static boolean isBold = false;
    private StringBuilder builder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        oetText = findViewById(R.id.oet_text);

        TextView bold = findViewById(R.id.bold);
        TextView size = findViewById(R.id.size);
        TextView italic = findViewById(R.id.italic);
        TextView addTitle = findViewById(R.id.add_title);
        ImageView addList = findViewById(R.id.add_list);
        ImageView btnGetUri = findViewById(R.id.btn_get_uri);

        bold.setOnClickListener(this);
        size.setOnClickListener(this);
        italic.setOnClickListener(this);
        addTitle.setOnClickListener(this);
        addList.setOnClickListener(this);
        btnGetUri.setOnClickListener(this);

        oetText.getEditText().addTextChangedListener(new TextWatcher() {
            int input_start;
            int input_end;
            private boolean editable = true;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                input_start = start;
                input_end = start + count;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bold) {
            Log.d(TAG, "onClick: " + oetText.getEditText().getText());
//            isBold = isBold? false : true;

            Objects.requireNonNull(oetText.getEditText().getText()).append("****");
            oetText.getEditText().setSelection(oetText.getEditText().getSelectionStart()-2);
        } else if (v.getId() == R.id.size) {
            File file = new File("/data/data/com.moment.myview/test.txt");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(oetText.getEditText().getText().toString().getBytes());
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}