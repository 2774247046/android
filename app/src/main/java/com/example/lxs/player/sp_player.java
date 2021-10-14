package com.example.lxs.player;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.lxs.R;

import org.litepal.LitePal;

import java.io.File;
import java.io.IOException;
public class sp_player extends AppCompatActivity implements View.OnClickListener{
    private VideoView view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_layout);
        view=findViewById(R.id.media_player);
        Button btn_start=findViewById(R.id.btn_start);
        Button btn_pause=findViewById(R.id.btn_pause);
        Button btn_reset=findViewById(R.id.btn_reset);
        btn_start.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(sp_player.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(sp_player.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else{
            initMediaPlayer();
        }
    }
    private void initMediaPlayer(){
        File file=new File(Environment.getExternalStorageDirectory(),"Documents/1545582900.mp4");
        view.setVideoPath(file.getPath());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                if (!view.isPlaying()){
                    view.start();
                }
                break;
            case R.id.btn_pause:
                if (view.isPlaying()){
                    view.pause();
                }
                break;
            case R.id.btn_reset:
                if (!view.isPlaying()||view.isPlaying()){
                    view.resume();
                }
                break;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    initMediaPlayer();
                }else{
                    Toast.makeText(sp_player.this,"失败",Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (view!=null){
            view.suspend();
        }
    }
}
