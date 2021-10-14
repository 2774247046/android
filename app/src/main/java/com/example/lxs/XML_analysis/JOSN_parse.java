package com.example.lxs.XML_analysis;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxs.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JOSN_parse extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_xml_layout);
        Button button=findViewById(R.id.xml_button);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
     new Thread(new Runnable() {
         @Override
         public void run() {
             try {
                 OkHttpClient client=new OkHttpClient();
                 Request request=new Request.Builder()
                         .url("http://10.0.2.2:8080/MyBook?type=AllData")
                         .build();
                 Response response=client.newCall(request).execute();
                 String a=response.body().string();
                 parseXMlJOSN(a);
             }catch (Exception e){
                 e.printStackTrace();
             }
         }
     }).start();
    }
    private void parseXMlJOSN(String xml){
        try {
            JSONArray jsonArray=new JSONArray(xml);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject =jsonArray.getJSONObject(i);
                String bookStatus=jsonObject.getString("bookStatus");
                String bookInfo=jsonObject.getString("bookInfo");
                String bookImage=jsonObject.getString("bookImage");
                String bookName=jsonObject.getString("bookName");
                String id=jsonObject.getString("id");
                String data=jsonObject.getString("data");
                Log.d("data",data.trim());
                Log.d("id",id.trim());
                Log.d("bookStatus",bookStatus.trim());
                Log.d("bookInfo",bookInfo.trim());
                Log.d("bookImage",bookImage.trim());
                Log.d("bookName",bookName.trim());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
