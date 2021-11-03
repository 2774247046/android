package com.chart;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxs.R;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class Radar_Chart extends AppCompatActivity {
    private List<String>list=new ArrayList<>();
    private RadarChart radarChart;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mp_layout);
        init();
    }
    private void init(){
        radarChart=findViewById(R.id.MP_chart);
        radarChart.getDescription().setEnabled(false);
        radarChart.setBackgroundColor(Color.LTGRAY);
        radarChart.getYAxis().setAxisMinimum(0);
        radarChart.setWebColorInner(Color.CYAN);
        radarChart.setWebColor(Color.CYAN);
        radarChart.getXAxis().setAxisMinimum(0);
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(1f);
        xAxis.setTextSize(20);
        xAxis.setLabelCount(10);
        xAxis.setCenterAxisLabels(true);//设置标签居中
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axis) {
                if (v==0){
                    return "北";
                }
                if (v==1){
                    return "东";
                }
                if (v==2){
                    return "南";
                }
                if (v==3){
                    return "西";
                }
                return "";
            }
        });
        YAxis yAxis=radarChart.getYAxis();
        yAxis.setAxisMaximum(100);
        yAxis.setGranularity(1f);
        yAxis.setLabelCount(10);

        List<RadarEntry>radarCharts=new ArrayList<>();
        radarCharts.add(new RadarEntry(40));
        radarCharts.add(new RadarEntry(10));
        radarCharts.add(new RadarEntry(60));
        radarCharts.add(new RadarEntry(80));

        List<RadarEntry>radarCharts2=new ArrayList<>();
        radarCharts2.add(new RadarEntry(80));
        radarCharts2.add(new RadarEntry(20));
        radarCharts2.add(new RadarEntry(120));
        radarCharts2.add(new RadarEntry(40));;

        RadarDataSet dataSet=new RadarDataSet(radarCharts,"数据一");
        dataSet.setValueTextSize(10);
        dataSet.setValueTextColor(Color.RED);
        dataSet.setColor(Color.RED);
        RadarDataSet dataSet2=new RadarDataSet(radarCharts2,"数据二");
        dataSet2.setValueTextSize(10);
        dataSet2.setColor(Color.BLACK);
        dataSet2.setValueTextColor(Color.BLACK);

        RadarData dataSets=new RadarData(dataSet);
        dataSets.addDataSet(dataSet2);
        radarChart.setData(dataSets);
//        LineChart chart=findViewById(R.id.MP_chart);
//        chart.setDrawBorders(false);
//        chart.setBorderColor(Color.RED);
//        chart.setBorderWidth(1f);
//        chart.setTouchEnabled(true);
//        chart.setDragDecelerationFrictionCoef(1f);
//        chart.setDragEnabled(true);
//        chart.setDoubleTapToZoomEnabled(false);
//        chart.setDrawGridBackground(false);
//        chart.setHapticFeedbackEnabled(true);
//        chart.setPinchZoom(true);
//        chart.setBackgroundColor(Color.LTGRAY);
//        chart.animateX(2500);
//        XAxis xaxis=chart.getXAxis();
//        xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xaxis.setDrawGridLines(false);
//        xaxis.setDrawLabels(true);
//        xaxis.setGranularity(1f);
//        xaxis.setTextSize(15);
//        xaxis.setLabelCount(list.size());
//        xaxis.setCenterAxisLabels(true);
//        xaxis.setValueFormatter(new IndexAxisValueFormatter(list));
////        setData(20,30);
//        Description description=new Description();
//        description.setText("描述内容");
//        chart.setDescription(description);
//        chart.setNoDataText("没有数据");
//        Legend a=chart.getLegend();
//        a.setForm(Legend.LegendForm.CIRCLE);
//
    }
}
