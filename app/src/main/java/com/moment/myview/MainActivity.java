package com.moment.myview;

import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.moment.myview.view.OEditTextView;
import com.moment.myview.view.OToolBar;


public class MainActivity extends AppCompatActivity {

    private OEditTextView oetText;
    private RelativeLayout rlMain;
    private OToolBar toolbar;
    private ViewTreeObserver observer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        rlMain = findViewById(R.id.rl_main);
        oetText = findViewById(R.id.oet_text);
        toolbar = findViewById(R.id.toolbar);
        observer = rlMain.getViewTreeObserver();

    }

}