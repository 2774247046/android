package com.example.lxs.Thread_UP_Ui;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxs.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class Renew_UI extends AppCompatActivity {
    private TextView textView;
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.renew_ui_layout);
        button=findViewById(R.id.renew_button);
        textView=findViewById(R.id.renew_text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message=new Message();
                message.what=1;
                Message_text(message);
            }
        });
    }
    private void  Message_text(Message message){
        switch (message.what){
            case 1:
                Log.d("6","6");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("7","7");
                        OkHttpClient okHttpClient =new OkHttpClient();
                        Request request=new Request.Builder()
                                .url("https://www.bilibili.com/")
                                .build();
                        try {
                            Response response=okHttpClient.newCall(request).execute();
                            String aa=response.body().string();
                            Ui_text(aa);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            default:
                break;
        }
    }
    private void Ui_text(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("8","8");
                textView.setText(text);
            }
        });
    }
}
