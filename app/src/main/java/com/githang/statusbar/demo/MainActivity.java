package com.githang.statusbar.demo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.githang.statusbar.StatusBarCompat;
import com.githang.statusbarcompat.demo.R;

public class MainActivity extends AppCompatActivity {

    private boolean mLightStatusBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBarColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
    }

    private void setStatusBarColor(@ColorInt int color) {
        StatusBarCompat.setStatusBarColor(this, color, mLightStatusBar);
    }

    public void onClick(View view) {
        mLightStatusBar = !mLightStatusBar;
        int color = mLightStatusBar ? Color.WHITE : ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
        setStatusBarColor(color);
    }

}
