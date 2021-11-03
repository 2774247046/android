package com.download;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxs.R;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class downLoad extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_layout);
        EditText text=findViewById(R.id.URl_text);
        Button button=findViewById(R.id.btn_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uRl=text.getText().toString();
                downLoad_1(uRl,downLoad.this);
            }
        });
    }
    private void downLoad_1(String path, Context context){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://autopatchcn.yuanshen.com/client_app/launcher/20211013_801c9f201b49a256/yuanshen_setup_mihoyo_20210924233112.exe");
                    InputStream stream =url.openStream();
                    String end = path.substring(path.lastIndexOf("."));
                    OutputStream os = context.openFileOutput("Cache_"+System.currentTimeMillis()+end, Context.MODE_PRIVATE);
                    byte[] buffer = new byte[1024];
                    int len;
                    //从输入六中读取数据,读到缓冲区中
                    while((len = stream.read(buffer))>0){
                        os.write(buffer,0,len);
                    }
                    //关闭输入输出流
                    stream.close();
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
