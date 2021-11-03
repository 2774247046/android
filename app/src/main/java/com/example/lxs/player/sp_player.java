package com.example.lxs.player;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.net.Uri;
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
//        File file=new File(Environment.getExternalStorageDirectory(),"Documents/1545582900.mp4");
        Uri uri=Uri.parse
        ("http://v5-h.douyinvod.com" +
                "/68f004382b2c5abfe7fcb9d249619475/617b61b6/" +
                "video/tos/cn/tos-cn-ve-15/974b864989e74fa4bb2b95786c" +
                "b372a4/?a=1128&br=1244&bt=1244&cd=0%7C0%7C0&ch=0&cr=0&cs=0" +
                "&cv=1&dr=0&ds=3&er=&ft=OR_LrKZZI02K1iTz7Th94ictRsWd.lK2_68&l=20211" +
                "029094708010212197134462E8EAC&lr=xigua_aweme_play_suffix&mime_type=video" +
                "_mp4&net=0&pl=0&qs=0&rc=amtvdmk6Zmc6NzMzNGkzM0ApO2c2OmVlNGQ8Nzw5OTk2NGcpaGRqbGRoaGRmYX" +
                "NrNnI0Xy0yYC0tZC0vc3MuLTZiLjU2MmIxYGEwMWMzOmNwb2wrbStqdDo%3D&vl=&vr=");
        view.setVideoURI(uri);
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
