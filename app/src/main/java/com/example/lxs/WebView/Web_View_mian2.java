package com.example.lxs.WebView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxs.R;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Web_View_mian2 extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_main);
        Button button=findViewById(R.id.WebView_button);
        textView=findViewById(R.id.WebView_text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestOKHttp();
            }
        });
    }
    private void  RequestOKHttp(){
    BlockingQueue<Runnable>blockingQueue=new LinkedBlockingQueue<>();
    ExecutorService service=new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors()*2, 1,TimeUnit.SECONDS,blockingQueue);
    service.execute(() -> {
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url("https://www.baidu.com/")
                .build();
        try {
            Response response=okHttpClient.newCall(request).execute();
            String hh=response.body().string();
            TextResponse(hh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
    }
    private void TextResponse(final String text){
        runOnUiThread(()-> {
                textView.setText(text);
        });
    }
}
