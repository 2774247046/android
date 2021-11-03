package com.chart;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxs.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class Line_chart extends AppCompatActivity {
    private LineChart chart;
    private List<Entry> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_chart_layout);
        init();
    }
    private void init(){
        chart=findViewById(R.id.line_chart);
        list=new ArrayList<>();
        list.add(new Entry(0,5));
        list.add(new Entry(5,3));
        list.add(new Entry(10,18));
        list.add(new Entry(15,30));
        LineDataSet set=new LineDataSet(list,"");
        set.setValueTextSize(20);
        LineData lineData=new LineData(set);
        lineData.setValueTextSize(15);
        chart.setData(lineData);

        //   X轴所在位置   默认为上面
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        //隐藏右边的Y轴
        chart.getAxisRight().setEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.notifyDataSetChanged();
        chart.invalidate();

        chart.animateY(3000); //折线在Y轴的动画  参数是动画执行时间 毫秒为单位
        chart.animateX(2000); //X轴动画
    }
}
