package com.example.lxs.player;
import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.lxs.R;

import java.io.File;
import java.io.IOException;

public class player_Main extends AppCompatActivity implements View.OnClickListener {
    private MediaPlayer mediaPlayer=new MediaPlayer();
//    private VideoView view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_layout);
//        view=findViewById(R.id.media_player);
        Button btn_start=findViewById(R.id.btn_start);
        Button btn_pause=findViewById(R.id.btn_pause);
        Button btn_reset=findViewById(R.id.btn_reset);
        btn_start.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(player_Main.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(player_Main.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else{
            initMediaPlayer();
        }
    }
    private void initMediaPlayer(){
        try {
            File file=new File(Environment.getExternalStorageDirectory(),"Download/feint_stray.mp3");
            mediaPlayer.setDataSource(file.getPath());
            Log.d("mps",file.getPath());
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    initMediaPlayer();
                }else{
                    Toast.makeText(player_Main.this,"失败",Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
    @Override
    public void onClick(View v){
            switch(v.getId()){
                case R.id.btn_start:
                    if (!mediaPlayer.isPlaying()){
                        mediaPlayer.start();
                    }
                    break;
                case R.id.btn_pause:
                    if (mediaPlayer.isPlaying()){
                        mediaPlayer.pause();
                    }
                    break;
                case R.id.btn_reset:
                    if (!mediaPlayer.isPlaying()||mediaPlayer.isPlaying()){
                        mediaPlayer.reset();
                        initMediaPlayer();
                    }
                    break;
                default:
                    break;
            }
    }
}
