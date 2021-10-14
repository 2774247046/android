package com.example.lxs.XML_analysis;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class HttpUtil {
    public static void sendHttpRequest(String http_url,okhttp3.Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(http_url);
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder()
                            .url(url)
                            .build();
                    client.newCall(request).enqueue(callback);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
