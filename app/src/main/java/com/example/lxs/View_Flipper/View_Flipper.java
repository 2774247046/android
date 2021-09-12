package com.example.lxs.View_Flipper;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;

import com.example.lxs.R;
public class View_Flipper extends Activity {
    private ViewFlipper vflp_help;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_flipper_layout);
        vflp_help =findViewById(R.id.vflp_help);
        vflp_help.startFlipping();
    }
}
