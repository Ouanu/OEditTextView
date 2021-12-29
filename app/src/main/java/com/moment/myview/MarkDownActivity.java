package com.moment.myview;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.moment.myview.view.OMDEditTextView;

import java.util.Objects;

public class MarkDownActivity extends AppCompatActivity implements View.OnClickListener {


    private OMDEditTextView oetText;
    private static boolean isEditing = false;
    private static boolean isBold = false;
    TextView bold;
    TextView size;
    TextView italic;
    TextView addTitle;
    ImageView addList;
    ImageView btnGetUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_down);
        initView();
    }

    private void initView() {
        oetText = (OMDEditTextView) findViewById(R.id.oet_text);

        bold = findViewById(R.id.bold);
        size = findViewById(R.id.size);
        italic = findViewById(R.id.italic);
        addTitle = findViewById(R.id.add_title);
        addList = findViewById(R.id.add_list);
        btnGetUri = findViewById(R.id.btn_get_uri);

        bold.setOnClickListener(this);
        size.setOnClickListener(this);
        italic.setOnClickListener(this);
        addTitle.setOnClickListener(this);
        addList.setOnClickListener(this);
        btnGetUri.setOnClickListener(this);


    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bold:
                oetText.getEditText().getText().insert(oetText.getEditText().getSelectionStart(), "****");
                oetText.getEditText().setSelection(oetText.getEditText().getSelectionStart()-2);
                break;
            case R.id.add_title:
                Objects.requireNonNull(oetText.getEditText().getText()).append("# ");
                oetText.getEditText().setSelection(oetText.getEditText().getSelectionStart());
                break;
            case R.id.italic:
                oetText.getEditText().getText().insert(oetText.getEditText().getSelectionStart(), "**");
                oetText.getEditText().setSelection(oetText.getEditText().getSelectionStart()-1);
                break;
            case R.id.add_list:
                Objects.requireNonNull(oetText.getEditText().getText()).append("* ");
                oetText.getEditText().setSelection(oetText.getEditText().getSelectionStart());
                break;
            case R.id.btn_get_uri:
//                oetText.getEditText().getText().append("![picture alt](link \"title\")");
//                oetText.getEditText().setSelection(oetText.getEditText().getSelectionStart());

                break;
            default:
                break;

        }

    }
}