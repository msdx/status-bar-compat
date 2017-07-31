package com.githang.statusbar.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.githang.statusbar.StatusBarCompat;
import com.githang.statusbarcompat.demo.R;
import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.SVBar;

public class MainActivity extends AppCompatActivity implements ColorPicker.OnColorChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ColorPicker picker = (ColorPicker) findViewById(R.id.picker);
        SVBar svBar = (SVBar) findViewById(R.id.svbar);

        picker.addSVBar(svBar);
        picker.setOldCenterColor(picker.getColor());
        picker.setOnColorChangedListener(this);
    }

    @Override
    public void onColorChanged(int color) {
        StatusBarCompat.setStatusBarColor(this, color);
    }
}
