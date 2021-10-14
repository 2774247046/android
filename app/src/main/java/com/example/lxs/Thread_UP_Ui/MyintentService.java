package com.example.lxs.Thread_UP_Ui;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxs.R;

public class MyintentService extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.renew_ui_layout);
        Button button=findViewById(R.id.renew_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MyintentService","id"+Thread.currentThread().getId());
                Intent intent=new Intent(MyintentService.this,IntentService_.class);
                startService(intent);
            }
        });
    }
}
