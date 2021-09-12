package com.example.lxs.player;
import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
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

public class player_Main extends AppCompatActivity {
    private MediaPlayer mediaPlayer=new MediaPlayer();
    private VideoView view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_layout);
        view=findViewById(R.id.media_player);
        Button btn_start=findViewById(R.id.btn_start);
        Button btn_pause=findViewById(R.id.btn_pause);
        Button btn_reset=findViewById(R.id.btn_reset);
        btn_start.setOnClickListener(h);
        btn_pause.setOnClickListener(h);
        btn_reset.setOnClickListener(h);
        if (ContextCompat.checkSelfPermission(player_Main.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(player_Main.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else{
            initMediaPlayer();
        }
    }
    private View.OnClickListener h=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button=(Button)v;
            switch (button.getId()){
                case R.id.btn_start:
                    if (!mediaPlayer.isPlaying()){
                        mediaPlayer.start();
                    }
                    break;
                case R.id.btn_pause:

                    if (!mediaPlayer.isPlaying()){
                        mediaPlayer.pause();
                    }
                    break;
                case R.id.btn_reset:
                    if (!mediaPlayer.isPlaying()){
                        mediaPlayer.reset();
                    }
                    break;
                default:
            }
        }
    };
    private void initMediaPlayer(){
        File file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS),"music.mp3");
        try {
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        } catch (IOException e) {
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
}
