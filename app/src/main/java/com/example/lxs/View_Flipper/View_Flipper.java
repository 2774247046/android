package com.example.lxs.View_Flipper;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxs.R;
public class View_Flipper extends AppCompatActivity {
    private ViewFlipper vflp_help;
    private int[]a={R.drawable.a1,R.drawable.a2,R.drawable.a3};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_flipper_layout);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(null);
            actionBar.setHomeAsUpIndicator(R.drawable.a1);
        }
        vflp_help =findViewById(R.id.vflp_help);
        vflp_help.startFlipping();

    }
}
