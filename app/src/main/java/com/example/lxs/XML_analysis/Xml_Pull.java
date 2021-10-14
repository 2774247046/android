package com.example.lxs.XML_analysis;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxs.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Xml_Pull extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_main);
        Button button=findViewById(R.id.WebView_button);
        button.setOnClickListener(button_click);
    }

    private View.OnClickListener button_click=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            URl_pull();
        }
    };

    public void URl_pull(){
        BlockingQueue<Runnable>blockingQueue=new LinkedBlockingQueue<>();
        ExecutorService service=new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors()*2,1, TimeUnit.SECONDS,blockingQueue);
        service.execute(()->{
            OkHttpClient okHttpClient=null;
            Response response;
            Request request;
            request=new Request.Builder()
                    .url("http://192.168.1.105/get_data.xml")
                    .build();
            try {
                response=okHttpClient.newCall(request).execute();
                String text=response.body().string();
                xml_pull(text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public void xml_pull(String xmlData){
        try {
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            XmlPullParser parser=factory.newPullParser();
            parser.setInput(new StringReader(xmlData));
            int type=parser.getEventType();
            String id="";
            String name="";
            String version="";
            while (type!=XmlPullParser.END_DOCUMENT){
                String nodeName=parser.getName();
                switch (type){
                    case XmlPullParser.START_TAG:
                            if ("id".equalsIgnoreCase(nodeName)){
                                id=parser.nextText();
                            }else if ("name".equalsIgnoreCase(nodeName)){
                                name=parser.nextText();
                            }else if("version".equalsIgnoreCase(nodeName)){
                                version=parser.nextText();
                            }
                            break;
                    case XmlPullParser.END_TAG:
                        if ("app".equals(nodeName)){
                            Log.e("id",id);
                            Log.e("name",name);
                            Log.e("version",version);
                        }
                        break;
                }
                type=parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
