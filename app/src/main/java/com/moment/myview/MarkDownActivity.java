package com.moment.myview;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.moment.myview.view.OMDEditTextView;

public class MarkDownActivity extends AppCompatActivity implements View.OnClickListener {


    private OMDEditTextView oetText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_down);
        initView();
    }

    private void initView() {

        oetText = (OMDEditTextView) findViewById(R.id.oet_text);

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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bold:
                oetText.getEditText().getText().append("****");
                oetText.getEditText().setSelection(oetText.getEditText().getSelectionStart() - 2);
                break;
            case R.id.add_title:
                oetText.getEditText().getText().append("# ");
                oetText.getEditText().setSelection(oetText.getEditText().getSelectionStart());
                break;
            case R.id.italic:
                oetText.getEditText().getText().append("**");
                oetText.getEditText().setSelection(oetText.getEditText().getSelectionStart() + 1);
                break;
            case R.id.add_list:
                oetText.getEditText().getText().append("* ");
                oetText.getEditText().setSelection(oetText.getEditText().getSelectionStart());
                break;
            case R.id.btn_get_uri:
                oetText.getEditText().getText().append("![picture alt](link \"title\")");
                oetText.getEditText().setSelection(oetText.getEditText().getSelectionStart());
                break;
            default:
                break;

        }

    }
}