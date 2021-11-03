package com.chart;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxs.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Bar_Chart extends AppCompatActivity {
    private BarChart bar_chart;
    private List<BarEntry>list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_chart_layout);
        init();
    }
    private void init(){
        bar_chart=findViewById(R.id.bar_chart);
        list=new ArrayList<>();
        Http_getList();
        bar_chart.notifyDataSetChanged();
        BarDataSet set=new BarDataSet(list,"温度");
        BarData data=new BarData(set);
        data.setBarWidth(0.3f);
        set.setValueTextSize(15);
        set.setColor(Color.BLACK);  //柱子的颜色
        set.setColors(Color.BLACK,Color.BLUE);//设置柱子多种颜色  循环使用
        set.setBarBorderColor(Color.CYAN);//柱子边框颜色
        set.setBarBorderWidth(2);       //柱子边框厚度
        set.setBarShadowColor(Color.RED);
        set.setHighlightEnabled(false);//选中柱子是否高亮显示  默认为true

        bar_chart.setData(data);
        bar_chart.getAxisRight().setEnabled(false);
        bar_chart.getDescription().setEnabled(false);

        Legend legend=bar_chart.getLegend();
        legend.setEnabled(true);    //是否显示图例
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);

        bar_chart.getAxisLeft().setAxisLineWidth(2);
        bar_chart.getAxisLeft().setAxisLineColor(Color.LTGRAY);
        bar_chart.getAxisLeft().setDrawGridLines(false);
        bar_chart.getXAxis().setAxisMinimum(0);
        bar_chart.getXAxis().setAxisMaximum(5);
        bar_chart.getXAxis().setEnabled(false);
        bar_chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        bar_chart.getXAxis().setValueFormatter((value, axis) -> {
            if (value==1){
                return "第一个";
            }
            if (value==2){
                return "第二个";
            }
            if (value==3){
                return "第三个";
            }
            if (value==4){
                return "第四个";
            }
            return "";
        });
        bar_chart.getAxisLeft().setAxisMinimum(0);
        bar_chart.getAxisLeft().setAxisMaximum(100);
        bar_chart.getAxisLeft().setValueFormatter((value, axis) -> {
            for (int a=0;a<100;a++){     //用个for循环方便
                if (a==value){
                    return a+"C°";
                }
            }
            return "";
        });
        bar_chart.setSelected(false);
        bar_chart.setDragEnabled(false);
        bar_chart.animateXY(2000,3000);
    }
    private void Http_getList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection connection= (HttpURLConnection) new URL("http://10.0.2.2:8080/Chart_war_exploded/hello-servlet?type=Rad").openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    InputStream stream=connection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(stream));
                    StringBuilder buffer=new StringBuilder();
                    String data;
                    if ((data=reader.readLine())!=null){
                        buffer.append(data);
                    }
                    List(buffer.toString());
                } catch (IOException  e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void List(String data){
        try {
            JSONObject jsonObject=new JSONObject(data);
            String JSONData=jsonObject.getString("data");
            String JSONData1=jsonObject.getString("data1");
            String JSONData2=jsonObject.getString("data2");
            String JSONData3=jsonObject.getString("data3");
            list.add(new BarEntry(1, Float.parseFloat(JSONData)));
            list.add(new BarEntry(2, Float.parseFloat(JSONData2)));
            list.add(new BarEntry(3, Float.parseFloat(JSONData3)));
            list.add(new BarEntry(4, Float.parseFloat(JSONData1)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
