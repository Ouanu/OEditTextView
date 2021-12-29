package com.moment.myview.view;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.moment.myview.R;

public class OToolBar extends LinearLayout implements View.OnClickListener{

    private final Context context;
    private OMDEditTextView oetText;

    private TextView bold;
    private TextView size;
    private TextView italic;
    private TextView addTitle;
    private ImageView addList;
    private ImageView btnGetUri;


    public OToolBar(Context context, OMDEditTextView oetText) {
        super(context);
        this.context = context;
        this.oetText = oetText;
        initView();
    }

    public OToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public OToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(context).inflate(R.layout.tool_bar, this);
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
