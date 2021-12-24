package com.moment.myview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.RelativeLayout;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.moment.myview.view.OEditTextView;
import com.moment.myview.view.OToolBar;


public class MainActivity extends AppCompatActivity {

    private OEditTextView oetText;
    private RelativeLayout rlMain;
    private OToolBar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        rlMain = findViewById(R.id.rl_main);
        oetText = findViewById(R.id.oet_text);
//        FloatViewUtil util = new FloatViewUtil(this);
//        toolbar = findViewById(R.id.toolbar);
//        util.setFloatView(oetText, toolbar, 0);
//        util.setFloatView(rlMain, oetText, 0);
    }

}