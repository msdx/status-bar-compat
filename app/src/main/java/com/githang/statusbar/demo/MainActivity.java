package com.githang.statusbar.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

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

        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggle);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                StatusBarCompat.setTranslucent(getWindow(), isChecked);
                StatusBarCompat.resetActionBarContainerTopMargin(getWindow());
                StatusBarCompat.setLightStatusBar(getWindow(), false);
            }
        });

        findViewById(R.id.no_action_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(NoActionBarActivity.class);
            }
        });
        findViewById(R.id.snack_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Show SnackBar", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onColorChanged(int color) {
        StatusBarCompat.setStatusBarColor(this, color);
    }

    private void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(this, cls));
    }
}
