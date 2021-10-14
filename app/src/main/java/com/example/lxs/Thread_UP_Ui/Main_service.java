package com.example.lxs.Thread_UP_Ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxs.R;

public class Main_service extends AppCompatActivity implements View.OnClickListener {
    private MyService.DownloadBinder downloadBinder;
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder= (MyService.DownloadBinder) service;
            downloadBinder.starDownload();
            downloadBinder.getP();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_layout);
        Button button=findViewById(R.id.start_button);
        Button button1=findViewById(R.id.stop_button);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Button button=(Button)v;
        if (button.getId()==R.id.start_button){
            Intent intent=new Intent(this,MyService.class);
            bindService(intent,connection,BIND_AUTO_CREATE);
        }else if (button.getId()==R.id.stop_button){
            unbindService(connection);
        }
    }
}
