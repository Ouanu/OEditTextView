package com.moment.myview.view.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;

import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.moment.myview.view.OEditText;

import org.jetbrains.annotations.NotNull;

public class OTools {
    private Context context;
    private OEditText view;
    private ActivityResultLauncher launcher;

    public OTools(Context context, OEditText view, Activity activity) {
        this.context = context;
        this.view = view;
    }






}
