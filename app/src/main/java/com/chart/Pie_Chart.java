package com.chart;

import static com.github.mikephil.charting.components.Legend.LegendPosition.BELOW_CHART_CENTER;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxs.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class Pie_Chart extends AppCompatActivity {
    private PieChart pieChart;
    private List<PieEntry> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pie_layout);
        init();
    }
    private void init(){
        pieChart=findViewById(R.id.pie_chart);
        list=new ArrayList<>();
        list.add(new PieEntry(55,"男"));
        list.add(new PieEntry(45,"女"));
        PieDataSet dataSet=new PieDataSet(list,"");
        dataSet.setValueTextSize(20);
        dataSet.setValueTextColor(Color.BLACK);
        PieData data=new PieData(dataSet);
        pieChart.setData(data);
        pieChart.setEntryLabelTextSize(25);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setCenterText("男女比例");
        pieChart.setCenterTextSize(20);
//        自带的英文消失
        pieChart.getDescription().setEnabled(false);
        pieChart.getDescription().setText("");
        pieChart.setBackgroundColor(Color.LTGRAY);
        dataSet.setColors(Color.RED,Color.CYAN);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueLinePart1Length(0.4f);
        dataSet.setValueLinePart2Length(0.3f);//设置数据连接线长度
        dataSet.setValueLineColor(Color.MAGENTA);

        //图例
        Legend legend=pieChart.getLegend();
        legend.setEnabled(true);    //是否显示图例
        legend.setPosition(BELOW_CHART_CENTER);    //图例的位置

        pieChart.animateX(2000);
        pieChart.animateY(2000);
    }
}
