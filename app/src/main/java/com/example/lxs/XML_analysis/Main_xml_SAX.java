package com.example.lxs.XML_analysis;

import android.os.Bundle;

import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxs.R;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class Main_xml_SAX extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_xml_layout);
        Button button=findViewById(R.id.xml_button);
        button.setOnClickListener(v->{
            start_analysis();
        });
    }
    private void start_analysis(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder()
                            .url("https://www.runoob.com/try/xml/note.xml")
                            .build();
                    Response response=client.newCall(request).execute();
                    String hh=response.body().string();
                    parse(hh);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void parse(String hh){
        try {
            SAXParserFactory factory=SAXParserFactory.newInstance();
            XMLReader reader=factory.newSAXParser().getXMLReader();
            SAX sax=new SAX();
            reader.setContentHandler(sax);
            reader.parse(new InputSource(new StringReader(hh)));
            String node_name=sax.node_name;
            StringBuilder to=sax.to;
            StringBuilder from=sax.from;
            StringBuilder heading=sax.heading;
            StringBuilder body=sax.body;
            Log.i("node_name",node_name.trim());
            Log.i("to", String.valueOf(to).trim());
            Log.i("from", String.valueOf(from).trim());
            Log.i("heading", String.valueOf(heading).trim());
            Log.i("body", String.valueOf(body).trim());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
